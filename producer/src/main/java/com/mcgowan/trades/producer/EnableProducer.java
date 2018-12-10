package com.mcgowan.trades.producer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Named;

import org.springframework.context.annotation.Import;

import com.mcgowan.trades.producer.config.ProducerQueueConfig;

@Named
@Import(ProducerQueueConfig.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableProducer {
}
