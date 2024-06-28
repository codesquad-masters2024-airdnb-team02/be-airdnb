package com.example.airdnb.service;

import com.example.airdnb.domain.chat.ChatMessage;
import com.example.airdnb.domain.user.User;
import com.example.airdnb.dto.chat.ChatRequest;
import com.example.airdnb.repository.mongo.ChatRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserService userService;
    private final ChatRepository chatRepository;

    public ChatMessage save(ChatRequest chatRequest) {
        return chatRepository.save(chatRequest.toEntity());
    }

    public List<ChatMessage> getChatMessages(String guest, String host) {
        return chatRepository.findChatMessagesBetween(guest, host);
    }

}
