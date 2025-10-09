package com.parking.responses;

public record UserResponse(Integer id,
                           String lastName,
                           String firstName,
                           String login,
                           String userRole) {
}
