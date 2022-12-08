package com.mycompany.secondmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class InvokeLicenseService {

    private final RestTemplate restTemplate;

    private String serviceId = "firstmicroservice";

    public String invoke() {
        String licenseServiceUrl = "http://" + serviceId + "/";

        return restTemplate.exchange(
                licenseServiceUrl,
                HttpMethod.GET,
                null,
                String.class
        ).getBody();
    }

}
