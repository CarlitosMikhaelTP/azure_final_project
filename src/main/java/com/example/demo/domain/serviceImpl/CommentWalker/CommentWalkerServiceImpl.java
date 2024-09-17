package com.example.demo.domain.serviceImpl.CommentWalker;

import com.example.demo.application.exceptions.CommentWalkerExceptions.CommentWalkerNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.ChatNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.RoomNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.TypeMessageNotFoundException;
import com.example.demo.application.exceptions.UserExceptions.NotFound.UserNotFoundException;
import com.example.demo.application.exceptions.WalkExceptions.NotFound.WalkNotFoundException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.service.CommentWalkerService.CommentWalkerService;
import com.example.demo.domain.entity.chat.Chat;
import com.example.demo.domain.entity.chat.Room;
import com.example.demo.domain.entity.chat.TypeMessage;
import com.example.demo.domain.entity.comment.CommentWalker;
import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.entity.walk.Walk;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.repository.Comment.CommentWalkerRepository;
import com.example.demo.domain.repository.User.UserRepository;
import com.example.demo.domain.repository.Walk.WalkRepository;
import com.example.demo.domain.repository.Walker.WalkerRepository;
import com.example.demo.infrastructure.web.projection.classBased.CommentWalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.ChatProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentWalkerProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentWalkerServiceImpl implements CommentWalkerService {

    private final CommentWalkerRepository commentWalkerRepository;
    private final WalkerRepository walkerRepository;
    private final WalkRepository walkRepository;
    private final UserRepository userRepository;

    // Implementando el servicio para realizar los chats
    @Override
    public CommentWalkerDTO registerCommentWalkerService(CommentWalkerDTO commentWalkerDTO) {


        Walker walker = walkerRepository.findById(commentWalkerDTO.getWalkerId())
                .orElseThrow(()-> new WalkerNotFoundException("Id del paseador no encontrado"));

        Walk walk = walkRepository.findById(commentWalkerDTO.getWalkId())
                .orElseThrow(()-> new WalkNotFoundException("Id del tipo de paseo no encontrado"));

        User user = userRepository.findById(commentWalkerDTO.getUserId())
                .orElseThrow(()-> new UserNotFoundException("Id del tipo de usuario no encontrado"));

        CommentWalker commentWalker = CommentWalker.builder()
                .walkerId(walker)
                .walkId(walk)
                .userId(user)
                .comment(commentWalkerDTO.getComment())
                .build();

        // Guardando los cambios haciendo uso del repositorio
        commentWalker = commentWalkerRepository.save(commentWalker);

        return commentWalkerDTO.builder()
                .id(commentWalker.getId())
                .walkerId(commentWalker.getWalkerId().getId())
                .walkId(commentWalker.getWalkId().getId())
                .userId(commentWalker.getUserId().getId())
                .comment(commentWalker.getComment())
                .build();
    }

    @Override
    public CommentWalkerDTO editCommentWalkerService(Integer id, CommentWalkerDTO commentWalkerDTO) {

        CommentWalker commentWalkerExists = commentWalkerRepository.findById(id)
                .orElseThrow(()-> new CommentWalkerNotFoundException("Id del comentario del paseador no encontrado"));

        Walker walker = commentWalkerExists.getWalkerId();
        if (commentWalkerDTO.getWalkId() != null){
            walker = walkerRepository.findById(commentWalkerDTO.getWalkerId())
                    .orElseThrow(()-> new WalkerNotFoundException("Id del paseador no encontrado"));
        }
        Walk walk = commentWalkerExists.getWalkId();
        if (commentWalkerDTO.getWalkId() != null){
            walk =  walkRepository.findById(commentWalkerDTO.getWalkId())
                    .orElseThrow(()-> new WalkerNotFoundException("Id del paseo no encontrado"));
        }
        User user = commentWalkerExists.getUserId();
        if (commentWalkerDTO.getUserId() != null){
            user = userRepository.findById(commentWalkerDTO.getUserId())
                    .orElseThrow(()-> new UserNotFoundException("Id del usuario no encontrado"));
        }
        if (commentWalkerDTO.getComment() != null){
            commentWalkerExists.setComment(commentWalkerDTO.getComment());
        }
        // Guardando campos
        commentWalkerExists = commentWalkerRepository.save(commentWalkerExists);

        return commentWalkerDTO.builder()
                .id(commentWalkerExists.getId())
                .walkerId(commentWalkerExists.getWalkerId().getId())
                .walkId(commentWalkerExists.getWalkId().getId())
                .userId(commentWalkerExists.getUserId().getId())
                .comment(commentWalkerExists.getComment())
                .build();
    }

    @Override
    public List<CommentWalkerProjection> showAllCommentWalkerService() {
        return commentWalkerRepository.findAllProjectedBy();
    }

    @Override
    public Optional<CommentWalkerProjection> showCommentWalkerByIdService(Integer id) {
        return commentWalkerRepository.findProjectedById(id);
    }

    @Override
    public boolean deleteCommentById(Integer id) {
        CommentWalker commentWalker = commentWalkerRepository.findById(id)
                .orElseThrow(()-> new CommentWalkerNotFoundException("Id del comentario no encontrado."));
        commentWalkerRepository.delete(commentWalker);
        System.out.println("Se eliminó correctamente el chat");
        return true;
    }

    @Override
    public List<CommentWalkerProjection> showAllCommentsByWalkerIdService(Integer walkerId) {
        Walker walker = walkerRepository.findById(walkerId)
                .orElseThrow(() -> new WalkerNotFoundException("Id del paseador no encontrado"));
        return commentWalkerRepository.findAllByWalkerId(walker);
    }
}
