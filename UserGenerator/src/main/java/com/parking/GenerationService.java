package com.parking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public class GenerationService {
    @PostMapping("/gen/user")
    public ResponseEntity<?> generateUser() {

        return ResponseEntity.ok().build();
    }

}
