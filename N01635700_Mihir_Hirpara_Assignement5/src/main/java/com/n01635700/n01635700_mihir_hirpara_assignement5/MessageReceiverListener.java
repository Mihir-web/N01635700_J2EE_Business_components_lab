package com.n01635700.n01635700_mihir_hirpara_assignement5;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverListener {

    @JmsListener(destination = "lab-queue")
    public void receiveMessage(MessageParser parser) {
        System.out.println("Received structured message in listener:");
        System.out.println("Name: " + parser.getName());
        System.out.println("Message: " + parser.getMessage());
    }
}
