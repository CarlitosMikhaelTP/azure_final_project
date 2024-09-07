package com.example.demo.application.service.ChatService;

import com.example.demo.infrastructure.web.projection.classBased.ChatDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.ChatProjection;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    ChatDTO registerChatService(ChatDTO chatDTO);

    ChatDTO editChatService(Integer id, ChatDTO chatDTO);

    List<ChatProjection> showAllChatService();

    Optional<ChatProjection> showChatByIdService(Integer id);

    boolean deleteChatById(Integer id);
}
