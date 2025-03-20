package com.turkcell.customerservice.services.concretes;

import com.turkcell.customerservice.entities.Campaign;
import com.turkcell.customerservice.entities.Customer;
import com.turkcell.customerservice.entities.CustomerCampaign;
import com.turkcell.customerservice.entities.CustomerType;
import com.turkcell.customerservice.repositories.CampaignRepository;
import com.turkcell.customerservice.repositories.CustomerCampaignRepository;
import com.turkcell.customerservice.repositories.CustomerRepository;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.CreateCustomerRequest;
import com.turkcell.customerservice.services.dtos.requests.customerRequests.UpdateCustomerRequest;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.CreatedCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetAllCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.GetCustomerResponse;
import com.turkcell.customerservice.services.dtos.responses.customerResponses.UpdatedCustomerResponse;
import com.turkcell.customerservice.services.mappers.CustomerMapper;
import com.turkcell.customerservice.services.rules.CustomerBusinessRules;
import io.github.ertansidar.paging.PageInfo;
import io.github.ertansidar.response.GetListResponse;
import io.github.ertansidar.response.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;
    private final CampaignRepository campaignRepository;
    private final CustomerCampaignRepository customerCampaignRepository;


    @Override
    public GetListResponse<GetAllCustomerResponse> getAll(PageInfo pageInfo) {
        return ListResponse.get(pageInfo, customerRepository, CustomerMapper.INSTANCE::getAllCustomerResponseFromCustomer);
    }

    @Override
    public GetCustomerResponse findById(UUID id) {
        customerBusinessRules.checkCustomerIdExists(id);
        Customer customer = customerRepository.findById(id).get();
        return CustomerMapper.INSTANCE.getCustomerResponseFromCustomer(customer);
    }

    @Override
    public CreatedCustomerResponse add(CreateCustomerRequest request) throws Exception {
//        if (request.getCustomerType() == CustomerType.INDIVIDUAL) {
//            customerBusinessRules.checkIndividualCustomerVerifiedByMernis(
//                    request.getNationalityId(),
//                    request.getFirstName(),
//                    request.getLastName(),
//                    request.getBirthDate().getYear()
//            );
//        }
        customerBusinessRules.checkCustomerNationalityIdIsUnique(request.getNationalityId());
        Customer customer = CustomerMapper.INSTANCE.customerFromCreateCustomerRequest(request);
        customer.setCustomerNumber(generateCustomerNumber(request));
        Customer createdCustomer = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.createdCustomerResponseFromCustomer(createdCustomer);
    }

    @Override
    public UpdatedCustomerResponse update(UpdateCustomerRequest request, UUID id) {
        customerBusinessRules.checkCustomerIdExists(id);
        Customer customer = CustomerMapper.INSTANCE.customerFromUpdateCustomerRequest(request);
        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.updatedCustomerResponseFromCustomer(updatedCustomer);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        customerBusinessRules.checkCustomerIdExists(id);
        customerRepository.softDelete(id, LocalDateTime.now(), AuditAwareImpl.USER);
    }

    @Override
    public void addCustomerToCampaign(UUID customerId, UUID campaignId) {
        Customer customer = customerRepository.findById(customerId).get();
        Campaign campaign = campaignRepository.findById(campaignId).get();
        CustomerCampaign customerCampaign = new CustomerCampaign();
        customerCampaign.setCustomer(customer);
        customerCampaign.setCampaign(campaign);
        customerCampaign.setAssignedDate(LocalDate.now());
        customerCampaignRepository.save(customerCampaign);
    }

    public static String generateCustomerNumber(CreateCustomerRequest request) {
        StringBuilder customerNumber = new StringBuilder();
        if (request.getCustomerType() == CustomerType.INDIVIDUAL) {
            customerNumber.append("IND")
                    .append(request.getNationalityId().substring(request.getNationalityId().length() - 4))
                    .append(request.getFirstName().charAt(0))
                    .append(request.getLastName().charAt(0));
        } else if (request.getCustomerType() == CustomerType.CORPORATE) {
            customerNumber.append("CORP")
                    .append(request.getTaxNumber().substring(request.getTaxNumber().length() - 4))
                    .append(request.getCompanyName().charAt(0));
        }
        customerNumber.append(new Random().nextInt(10000));
        return customerNumber.toString();
    }
}
