package com.mycompany.secondmicroservice;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class ConfigFeignSecurity {

    @Bean
    public RequestInterceptor requestInterceptorSecurity() {
        return  requestTemplate -> {
            String token = getAuthToken();

            if (token != null) {
                requestTemplate.header(AUTHORIZATION, "Bearer " + token);
            }
        };
    }

    public static String getAuthToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
            return null;
        }

        AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
        return token.getTokenValue();
    }
}
