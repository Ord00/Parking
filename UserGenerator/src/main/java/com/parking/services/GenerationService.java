package com.parking.services;

import com.parking.grpc.GenerateUserRequest;
import com.parking.grpc.GenerationServiceGrpc;
import com.parking.grpc.UserResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GenerationService extends GenerationServiceGrpc.GenerationServiceImplBase {

    private Server server;
    private final AtomicInteger idCounter = new AtomicInteger(1);
    private final Random random = new Random();

    private final List<String> firstNames = Arrays.asList(
            "John", "Jane", "Alex", "Maria", "David", "Sarah", "Mike", "Emma",
            "Robert", "Lisa", "James", "Anna", "William", "Olivia", "Thomas", "Sophia"
    );

    private final List<String> lastNames = Arrays.asList(
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
            "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson"
    );

    private final List<String> userRoles = Arrays.asList(
            "USER", "ADMIN", "EMPLOYEE"
    );

    @PostConstruct
    public void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(this)
                .build()
                .start();

        System.out.println("=== UserGenerator gRPC Server Started ===");
        System.out.println("Port: " + port);
        System.out.println("Ready to generate users...");

        new Thread(() -> {
            try {
                server.awaitTermination();
            } catch (InterruptedException e) {
                System.err.println("gRPC server interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }).start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server...");
            if (server != null) {
                server.shutdown();
            }
        }));
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            server.shutdown();
            System.out.println("gRPC Server stopped!");
        }
    }

    @Override
    public void generateUser(GenerateUserRequest request,
                             StreamObserver<UserResponse> responseObserver) {

        int userId = idCounter.getAndIncrement();
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        String login = firstName.toLowerCase() + "." + lastName.toLowerCase() + userId;
        String userRole = userRoles.get(random.nextInt(userRoles.size()));

        UserResponse response = UserResponse.newBuilder()
                .setId(userId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setLogin(login)
                .setUserRole(userRole)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
