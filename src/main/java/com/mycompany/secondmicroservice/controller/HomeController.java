package com.mycompany.secondmicroservice.controller;

import com.mycompany.secondmicroservice.dto.LicenseDto;
import com.mycompany.secondmicroservice.service.InvokeLicenseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final InvokeLicenseService invokeLicenseService;

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public Map<String, Object> get() {

        return Map.of("message", "Hello, Second Service");
    }

    @GetMapping("invoke-license-service/{id}")
    public LicenseDto invoke(@PathVariable String id) {

        logger.debug("call invoke controller");
        return invokeLicenseService.invoke(id);
    }

    @PostMapping("/test")
    public String test() {
        return "Good";
    }

}
