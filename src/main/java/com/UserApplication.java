package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@RestController
@RibbonClient(name = "hello", configuration = DefaultRibbonConfig.class)
public class UserApplication {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @RequestMapping("/hi")
    public String hi() {
        String greeting = webClientBuilder.build().get().uri("http://hello").retrieve().bodyToMono(String.class).block();
        return String.format("%s!", greeting);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}