package com.parking.controller;

import com.parking.responses.DomainUserResponse;
import com.parking.services.RegistrarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrarController {
    private final RegistrarService registrarService;

    @PostMapping("/api/register/user")
    public DomainUserResponse registerUser() {
        return registrarService.generateAndReceiveUser();
    }
}
