package com.example.demo.domain.serviceImpl.CommentCalification;

import com.example.demo.application.exceptions.CommentWalkerExceptions.CommentWalkerNotFoundException;
import com.example.demo.application.exceptions.WalkExceptions.NotFound.WalkNotFoundException;
import com.example.demo.application.service.CommentCalificationService.CommentCalificationService;
import com.example.demo.domain.entity.commentCalification.CommentCalification;
import com.example.demo.domain.entity.walk.Walk;
import com.example.demo.domain.repository.CommentCalification.CommentCalificationRepository;
import com.example.demo.domain.repository.Walk.WalkRepository;
import com.example.demo.infrastructure.web.projection.classBased.CommentCalificationDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.CommentCalificationProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentCalificationServiceImpl implements CommentCalificationService {

    private final CommentCalificationRepository commentCalificationRepository;
    private final WalkRepository walkRepository;
    @Override
    public CommentCalificationDTO registerCommentCalificationService(CommentCalificationDTO commentCalificationDTO) {
        Walk walk = walkRepository.findById(commentCalificationDTO.getWalkId())
                .orElseThrow(()-> new WalkNotFoundException("Walk not found"));
        CommentCalification commentCalification = CommentCalification.builder()
                .walkId(walk)
                .comment(commentCalificationDTO.getComment())
                .calification(commentCalificationDTO.getCalification())
                .build();

        // Guardando los cambios haciendo uso del repositorio de LocacionComentario
        commentCalification = commentCalificationRepository.save(commentCalification);
        // Personalizando respuesta
        Integer Id = commentCalification.getId();
        Integer IdWalk = commentCalification.getWalkId().getId();
        Integer IdBooking = commentCalification.getWalkId().getBookingId().getId();
        Integer IdOwner = commentCalification.getWalkId().getBookingId().getOwnerId().getId();
        Integer IdWalker = commentCalification.getWalkId().getBookingId().getWalkerId().getId();

        return CommentCalificationDTO.builder()
                .id(Id)
                .walkId(IdWalk)
                .bookingId(IdBooking)
                .ownerId(IdOwner)
                .walkerId(IdWalker)
                .comment(commentCalificationDTO.getComment())
                .calification(commentCalificationDTO.getCalification())
                .build();
    }

    @Override
    public CommentCalificationDTO editCommentCalificationService(Integer id, CommentCalificationDTO commentCalificationDTO) {
        CommentCalification commentCalificationExists = commentCalificationRepository.findById(id)
                .orElseThrow(()-> new CommentWalkerNotFoundException("Id de calificacion y comentario no encontrados"));
        Walk walk = commentCalificationExists.getWalkId();
        if (commentCalificationDTO.getWalkId() != null){
            walk = walkRepository.findById(commentCalificationDTO.getWalkId())
                    .orElseThrow(()-> new WalkNotFoundException("Id del paseo no encontrado"));
        }
        // Actualizando campos
        commentCalificationExists.setComment(commentCalificationDTO.getComment());
        commentCalificationExists.setCalification(commentCalificationDTO.getCalification());
        // Guardando los datos actualizados
        commentCalificationExists = commentCalificationRepository.save(commentCalificationExists);

        // Personalizando respuesta
        Integer Id = commentCalificationExists.getId();
        Integer IdWalk = commentCalificationExists.getWalkId().getId();
        Integer IdBooking = commentCalificationExists.getWalkId().getBookingId().getId();
        Integer IdOwner = commentCalificationExists.getWalkId().getBookingId().getOwnerId().getId();
        Integer IdWalker = commentCalificationExists.getWalkId().getBookingId().getWalkerId().getId();

        return CommentCalificationDTO.builder()
                .id(Id)
                .walkId(IdWalk)
                .bookingId(IdBooking)
                .ownerId(IdOwner)
                .walkerId(IdWalker)
                .comment(commentCalificationDTO.getComment())
                .calification(commentCalificationDTO.getCalification())
                .build();
    }

    @Override
    public List<CommentCalificationProjection> showAllCommentCalificationService() {
        return commentCalificationRepository.findAllProjectedBy();
    }

    @Override
    public Optional<CommentCalificationProjection> showCommentCalificationByIdService(Integer id) {
        return commentCalificationRepository.findProjectedById(id);
    }

    @Override
    public boolean deleteCommentCalificationById(Integer id) {
        CommentCalification commentCalification = commentCalificationRepository.findById(id)
                .orElseThrow(()-> new CommentWalkerNotFoundException("Id de la califiacion y comentario no encontrado"));
        commentCalificationRepository.delete(commentCalification);
        System.out.println("Se eliminó correctamente la calificacion y comentario del paseo");
        return true;
    }
}
