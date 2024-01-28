package timemessage.ms3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import timemessage.ms3.entity.Message;

import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final SendMessageService sendMessageService;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}", concurrency = "4")
    public void listen(@Payload Message message) {
        message.setMC3Timestamp(LocalDateTime.now());
        log.info(String.valueOf(message));
        sendMessageService.send(message);
    }
}
