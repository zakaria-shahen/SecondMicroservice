package com.mycompany.secondmicroservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configSecurity(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                // /test/
                .mvcMatchers("/invoke-license-service/**").authenticated()
                .anyRequest().permitAll()
        ;

        // Oauth2 Resource server - JWT
        httpSecurity.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        // Oauth2 Resource server - opaqueToken
        // httpSecurity.oauth2ResourceServer(OAuth2ResourceServerConfigurer::opaqueToken);

        // Oauth2 Client
        // add spring-boot-starter-oauth2-client to pom.xml
        // httpSecurity.oauth2Login();

        return  httpSecurity.build();
    }


}
