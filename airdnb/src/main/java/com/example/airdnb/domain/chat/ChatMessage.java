package com.example.airdnb.domain.chat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    private ObjectId id;

    private String message;

    private String sender;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public ChatMessage(String message, String sender, MessageType type) {
        this.message = message;
        this.sender = sender;
        this.type = type;
    }

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE;
    }
}
