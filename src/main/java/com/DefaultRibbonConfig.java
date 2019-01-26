package com;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DefaultRibbonConfig {

    @Bean
    public IRule ribbonRule() {
        //return new BestAvailableRule();
        return new AvailabilityFilteringRule();
    }

    @Bean
    public IPing ribbonPing() {
        return new PingUrl();
    }

}