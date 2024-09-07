package com.example.demo.domain.serviceImpl.Chat;

import com.example.demo.application.exceptions.BookingExceptions.NotFound.BookingNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.ChatNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.RoomNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.TypeMessageNotFoundException;
import com.example.demo.application.exceptions.OwnerExceptions.NotFound.OwnerNotFoundException;
import com.example.demo.application.exceptions.UserExceptions.NotFound.UserNotFoundException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.service.BookingService.BookingService;
import com.example.demo.application.service.ChatService.ChatService;
import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.domain.entity.chat.Chat;
import com.example.demo.domain.entity.chat.Room;
import com.example.demo.domain.entity.chat.TypeMessage;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.repository.Booking.BookingRepository;
import com.example.demo.domain.repository.Chat.ChatRepository;
import com.example.demo.domain.repository.Chat.RoomRepository;
import com.example.demo.domain.repository.Chat.TypeMessageRepository;
import com.example.demo.domain.repository.Chat.TypeRoomRepository;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.domain.repository.User.UserRepository;
import com.example.demo.domain.repository.Walker.WalkerRepository;
import com.example.demo.infrastructure.web.projection.classBased.BookingDTO;
import com.example.demo.infrastructure.web.projection.classBased.ChatDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.BookingProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.ChatProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;
    private final TypeMessageRepository typeMessageRepository;
    private final UserRepository userRepository;

    // Implementando el servicio para realizar los chats
    @Override
    public ChatDTO registerChatService(ChatDTO chatDTO) {


        Room room = roomRepository.findById(chatDTO.getRoomId())
                .orElseThrow(()-> new RoomNotFoundException("Id de la sala no encontrado"));

        TypeMessage typeMessage = typeMessageRepository.findById(chatDTO.getTypeMessageId())
                .orElseThrow(()-> new TypeMessageNotFoundException("Id del tipo de mensaje no encontrado"));

        User user = userRepository.findById(chatDTO.getUserId())
                .orElseThrow(()-> new UserNotFoundException("Id del tipo de usuario no encontrado"));

        Chat chat = Chat.builder()
                .room(room)
                .user(user)
                .typeMessage(typeMessage)
                .message(chatDTO.getMessage())
                .date(chatDTO.getDate())
                .url(chatDTO.getUrl())
                .build();

        // Guardando los cambios haciendo uso del repositorio
        chat = chatRepository.save(chat);

        return chatDTO.builder()
                .id(chat.getId())
                .roomId(chat.getRoom().getId())
                .nameUser(chat.getUser().getName())
                .userId(chat.getUser().getId())
                .typeMessageId(chat.getTypeMessage().getId())
                .message(chat.getMessage())
                .date(chat.getDate())
                .url(chat.getUrl())
                .build();
    }

    @Override
    public ChatDTO editChatService(Integer id, ChatDTO chatDTO) {

        Chat chatExists = chatRepository.findById(id)
                .orElseThrow(()-> new ChatNotFoundException("Id del chat no encontrado"));

        Room room = chatExists.getRoom();
        if (chatDTO.getRoomId() != null){
            room = roomRepository.findById(chatDTO.getRoomId())
                    .orElseThrow(()-> new RoomNotFoundException("Id de la sala no encontrado"));
        }
        TypeMessage typeMessage = chatExists.getTypeMessage();
        if (chatDTO.getTypeMessageId() != null){
            typeMessage =  typeMessageRepository.findById(chatDTO.getTypeMessageId())
                    .orElseThrow(()-> new TypeMessageNotFoundException("Id del tipo de mensaje no encontrado"));
        }
        User user = chatExists.getUser();
        if (chatDTO.getUserId() != null){
            user = userRepository.findById(chatDTO.getUserId())
                    .orElseThrow(()-> new UserNotFoundException("Id del usuario no encontrado"));
        }
        if (chatDTO.getMessage() != null){
            chatExists.setMessage(chatDTO.getMessage());
        }
        if (chatDTO.getDate() != null){
            chatExists.setDate(chatDTO.getDate());
        }
        if (chatDTO.getUrl() != null){
            chatExists.setUrl(chatDTO.getUrl());
        }
        // Guardando campos
        chatExists = chatRepository.save(chatExists);

        return chatDTO.builder()
                .id(chatExists.getId())
                .roomId(chatExists.getRoom().getId())
                .userId(chatExists.getUser().getId())
                .nameUser(chatExists.getUser().getName())
                .typeMessageId(chatExists.getTypeMessage().getId())
                .message(chatExists.getMessage())
                .date(chatExists.getDate())
                .url(chatExists.getUrl())
                .build();
    }

    @Override
    public List<ChatProjection> showAllChatService() {
        return chatRepository.findAllProjectedBy();
    }

    @Override
    public Optional<ChatProjection> showChatByIdService(Integer id) {
        return chatRepository.findProjectedById(id);
    }

    @Override
    public boolean deleteChatById(Integer id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(()-> new ChatNotFoundException("Id del chat no encontrado."));
        chatRepository.delete(chat);
        System.out.println("Se eliminó correctamente el chat");
        return true;
    }
}
