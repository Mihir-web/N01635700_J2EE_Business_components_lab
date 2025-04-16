package com.n01635700.lab13;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverListener {
    @JmsListener(destination = "lab-queue")
    public void receiveMessage(String message) {
        System.out.println("Received message in listener: " + message);
    }
}
