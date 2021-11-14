package com.alexpoletaev.payment.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;

import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@KafkaClient
public interface CardChargeReplyClient {

    @Topic("payment.credit.card.charge.reply")
    CompletableFuture<Void> sendReply(@KafkaKey Integer cardNumber,
                                      @NotNull @MessageHeader("correlationKey") UUID correlationKey,
                                      @NotNull UUID transactionId);
}
