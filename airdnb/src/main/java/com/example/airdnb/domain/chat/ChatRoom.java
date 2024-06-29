package com.example.airdnb.domain.chat;

import jakarta.persistence.Id;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatrooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatRoom {

    @Id
    private ObjectId id;

    private Set<String> users = new HashSet<>();

    private List<ChatMessage> chatMessages;

    public ChatRoom(String guest, String host) {
        users.add(guest);
        users.add(host);
    }

    public boolean containsUsers(String userName1, String userName2) {
        return users.contains(userName1) && users.contains(userName2);
    }

    public void addChatMessage(ChatMessage chatMessage) {
        chatMessages.add(chatMessage);
    }
}
