package com.turkcell.notificationservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "turkcell.notificationservice")
@Configuration
@Getter
@Setter
public class NotificationProperties {

    private Email email;

    private Client client;

    public static record Email(
            String username,
            String password,
            String host,
            int port,
            String from
    ){}

    public static record Client(
            String host
    ){}
}
