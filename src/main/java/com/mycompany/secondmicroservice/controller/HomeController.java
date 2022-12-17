package com.mycompany.secondmicroservice.controller;

import com.mycompany.secondmicroservice.dto.LicenseDto;
import com.mycompany.secondmicroservice.service.InvokeLicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final InvokeLicenseService invokeLicenseService;

    @GetMapping
    public Map<String, Object> get() {

        return Map.of("message", "Hello, Second Service");
    }

    @GetMapping("invoke-license-service/{id}")
    public LicenseDto invoke(@PathVariable String id) {
        return invokeLicenseService.invoke(id);
    }

}
