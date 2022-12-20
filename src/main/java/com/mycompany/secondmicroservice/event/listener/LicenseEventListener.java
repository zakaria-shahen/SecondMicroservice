package com.mycompany.secondmicroservice.event.listener;

import com.mycompany.secondmicroservice.event.listener.model.LicenseMessageEventModel;
import com.mycompany.secondmicroservice.service.InvokeLicenseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class LicenseEventListener {

    private final Logger logger = LoggerFactory.getLogger(LicenseEventListener.class);

    @Bean
    public Consumer<LicenseMessageEventModel> createLicenseEventListener() {
        return new Consumer<>() {
            @Override
            @CacheEvict(cacheNames = InvokeLicenseService.CACHE_LICENSE, key="#model.getLicenseId()")
            public void accept(LicenseMessageEventModel model) {
                logger.debug("Message received from kafka: {}", model);
            }
        };
    }

}
