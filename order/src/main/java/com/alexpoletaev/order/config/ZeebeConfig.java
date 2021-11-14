package com.alexpoletaev.order.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("zeebe")
public class ZeebeConfig {

    private boolean enabled;

    private String broker;
}
