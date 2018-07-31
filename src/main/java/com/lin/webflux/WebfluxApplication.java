package com.lin.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args) {
       SpringApplication.run(WebfluxApplication.class, args);
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public UnicastProcessor<String> eventPublisher() {
        return UnicastProcessor.create();
    }

    @Bean
    public Flux<String> events(UnicastProcessor<String> eventPublisher) {
        return eventPublisher
                .replay(1) // 历史数据
                .autoConnect();
    }
}
