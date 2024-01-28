package com.example.ms1.controller;

import com.example.ms1.entity.Message;
import com.example.ms1.repository.MessageRepository;
import com.example.ms1.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MS1Controller {

    private final MessageRepository messageRepository;
    private final MessageService messageService;


    @GetMapping("/start")
    public ResponseEntity<String> start() throws InterruptedException {
        if (!messageService.isSendFlag()) {
            messageService.setSendFlag(true);
            messageService.send(messageRepository.findMaxSessionId().orElse(0) + 1);
        } else {
            return ResponseEntity.accepted().body("Sending messages has already started.\n");
        }
        return ResponseEntity.accepted().body("Sending message started.\n");
    }

    @GetMapping("/stop")
    public ResponseEntity<String> stop() throws InterruptedException {
        if (!messageService.isSendFlag())
            return ResponseEntity.accepted().body("Service don't started.\n");
        messageService.stop();
        return ResponseEntity.accepted().body("Sending message stopped.\n");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getMessage(@RequestBody Message message) {
        message.setEndTimestamp(LocalDateTime.now());
        messageService.setWorkTime(System.currentTimeMillis());
        Message savedMessage = messageRepository.save(message);
        log.debug("Message save in database " + savedMessage);
    }
}
