package com.parking.services;

import com.parking.grpc.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GenerationService {

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
            "USER", "ADMIN", "MODERATOR", "VIEWER", "EDITOR"
    );

    @PostMapping("/gen/user")
    public UserResponse generateUser() {

        int userId = idCounter.getAndIncrement();
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        String login = firstName.toLowerCase() + "." + lastName.toLowerCase() + userId;
        String userRole = userRoles.get(random.nextInt(userRoles.size()));

        return UserResponse.newBuilder()
                .setId(userId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setLogin(login)
                .setUserRole(userRole)
                .build();
    }

}
