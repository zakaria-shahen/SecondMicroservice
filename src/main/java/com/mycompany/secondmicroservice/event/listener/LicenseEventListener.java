package com.mycompany.secondmicroservice.event.listener;

import com.mycompany.secondmicroservice.event.listener.model.LicenseMessageEventModel;
import com.mycompany.secondmicroservice.service.InvokeLicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class LicenseEventListener {

    private final CacheManager cacheManager;

    @Bean
    public Consumer<LicenseMessageEventModel> createLicenseEventListener() {

        // return new Consumer<>() {
        //     @Override
        //     // TODO: why @CacheEvict not work?
        //     @CacheEvict(cacheNames = InvokeLicenseService.FALLBACK_METHOD, key="#model.getLicenseId()")
        //     public void accept(LicenseMessageEventModel model) {
        //         System.out.println("Message received from kafka:" + model);
        //     }
        // };

        return model -> {
            System.out.println("Message received from kafka:" + model);
            Cache cache = cacheManager.getCache(InvokeLicenseService.CACHE_LICENSE);
            if (cache == null) {
                return;
            }
            cache.evict(model.getLicenseId());

        };
    }

}
