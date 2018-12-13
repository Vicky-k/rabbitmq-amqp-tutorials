/* See the file "LICENSE" for the full license governing this code. */
package com.cognizant.rabbitmqamqptutorials;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!show_usage_message")
@Component
public class RabbitCommandLineRunner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;

    private final int duration;

    RabbitCommandLineRunner(final ConfigurableApplicationContext context,
                            @Value("${tutorial.client.duration}") final int duration,
                            @Value("${spring.profiles.active}") final String profileName) {
        this.context = context;


        if("tut2, work-queues,sender".equals(profileName))    {
            this.duration = 10000;
        }   else if("tut2, work-queues,receiver".equals(profileName))   {
            this.duration = 10000;
        }   else    {
            this.duration = 10000;
        }
    }


    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Ready ... running for " + duration + "ms");
        Thread.sleep(duration);
        //context.close();
    }
}