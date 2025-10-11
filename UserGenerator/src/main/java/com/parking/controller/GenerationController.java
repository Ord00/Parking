package com.parking.controller;

import com.parking.grpc.GenerateUserRequest;
import com.parking.grpc.UserResponse;
import com.parking.services.GenerationService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GenerationController {

    private final GenerationService generationService;

    @PostMapping("/gen/user")
    public void generateUser(GenerateUserRequest request,
                             StreamObserver<UserResponse> responseObserver) {

        UserResponse response = generationService.generateUser();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
