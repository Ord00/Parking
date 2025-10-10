package com.parking.responses;

public record DomainUserResponse(
        Integer id,
        String lastName,
        String firstName,
        String login,
        String userRole
) {}
