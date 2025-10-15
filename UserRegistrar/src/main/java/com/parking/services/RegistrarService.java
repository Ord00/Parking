package com.parking.services;

import com.parking.grpc.GenerationServiceGrpc;
import com.parking.grpc.GenerateUserRequest;
import com.parking.responses.DomainUserResponse;
import com.parking.grpc.UserResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class RegistrarService {

    private ManagedChannel channel;
    private GenerationServiceGrpc.GenerationServiceBlockingStub blockingStub;

    @PostConstruct
    public void init() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        this.blockingStub = GenerationServiceGrpc.newBlockingStub(channel);
    }

    @PreDestroy
    public void shutdown() {
        if (channel != null) {
            channel.shutdown();
        }
    }

    public DomainUserResponse generateAndReceiveUser() {
        try {
            GenerateUserRequest request = GenerateUserRequest.newBuilder().build();
            UserResponse grpcResponse = blockingStub.generateUser(request);

            DomainUserResponse user = new DomainUserResponse(
                    grpcResponse.getId(),
                    grpcResponse.getLastName(),
                    grpcResponse.getFirstName(),
                    grpcResponse.getLogin(),
                    grpcResponse.getUserRole()
            );

            System.out.println("Received user: " + user.firstName() + " " + user.lastName());
            return user;

        } catch (Exception e) {
            System.err.println("gRPC Error: " + e.getMessage());
            throw new RuntimeException("Failed to generate user via gRPC", e);
        }
    }
}
