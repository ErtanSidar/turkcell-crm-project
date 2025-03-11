package com.turkcell.customerservice.adapters;

import com.turkcell.customerservice.outservices.mernis.LWAKPSPublicSoap;
import org.springframework.stereotype.Service;

@Service
public class MernisServiceAdapter implements CustomerCheckService{
    @Override
    public boolean checkIfRealPerson(String nationalityId,
                                     String firstName,
                                     String lastName,
                                     int birthDate) throws Exception {
        LWAKPSPublicSoap client = new LWAKPSPublicSoap();
        return client.TCKimlikNoDogrula(
                Long.parseLong(nationalityId),
                firstName,
                lastName,
                birthDate);
    }
}
