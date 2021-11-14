package com.alexpoletaev.order.controller;

import io.camunda.zeebe.client.ZeebeClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Controller("/process")
public class ProcessController {

    private final ZeebeClient zeebeClient;

    @Post("/{processId}/start")
    public CompletableFuture<HttpResponse<?>> create(@PathVariable String processId,
                                                     @Body Map<String, Object> variables) {
        return CompletableFuture.supplyAsync(() -> zeebeClient.newCreateInstanceCommand()
                    .bpmnProcessId(processId)
                    .latestVersion()
                    .variables(variables)
                    .send())
                .thenApply(v -> HttpResponse.ok());
    }
}
