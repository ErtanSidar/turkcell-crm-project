package com.essoft.entities;

public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account"),
    SHIPPING_UPDATE("shipping_update"),
    PAYMENT_NOTIFICATION("payment_notification"),
    CUSTOMER_SUPPORT("customer_support"),
    USER_CREATED("user_created");


    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
