package com.turkcell.customerservice.adapters;

public interface CustomerCheckService {
    public boolean checkIfRealPerson(String nationalityId,
                                     String firstName,
                                     String lastName,
                                     int birthDate) throws Exception;
}
