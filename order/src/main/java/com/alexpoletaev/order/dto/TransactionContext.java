package com.alexpoletaev.order.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@Introspected
public class TransactionContext {

    @NotNull
    private Integer cardNumber;

    @NotNull
    private BigDecimal amount;

    @Nullable
    private UUID transactionId;
}
