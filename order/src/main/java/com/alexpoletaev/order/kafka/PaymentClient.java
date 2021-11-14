package com.alexpoletaev.order.kafka;

import com.alexpoletaev.order.dto.TransactionContext;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@KafkaClient
public interface PaymentClient {

    @Topic("payment.credit.card.charge.request")
    CompletableFuture<Void> charge(@KafkaKey Integer cardNumber,
                                   @NotNull @MessageHeader("correlationKey") UUID correlationKey,
                                   @NotNull @Valid TransactionContext transactionContext);
}
