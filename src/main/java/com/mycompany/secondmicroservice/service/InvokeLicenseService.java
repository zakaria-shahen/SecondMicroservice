package com.mycompany.secondmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvokeLicenseService {

    private final DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    private String serviceId = "firstmicroservice";

    public String invoke() {

        List<ServiceInstance> instanceInfos = discoveryClient.getInstances(serviceId);

        if (! instanceInfos.isEmpty()) {

            String licenseServiceUrl = instanceInfos.get(0).getUri().toString();

            return restTemplate.exchange(
                    licenseServiceUrl,
                    HttpMethod.GET,
                    null,
                    String.class
            ).getBody();
        }

        return null;
    }
}
