package com.example.airdnb.dto.chat;

import com.example.airdnb.domain.chat.ChatMessage;
import com.example.airdnb.domain.chat.ChatMessage.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatRequest (
    @NotNull String sender,
    @NotNull String receiver,
    @NotBlank String content,
    @NotBlank MessageType type
) {

    public ChatMessage toEntity() {
        return ChatMessage.builder()
            .message(content)
            .sender(sender)
            .receiver(receiver)
            .type(type)
            .build();
    }

}
