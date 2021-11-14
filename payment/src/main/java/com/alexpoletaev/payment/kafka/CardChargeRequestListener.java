package com.alexpoletaev.payment.kafka;

import com.alexpoletaev.payment.dto.TransactionContext;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@KafkaListener
public class CardChargeRequestListener {

    private final CardChargeReplyClient replyClient;

    @Topic("payment.credit.card.charge.request")
    CompletableFuture<Void> chargeCreditCard(@KafkaKey Integer cardNumber,
                                             @NotNull @MessageHeader("correlationKey") UUID correlationKey,
                                             @NotNull @Valid TransactionContext transactionContext) {
        log.info("Emulate charging for card number: {}", transactionContext.getCardNumber());

        return replyClient.sendReply(cardNumber, correlationKey, UUID.randomUUID());
    }
}
