package com.turkcell.planservice.services.concretes;

import com.essoft.event.packageEntity.PackageCreatedEvent;
import com.turkcell.planservice.dtos.packagedtos.requests.CreatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.requests.UpdatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.responses.PackageResponse;
import com.turkcell.planservice.dtos.productdtos.responses.ProductResponse;
import com.turkcell.planservice.entities.Package;
import com.turkcell.planservice.entities.Product;
import com.turkcell.planservice.kafka.PackageCreatedProducer;
import com.turkcell.planservice.mappers.PackageMapper;
import com.turkcell.planservice.mappers.ProductMapper;
import com.turkcell.planservice.repositories.PackageRepository;
import com.turkcell.planservice.rules.PackageBusinessRules;
import com.turkcell.planservice.services.abstracts.PackageService;
import com.turkcell.planservice.services.abstracts.ProductService;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final ProductService productService;
    private final PackageBusinessRules packageBusinessRules;
    private final AuditAwareImpl auditAware;
    private final PackageCreatedProducer packageCreatedProducer;

    public PackageServiceImpl(PackageRepository packageRepository, @Lazy ProductService productService, PackageBusinessRules packageBusinessRules, AuditAwareImpl auditAware, PackageCreatedProducer packageCreatedProducer) {
        this.packageRepository = packageRepository;
        this.productService = productService;
        this.packageBusinessRules = packageBusinessRules;
        this.auditAware = auditAware;
        this.packageCreatedProducer = packageCreatedProducer;
    }


    @Override
    public PackageResponse getOnePackage(UUID id) {
        log.info("Getting package with id: " + id);
        Package pack=packageRepository.findById(id).orElseThrow(
                ()->new BusinessException("Package with id: " + id + " not found"));

        PackageResponse packageResponse=PackageMapper.INSTANCE.createPackageResponseFromPackage(pack);
        return packageResponse;
    }


    @Override
    public GetListResponse<PackageResponse> gelAllPackages(PageInfo pageInfo) {
        return ListResponse.get(pageInfo,
                packageRepository,
                PackageMapper.INSTANCE::createPackageResponseFromPackage);
    }

    @Override
    public void createPackage(CreatePackageRequest createPackageRequest) {
        log.info("Creating package " + createPackageRequest.getPackageName());

        packageBusinessRules.checkIfPackageNameExists(createPackageRequest.getPackageName());
        Package newPackage = PackageMapper.INSTANCE.CreatePackageFromCreatePackageRequest(createPackageRequest);

        packageRepository.save(newPackage);

        PackageCreatedEvent event = new PackageCreatedEvent();
        event.setPackageName(createPackageRequest.getPackageName());
        event.setPackageType(createPackageRequest.getPackageType());
        event.setQuota(createPackageRequest.getQuota());
        event.setPrice(createPackageRequest.getPrice());
        event.setValidityPeriod(createPackageRequest.getValidityPeriod());
        packageCreatedProducer.sendMessage(event);
    }

    @Override
    public void updatePackage(UUID id, UpdatePackageRequest updatePackageRequest) {
        packageBusinessRules.checkIfPackageExists(id);
        log.info("Updating package with id: " + id);
        Package pack=packageRepository.findById(id).orElseThrow(
                ()->new BusinessException("Package with id: " + id + " not found"));
        log.info("Package found: " + pack);
        PackageMapper.INSTANCE.updatePackageFromRequest(updatePackageRequest, pack);
        packageRepository.save(pack);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        packageBusinessRules.checkIfPackageExists(id);
        log.info("Deleting package with id: " + id);
        packageRepository.softDelete(id, LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
    }




}
