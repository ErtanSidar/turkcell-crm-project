package com.turkcell.customerservice.services.mappers;


import com.turkcell.customerservice.entities.District;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.CreateDistrictRequest;
import com.turkcell.customerservice.services.dtos.requests.districtRequests.UpdateDistrictRequest;
import com.turkcell.customerservice.services.dtos.responses.districtResponses.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DistrictMapper {

    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    GetAllDistrictResponse getAllDistrictResponseFromDistrict(District district);

    GetDistrictResponse getDistrictResponseFromDistrict(District district);

    District districtFromCreateDistrictRequest(CreateDistrictRequest createDistrictRequest);

    CreatedDistrictResponse createdDistrictResponseFromDistrict(District district);

    District districtFromUpdateDistrictRequest(UpdateDistrictRequest updateDistrictRequest);

    UpdatedDistrictResponse updatedDistrictResponseFromDistrict(District district);
}
