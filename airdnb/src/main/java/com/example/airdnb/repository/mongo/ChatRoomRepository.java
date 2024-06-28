package com.example.airdnb.repository.mongo;

import com.example.airdnb.domain.chat.ChatRoom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Query("{ 'users': { $all: [?0, ?1], $size: 2 } }")
    Optional<ChatRoom> findByUsers(String userName1, String userName2);

    List<ChatRoom> findByUsersContaining(String username);

}
