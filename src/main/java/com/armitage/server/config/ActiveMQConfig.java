package com.armitage.server.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;


@Configuration
@EnableJms
public class ActiveMQConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("activeMQQueue_request");
    }
}