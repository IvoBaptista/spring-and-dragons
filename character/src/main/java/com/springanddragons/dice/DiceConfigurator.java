package com.springanddragons.dice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiceConfigurator {
    @Bean
    public Dice d3() {
        return new Dice(3);
    }
    @Bean
    public Dice d4() {
        return new Dice(4);
    }
    @Bean
    public Dice d6() {
        return new Dice(6);
    }
    @Bean
    public Dice d8() {
        return new Dice(8);
    }
    @Bean
    public Dice d10() {
        return new Dice(10);
    }
    @Bean
    public Dice d12() {
        return new Dice(12);
    }
    @Bean
    public Dice d20() {
        return new Dice(20);
    }
}
