package com.example.ms1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sessionId;

    private LocalDateTime MC1Timestamp;

    private LocalDateTime MC2Timestamp;

    private LocalDateTime MC3Timestamp;

    private LocalDateTime endTimestamp;

    public Message(Integer sessionId, LocalDateTime MC1Timestamp) {
        this.sessionId = sessionId;
        this.MC1Timestamp = MC1Timestamp;
    }

    @PrePersist
    private void endTime() {
        endTimestamp = LocalDateTime.now();
    }
}
