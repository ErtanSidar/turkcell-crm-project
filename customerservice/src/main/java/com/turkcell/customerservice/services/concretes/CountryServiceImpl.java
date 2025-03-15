package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Country;
import com.turkcell.customerservice.repositories.CountryRepository;
import com.turkcell.customerservice.services.abstracts.CountryService;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.CreateCountryRequest;
import com.turkcell.customerservice.services.dtos.requests.countryRequests.UpdateCountryRequest;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.CreatedCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.GetAllCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.GetCountryResponse;
import com.turkcell.customerservice.services.dtos.responses.countryResponses.UpdatedCountryResponse;
import com.turkcell.customerservice.services.mappers.CountryMapper;
import com.turkcell.customerservice.services.rules.CountryBusinessRules;
import io.github.ertansidar.exception.type.BusinessException;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private CountryBusinessRules countryBusinessRules;

    @Override
    public GetListResponse<GetAllCountryResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, countryRepository, CountryMapper.INSTANCE::getAllCountryResponseFromCountry);
    }

    @Override
    public GetCountryResponse getById(UUID id) {
        countryBusinessRules.checkCountryIdExists(id);
        Country foundedCountry = countryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Country not found"));
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
        countryBusinessRules.checkCountryIdExists(id);
        Country foundCountry = countryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Country not found"));

        Country country = CountryMapper.INSTANCE.countryFromUpdateCountryRequest(request);
        country.setId(foundCountry.getId());
        Country updatedCountry = countryRepository.save(country);

        return CountryMapper.INSTANCE.updatedCountryResponseFromCountry(updatedCountry);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        countryBusinessRules.checkCountryIdExists(id);
        countryRepository.softDelete(id, LocalDateTime.now(), AuditAwareImpl.USER);
    }
}
