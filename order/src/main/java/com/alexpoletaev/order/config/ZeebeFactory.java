package com.alexpoletaev.order.config;

import io.camunda.zeebe.client.ZeebeClient;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Factory
public class ZeebeFactory {

    private final ZeebeConfig config;

    @Singleton
    public ZeebeClient zeebeClient() {
        return ZeebeClient.newClientBuilder()
                .gatewayAddress(config.getBroker())
                .usePlaintext()
                .build();
    }
}
