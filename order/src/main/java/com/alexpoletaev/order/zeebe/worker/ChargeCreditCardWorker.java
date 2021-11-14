package com.alexpoletaev.order.zeebe.worker;

import com.alexpoletaev.order.dto.TransactionContext;
import com.alexpoletaev.order.exception.RequestProcessingException;
import com.alexpoletaev.order.kafka.PaymentClient;
import io.camunda.zeebe.client.ZeebeClient;
import io.micronaut.context.annotation.Context;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Context
public class ChargeCreditCardWorker {

    public static final String JOB = "charge-credit-card";
    public static final String VAR_CARD_NUMBER = "cardNumber";
    public static final String VAR_AMOUNT = "amount";
    public static final String VAR_KEY = "correlationKey";

    private final ZeebeClient zeebeClient;

    private final PaymentClient paymentClient;

    @PostConstruct
    public void charge() {
        zeebeClient.newWorker().jobType(JOB).handler((jobClient, activatedJob) -> {
            Map<String, Object> variables = activatedJob.getVariablesAsMap();
            Integer cardNumber = (Integer) variables.get(VAR_CARD_NUMBER);
            BigDecimal amount = new BigDecimal((String) variables.get(VAR_AMOUNT));
            UUID key = UUID.randomUUID();
            paymentClient.charge(cardNumber, key, TransactionContext.builder().cardNumber(cardNumber).amount(amount).build())
                    .thenAccept(v -> jobClient.newCompleteCommand(activatedJob.getKey())
                            .variables(Map.of(VAR_KEY, key))
                            .send()
                    .exceptionally(e -> {
                        log.error(e.getMessage());
                        throw new RequestProcessingException(e.getMessage());
                    }));
        }).open();
    }
}
