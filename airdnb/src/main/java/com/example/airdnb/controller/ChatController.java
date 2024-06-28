package com.example.airdnb.controller;

import com.example.airdnb.domain.chat.ChatMessage;
import com.example.airdnb.dto.chat.ChatRequest;
import com.example.airdnb.service.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatRequest chatRequest, SimpMessageHeaderAccessor headerAccessor) {
        log.debug("메세지: {}", chatRequest);
        ChatMessage savedMessage = chatService.save(chatRequest);
        messagingTemplate.convertAndSend("/queue/messages/" + chatRequest.receiver(), savedMessage);
    }

    @GetMapping("/chat/history")
    public List<ChatMessage> getChatHistory(@RequestParam String guest, @RequestParam String host) {
        return chatService.getChatMessages(guest, host);
    }

}
