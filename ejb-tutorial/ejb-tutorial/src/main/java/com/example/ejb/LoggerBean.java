package com.example.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
public class LoggerBean {
    private static final Logger logger = Logger.getLogger(LoggerBean.class.getName());

    @PostConstruct
    public void init() {
        logger.info("LoggerBean initialized (PostConstruct)");
    }

    public void logAction(String action) {
        logger.info("Action logged: " + action);
    }

    @PreDestroy
    public void cleanup() {
        logger.info("LoggerBean about to be destroyed (PreDestroy)");
    }
}