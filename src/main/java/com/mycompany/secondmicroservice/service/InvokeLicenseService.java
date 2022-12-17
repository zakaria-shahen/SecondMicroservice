package com.mycompany.secondmicroservice.service;

import com.mycompany.secondmicroservice.dto.LicenseDto;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(InvokeLicenseService.SERVICE_NAME)
public interface InvokeLicenseService {

     String SERVICE_NAME = "firstMicroservice";
     String FALLBACK_METHOD = "firstMicroserviceFallback";
     String CACHE_LICENSE = "licenses";

     // default: type = Bulkhead.Type.SEMAPHORE
    @Bulkhead(name = SERVICE_NAME, fallbackMethod = FALLBACK_METHOD, type = Bulkhead.Type.SEMAPHORE)
    @RateLimiter(name = SERVICE_NAME, fallbackMethod = FALLBACK_METHOD)
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = FALLBACK_METHOD)
    @Retry(name = SERVICE_NAME, fallbackMethod = FALLBACK_METHOD)
    @GetMapping("/license/{id}")
    @Cacheable(cacheNames = CACHE_LICENSE, key = "#id")
    LicenseDto invoke(@PathVariable String id);

    default LicenseDto firstMicroserviceFallback(Throwable throwable) {
        return LicenseDto.builder().build();
    }
}
