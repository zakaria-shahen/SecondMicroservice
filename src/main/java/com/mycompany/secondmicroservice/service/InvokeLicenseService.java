package com.mycompany.secondmicroservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("firstmicroservice")
public interface InvokeLicenseService {

    @GetMapping
    String invoke();

}
