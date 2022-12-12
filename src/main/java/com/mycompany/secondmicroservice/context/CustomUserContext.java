package com.mycompany.secondmicroservice.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";

    private String correlationId;
    private String authToken;
    private String userId;

}
