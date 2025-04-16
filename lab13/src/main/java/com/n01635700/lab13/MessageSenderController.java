package com.n01635700.lab13;

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


//    @PostMapping("/send")
//    public String sendMessage(@RequestBody String message) {
//        jmsTemplate.convertAndSend("lab-queue", message);
//        return "Message sent: " + message;
//    }
}
