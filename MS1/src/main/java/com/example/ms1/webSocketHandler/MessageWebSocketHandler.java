package com.example.ms1.webSocketHandler;

import com.example.ms1.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;


public class MessageWebSocketHandler extends StompSessionHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MessageWebSocketHandler.class);

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        logger.info("Received message: {}", payload);
    }

}
