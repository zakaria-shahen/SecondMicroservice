package com.mycompany.secondmicroservice.event.listener;

import com.mycompany.secondmicroservice.event.listener.model.LicenseMessageEventModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class LicenseEventListener {

    @Bean
    public Consumer<LicenseMessageEventModel> createLicenseEventListener() {
        return licenseMessageEventModel -> System.out.println("Message received from kafka:" + licenseMessageEventModel);
    }

}
