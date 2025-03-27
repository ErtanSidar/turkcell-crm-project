package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.District;
import com.turkcell.customerservice.repositories.DistrictRepository;
import com.turkcell.customerservice.services.abstracts.DistrictService;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.turkcell.customerservice.services.dtos.responses.districtResponses.*;
import com.turkcell.customerservice.services.mappers.DistrictMapper;
import com.turkcell.customerservice.services.rules.DistrictBusinessRules;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private DistrictRepository districtRepository;
    private DistrictBusinessRules districtBusinessRules;
    private AuditAwareImpl auditAware;

    @Override
    public GetListResponse<GetAllDistrictResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, districtRepository, DistrictMapper.INSTANCE::getAllDistrictResponseFromDistrict);
    }

    @Override
    public GetDistrictResponse getById(UUID id) {
        districtBusinessRules.checkDistrictIdExists(id);
        District foundDistrict = districtRepository.findById(id).get();
        return DistrictMapper.INSTANCE.getDistrictResponseFromDistrict(foundDistrict);
    }

    @Override
    public CreatedDistrictResponse add(CreateDistrictRequest request) {
        districtBusinessRules.checkDistrictNameIsUnique(request.getName());
        District district = DistrictMapper.INSTANCE.districtFromCreateDistrictRequest(request);
        District createdDistrict = districtRepository.save(district);
        return DistrictMapper.INSTANCE.createdDistrictResponseFromDistrict(createdDistrict);
    }

    @Override
    public UpdatedDistrictResponse update(UpdateDistrictRequest request, UUID id) {
        districtBusinessRules.checkDistrictIdExists(id);
        districtBusinessRules.checkDistrictNameIsUnique(request.getName());
        District foundDistrict = districtRepository.findById(id).get();
        District district = DistrictMapper.INSTANCE.districtFromUpdateDistrictRequest(request);
        district.setId(foundDistrict.getId());
        District updatedDistrict = districtRepository.save(district);
        return DistrictMapper.INSTANCE.updatedDistrictResponseFromDistrict(updatedDistrict);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        districtBusinessRules.checkDistrictIdExists(id);
        districtRepository.softDelete(id, LocalDateTime.now(), auditAware.getCurrentAuditor().toString());
    }
}
