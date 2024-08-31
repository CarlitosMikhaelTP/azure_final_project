package com.example.demo.application.service.WalkerService;

import com.example.demo.infrastructure.web.projection.classBased.WalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.WalkerProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface WalkerService {

    // DEFINIENDO método para realizar el registro de Walker
    WalkerDTO registerWalkerService(WalkerDTO walkerDTO);

    void editFotoWalkerService(Integer id, MultipartFile foto) throws Exception;

    // DEFINIENDO método para realizar la edición de Walker
    WalkerDTO editWalkerService(Integer id, WalkerDTO walkerDTO);

    // DEFINIENDO método para mostrar a los walker registrados
    List<WalkerProjection> showAllWalkersService();

    // DEFINIENDO método para mostrar a un paseador por su ID
    Optional<WalkerProjection> findWalkerByIdService(Integer id);

    // DEFINIENDO método para eliminar un paseador por su ID
    boolean deleteWalkerByIdService(Integer id);



}

