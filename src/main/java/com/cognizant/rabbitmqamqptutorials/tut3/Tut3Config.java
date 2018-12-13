package com.cognizant.rabbitmqamqptutorials.tut3;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut3", "pub-sub", "publish-subscribe"})
@Configuration
public class Tut3Config {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            System.out.println("one");
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            System.out.println("dtwo");
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanout,
                                Queue autoDeleteQueue1) {
            System.out.println("three");
            System.out.println(autoDeleteQueue1.getName());
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout,
                                Queue autoDeleteQueue2) {
            System.out.println("four");
            System.out.println(autoDeleteQueue2.getName());
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public Tut3Receiver receiver() {
            System.out.println("dfvdfvd vd");
            return new Tut3Receiver();
        }
    }

    @Profile("sender")
    @Bean
    public Tut3Sender sender() {
        return new Tut3Sender();
    }
}