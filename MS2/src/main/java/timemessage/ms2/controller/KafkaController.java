package timemessage.ms2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import timemessage.ms2.entity.Message;
import timemessage.ms2.service.KafkaProducerService;

import java.time.LocalDateTime;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService producer;

    @MessageMapping("/process-message")
    @SendTo("/topic/messages")
    public Message processMessage(Message message) {
        message.setMC2Timestamp(LocalDateTime.now());
        log.info(String.valueOf(message));
        producer.sendMessage(message);
        return message;
    }
}
