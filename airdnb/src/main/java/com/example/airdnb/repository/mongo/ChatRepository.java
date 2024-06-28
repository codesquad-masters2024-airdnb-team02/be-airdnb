package com.example.airdnb.repository.mongo;

import com.example.airdnb.domain.chat.ChatMessage;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatRepository extends MongoRepository<ChatMessage, ObjectId> {

    @Query("{ $or: [ { $and: [ { 'sender': ?0 }, { 'receiver': ?1 } ] }, { $and: [ { 'sender': ?1 }, { 'receiver': ?0 } ] } ] }")
    List<ChatMessage> findChatMessagesBetween(String sender, String receiver);
}
