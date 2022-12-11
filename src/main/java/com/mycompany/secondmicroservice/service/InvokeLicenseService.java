package com.mycompany.secondmicroservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("firstmicroservice")
public interface InvokeLicenseService {


    @CircuitBreaker(name = "firstMicroservice", fallbackMethod = "firstMicroserviceFallback")
    @GetMapping
    String invoke();

    default String firstMicroserviceFallback(Throwable throwable) {
        return "firstMicroservice: Not available";
    }
}
