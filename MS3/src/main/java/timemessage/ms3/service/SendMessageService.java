package timemessage.ms3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import timemessage.ms3.entity.Message;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class SendMessageService {

    @Value("${microservice1}")
    String urlMicroservice1;

    private final RestTemplate restTemplate;

    public SendMessageService() {
        restTemplate = new RestTemplate();
    }

    public void send(Message message) {
        try {
            URI uri = new URI(urlMicroservice1);
            RequestEntity<Message> requestEntity = RequestEntity.post(uri).body(message);
            ResponseEntity<Message> response = restTemplate.exchange(requestEntity, Message.class);
        } catch (URISyntaxException | RestClientException e) {
            e.printStackTrace();
        }
    }
}
