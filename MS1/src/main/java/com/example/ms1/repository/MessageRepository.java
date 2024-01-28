package com.example.ms1.repository;

import com.example.ms1.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select max(m.session_id) from message m", nativeQuery = true)
    Optional<Integer> findMaxSessionId();
}
