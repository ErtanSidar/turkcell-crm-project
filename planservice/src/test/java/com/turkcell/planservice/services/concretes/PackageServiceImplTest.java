package com.turkcell.planservice.services.concretes;

import com.turkcell.planservice.dtos.packagedtos.requests.CreatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.requests.UpdatePackageRequest;
import com.turkcell.planservice.dtos.packagedtos.responses.PackageResponse;
import com.turkcell.planservice.entities.Package;
import com.turkcell.planservice.kafka.PackageCreatedProducer;
import com.turkcell.planservice.kafka.PlanCreatedProducer;
import com.turkcell.planservice.repositories.PackageRepository;
import com.turkcell.planservice.rules.PackageBusinessRules;
import com.turkcell.planservice.services.abstracts.PackageService;
import com.turkcell.planservice.services.abstracts.ProductService;
import io.github.ertansidar.audit.AuditAwareImpl;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PackageServiceImplTest {

    @Mock
    private PackageRepository packageRepository;

    @Mock
    private ProductService productService;

    @Mock
    private PackageBusinessRules packageBusinessRules;

    @Mock
    private AuditAwareImpl auditAware;

    private PackageService packageService;


    private PackageCreatedProducer packageCreatedProducer;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        packageService = new PackageServiceImpl(
                packageRepository,
                productService,
                packageBusinessRules,
                auditAware,
                packageCreatedProducer
        );
    }

    @Test
    void testGetOnePackage_WhenPackageExists_ShouldReturnPackageResponse() {
        UUID packageId = UUID.randomUUID();
        Package packageEntity = new Package();
        packageEntity.setId(packageId);
        packageEntity.setPackageName("test-package");

        when(packageRepository.findById(packageId))
                .thenReturn(Optional.of(packageEntity));

        PackageResponse packageResponse = packageService.getOnePackage(packageId);
        assertNotNull(packageResponse);
        verify(packageRepository).findById(packageId);
    }

    @Test
    void testGetOnePackage_WhenPackageNotExists_ShouldThrowBusinessException() {
        UUID packageId = UUID.randomUUID();
        Package packageEntity = new Package();
        packageEntity.setId(packageId);

        when(packageRepository.findById(packageId)).thenReturn(Optional.empty());


        assertThrows(BusinessException.class, () -> {
            packageService.getOnePackage(packageId);
        });
    }

    @Test
    void testGetAllPackages_ShouldReturnGetListResponse() {

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(0);
        pageInfo.setSize(10);

        Package package1 = new Package();
        package1.setId(UUID.randomUUID());
        package1.setPackageName("test-package");
        Package package2 = new Package();
        package2.setId(UUID.randomUUID());
        package2.setPackageName("test-package2");

        List<Package>packages= Arrays.asList(package1,package2);

        Page<Package> packagePage = new PageImpl<>(packages,
                PageRequest.of(pageInfo.getPage(), pageInfo.getSize()),
                packages.size()
        );

        when(packageRepository
                .findAll(any(PageRequest.class)))
                .thenReturn(packagePage);

        GetListResponse<PackageResponse> response =
                packageService.gelAllPackages(pageInfo);

        assertNotNull(response);
        verify(packageRepository).findAll(any(PageRequest.class));

    }

    @Test
    void testCreatePackage_ShouldSaveNewPackage() {
        CreatePackageRequest request = new CreatePackageRequest();
        request.setPackageName("new-package");


        doNothing()
                .when(packageBusinessRules)
                .checkIfPackageNameExists(request.getPackageName());

        packageService.createPackage(request);

        verify(packageBusinessRules).checkIfPackageNameExists(request.getPackageName());
        verify(packageRepository).save(any(Package.class));


    }

    @Test
    void createPackage_WithExistingPackageName_ShouldThrowException() {
        // Arrange
        CreatePackageRequest request = new CreatePackageRequest();
        request.setPackageName("Existing Package");

        doThrow(new RuntimeException("A package with this name already exists"))
                .when(packageBusinessRules)
                .checkIfPackageNameExists(request.getPackageName());

        // Act & Assert
        assertThrows(RuntimeException.class,
                () -> packageService.createPackage(request),
                "A package with this name already exists"
        );
    }

    @Test
    void testUpdatePackage_WhenPackageExists_ShouldUpdateAndSave() {

        UUID packageId = UUID.randomUUID();
        UpdatePackageRequest updatePackageRequest = new UpdatePackageRequest();
        updatePackageRequest.setPackageName("Updated package");

        Package existingPackage = new Package();
        existingPackage.setId(packageId);


        doNothing()
                .when(packageBusinessRules)
                .checkIfPackageExists(packageId);
        when(packageRepository.findById(packageId))
                .thenReturn(Optional.of(existingPackage));

        packageService.updatePackage(packageId, updatePackageRequest);

        verify(packageBusinessRules).checkIfPackageExists(packageId);
        verify(packageRepository).save(existingPackage);
        assertEquals(updatePackageRequest.getPackageName(), existingPackage.getPackageName());

    }

    @Test
    void testUpdatePackage_WhenPackageNotExists_ShouldThrowBusinessException() {
        // Arrange
        UUID packageId = UUID.randomUUID();

        UpdatePackageRequest updateRequest = new UpdatePackageRequest();

        doNothing()
                .when(packageBusinessRules)
                .checkIfPackageExists(packageId);


        when(packageRepository.findById(packageId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BusinessException.class, () -> {
            packageService.updatePackage(packageId, updateRequest);
        });
    }

    @Test
    void testDeleteById_ShouldCallSoftDelete() {

        UUID packageId = UUID.randomUUID();
        Package pack = new Package();
        pack.setId(packageId);

        doNothing().when(packageBusinessRules)
                        .checkIfPackageExists(packageId);

        // Act
        packageService.delete(packageId);

        // Assert
        verify(packageBusinessRules).checkIfPackageExists(packageId);
        verify(packageRepository).softDelete(
                eq(packageId),
                any(LocalDateTime.class),
                anyString()
        );
    }
}