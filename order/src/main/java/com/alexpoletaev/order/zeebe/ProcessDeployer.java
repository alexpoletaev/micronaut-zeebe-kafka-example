package com.alexpoletaev.order.zeebe;

import io.camunda.zeebe.client.ZeebeClient;
import io.micronaut.context.annotation.Context;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Context
public class ProcessDeployer {

    private final ZeebeClient zeebeClient;

    @PostConstruct
    public void deploy() {
        zeebeClient.newDeployCommand()
                .addResourceFromClasspath("bpmn/order-payment-example.bpmn")
                .send()
                .join();
    }
}
