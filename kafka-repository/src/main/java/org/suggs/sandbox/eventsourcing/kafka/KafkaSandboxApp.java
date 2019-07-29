package org.suggs.sandbox.eventsourcing.kafka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.springframework.boot.WebApplicationType.SERVLET;

@SpringBootApplication
public class KafkaSandboxApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(KafkaSandboxApp.class)
                .web(SERVLET)
                .run(args);
    }
}
