package com.essoft.event.user;

import com.essoft.entities.EmailTemplateName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreatedEvent {
    private String username;
    private String email;
    private final EmailTemplateName emailTemplateName = EmailTemplateName.USER_CREATED;
}
