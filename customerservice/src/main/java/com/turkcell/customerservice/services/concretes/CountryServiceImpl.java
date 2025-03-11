package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Country;
import com.turkcell.customerservice.repositories.CountryRepository;
import com.turkcell.customerservice.services.abstracts.CountryService;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.CreateCountryRequest;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.UpdateCountryRequest;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.*;
import com.turkcell.customerservice.services.mappers.CountryMapper;
import com.turkcell.customerservice.services.rules.CountryBusinessRules;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private CountryBusinessRules countryBusinessRules;

    @Override
    public GetListResponse<GetAllCountryResponse> getAll(PageInfo pageInfo) {
        GetListResponse<GetAllCountryResponse> response = new GetListResponse<>();
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Country> countries = countryRepository.findAllIfDeletedDateIsNull(pageable);
        response.setItems(countries.stream()
                .map(CountryMapper.INSTANCE::getAllCountryResponseFromCountry).collect(Collectors.toList()));
        response.setTotalElements(countries.getTotalElements());
        response.setTotalPage(countries.getTotalPages());
        response.setSize(countries.getSize());
        response.setHasNext(countries.hasNext());
        response.setHasPrevious(countries.hasPrevious());
        return response;
    }

    @Override
    public GetCountryResponse getById(UUID id) {
        countryBusinessRules.checkCountryExists(id);
        countryBusinessRules.checkCountryIsDeleted(id);
        Country foundedCountry = countryRepository.findById(id).get();
        return CountryMapper.INSTANCE.getCountryResponseFromCountry(foundedCountry);
    }

    @Override
    public CreatedCountryResponse add(CreateCountryRequest request) {
        countryBusinessRules.checkCountryNameIsUnique(request.getName());
        Country country = CountryMapper.INSTANCE.countryFromCreateCountryRequest(request);
        Country createdCountry = countryRepository.save(country);
        return CountryMapper.INSTANCE.createdCountryResponseFromCountry(createdCountry);
    }

    @Override
    public UpdatedCountryResponse update(UpdateCountryRequest request, UUID id) {
        countryBusinessRules.checkCountryExists(id);
        countryBusinessRules.checkCountryIsDeleted(id);
        countryBusinessRules.checkCountryNameIsUnique(request.getName());
        Country foundCountry = countryRepository.findById(id).get();

        Country country = CountryMapper.INSTANCE.countryFromUpdateCountryRequest(request);
        country.setId(foundCountry.getId());
        country.setCreatedDate(foundCountry.getCreatedDate());
        country.setUpdatedDate(foundCountry.getUpdatedDate());
        Country updatedCountry = countryRepository.save(country);

        UpdatedCountryResponse updatedCountryResponse = CountryMapper.INSTANCE.updatedCountryResponseFromCountry(updatedCountry);
        return updatedCountryResponse;
    }

    @Override
    public DeletedCountryResponse delete(UUID id) {
        countryBusinessRules.checkCountryExists(id);
        countryBusinessRules.checkCountryIsDeleted(id);
        Country foundCountry = countryRepository.findById(id).get();
        foundCountry.setDeletedDate(LocalDateTime.now());
        Country deletedCountry = countryRepository.save(foundCountry);
        return CountryMapper.INSTANCE.deletedCountryResponseFromCountry(deletedCountry);
    }
}
