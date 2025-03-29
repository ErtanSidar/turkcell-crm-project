package com.turkcell.customersupportservice.coreBusiness;

import io.github.ertansidar.exception.type.BusinessException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.UUID;

public class Utility {
    public static void checkIdIsEmpty(UUID id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("Id cannot be null");
        }
    }
}
