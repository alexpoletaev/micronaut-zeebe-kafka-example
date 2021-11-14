package com.alexpoletaev.order.kafka;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.PublishMessageResponse;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@KafkaListener
public class PaymentListener {

    private static final String MESSAGE_NAME = "transaction-id-received";

    private final ZeebeClient zeebeClient;

    @Topic("payment.credit.card.charge.reply")
    ZeebeFuture<PublishMessageResponse> receiveTransactionId(@KafkaKey Integer cardNumber,
                                                             @NotNull @MessageHeader("correlationKey") UUID correlationKey,
                                                             @NotNull UUID transactionId) {
        return zeebeClient.newPublishMessageCommand()
                .messageName(MESSAGE_NAME)
                .correlationKey(correlationKey.toString())
                .variables(Map.of("correlationKey", correlationKey, "transactionId", transactionId))
                .send();
    }
}
