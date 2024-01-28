package timemessage.ms3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {

    private Long id;

    private Integer sessionId;

    private LocalDateTime MC1Timestamp;

    private LocalDateTime MC2Timestamp;

    private LocalDateTime MC3Timestamp;

    private LocalDateTime endTimestamp;

}
