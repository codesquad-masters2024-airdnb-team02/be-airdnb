package com.example.airdnb.service;

import com.example.airdnb.domain.chat.ChatMessage;
import com.example.airdnb.domain.chat.ChatRoom;
import com.example.airdnb.dto.chat.ChatRequest;
import com.example.airdnb.repository.mongo.ChatRoomRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatMessage save(String chatRoomId, ChatRequest chatRequest) {
        ChatRoom chatRoom = findChatRoomById(chatRoomId);
        ChatMessage chatMessage = chatRequest.toEntity();
        chatRoom.addChatMessage(chatMessage);
        chatRoomRepository.save(chatRoom);
        return chatMessage;
    }

    public List<ChatRoom> findAllChatRooms(String userName) {
        return chatRoomRepository.findByUsersContaining(userName);
    }

    public List<ChatMessage> findMessageByChatRoomId(String chatRoomId) {
        ChatRoom chatRoom = findChatRoomById(chatRoomId);
        return chatRoom.getChatMessages();
    }

    public ChatRoom findChatRoomById(String chatRoomId) {
        return chatRoomRepository.findById(chatRoomId).orElseThrow(
            NoSuchElementException::new);
    }

    public ChatRoom getOrCreateChatRoom(String guestName, String hostName) {
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findByUsers(guestName, hostName);
        return chatRoomOptional.orElseGet(
            () -> chatRoomRepository.save(new ChatRoom(guestName, hostName)));
    }

}
