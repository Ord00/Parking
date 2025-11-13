package com.parking.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ContainerInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        String hostname = System.getenv().getOrDefault("HOSTNAME", "unknown");
        builder.withDetail("container", hostname)
                .withDetail("service", "user-generator")
                .withDetail("timestamp", Instant.now());
    }
}
