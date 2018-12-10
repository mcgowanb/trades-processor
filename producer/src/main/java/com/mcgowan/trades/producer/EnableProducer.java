package com.mcgowan.trades.producer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.mcgowan.trades.producer.config.ProducerQueueConfig;

@Configuration
@Import(ProducerQueueConfig.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableProducer {
}
