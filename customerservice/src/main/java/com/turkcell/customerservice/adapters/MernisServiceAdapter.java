package com.turkcell.customerservice.adapters;

import com.turkcell.customerservice.outservices.mernis.IMGKPSPublicSoap;
import org.springframework.stereotype.Service;

@Service
public class MernisServiceAdapter implements CustomerCheckService {
    @Override
    public boolean checkIfRealPerson(String nationalityId,
                                     String firstName,
                                     String lastName,
                                     int birthDate) throws Exception {
        IMGKPSPublicSoap client = new IMGKPSPublicSoap();
        return client.TCKimlikNoDogrula(
                Long.parseLong(nationalityId),
                firstName,
                lastName,
                birthDate);
    }
}
