package com.example.ms1.service;

import com.example.ms1.entity.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private final StompSession session;

    @Value("${interaction.time}")
    private Integer interactionTime;

    @Setter
    @Getter
    private Long workTime;

    @Getter
    private AtomicInteger countMessage;

    @Getter
    private  boolean sendFlag;

    @Async
    public void send(int sessionId) throws InterruptedException {
        log.info("Sending message started");
        countMessage = new AtomicInteger(0);
        long startTime = System.currentTimeMillis();
        long endTime = startTime;
        while (sendFlag && interactionTime > (endTime - startTime) / 1000) {
            Message message = new Message();
            message.setSessionId(sessionId);
            message.setMC1Timestamp(LocalDateTime.now());
            log.debug("Send message" + message);
            session.send("/app/process-message", message);
            countMessage.incrementAndGet();
            Thread.sleep(10);
            endTime = System.currentTimeMillis();
        }
        Thread.sleep(800);
        workTime = (workTime - startTime) / 1000;
        log.info("Sending message stopped. Total messages send: {}. Time work: {}s", countMessage.get(), workTime);
        sendFlag = false;

    }

    public String stop() throws InterruptedException {
        this.setSendFlag(false);
        return "Sending message stopped. Total messages send: " + countMessage.get() + ". Time work: " + workTime + "s";
    }

    public synchronized void setSendFlag(boolean sendFlag) throws InterruptedException {
        this.sendFlag = sendFlag;
        Thread.sleep(1300);
    }
}
