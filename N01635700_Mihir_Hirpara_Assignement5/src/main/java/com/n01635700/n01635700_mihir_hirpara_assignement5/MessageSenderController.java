package com.n01635700.n01635700_mihir_hirpara_assignement5;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MessageSenderController {
    private final JmsTemplate jmsTemplate;

    public MessageSenderController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        jmsTemplate.convertAndSend("demo-queue", message);
        return "Message sent: " + message;
    }

    @PostMapping("/send")
    public String sendStructuredMessage(@RequestBody MessageParser parser) {
        jmsTemplate.convertAndSend("lab-queue", parser);
        return "Structured message sent: " + parser.getMessage();
    }
}
