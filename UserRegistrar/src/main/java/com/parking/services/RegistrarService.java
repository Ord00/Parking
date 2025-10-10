package com.parking.services;

import com.parking.grpc.GenerationServiceGrpc;
import com.parking.grpc.GenerateUserRequest;
import com.parking.responses.DomainUserResponse;
import com.parking.grpc.UserResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class RegistrarService {

    private final GenerationServiceGrpc.GenerationServiceBlockingStub blockingStub;

    public RegistrarService(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.blockingStub = GenerationServiceGrpc.newBlockingStub(channel);
    }

    public DomainUserResponse generateAndReceiveUser() {
        GenerateUserRequest request = GenerateUserRequest.newBuilder().build();

        UserResponse grpcResponse = blockingStub.generateUser(request);

        return new DomainUserResponse(
                grpcResponse.getId(),
                grpcResponse.getLastName(),
                grpcResponse.getFirstName(),
                grpcResponse.getLogin(),
                grpcResponse.getUserRole()
        );
    }
}
