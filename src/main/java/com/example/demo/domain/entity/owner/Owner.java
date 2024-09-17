package com.example.demo.domain.entity.owner;

import com.example.demo.domain.entity.pet.Pet;
import com.example.demo.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    // Posible implementación de tabla para el tipo de calificación asiganda //
    @Column(name = "calification")
    private Integer calification;

    @Column(name = "foto")
    private String foto;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "walking_preferences", columnDefinition = "TEXT", nullable = false)
    private String walkingPreferences;

    @Column(name = "money", precision = 10, scale = 2, nullable = false)
    private Double money;

    @Column(name = "availability", nullable = false)
    private Boolean availability;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    private Short state;

    @Column(name = "location", length = 20)
    private String location;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private User updatedBy;

    ///////// MAPEO DE CARDINALIDADES ////////////////////////////
    //@OneToMany(mappedBy = "owner")
    //private List<Transaction> transacciones;
    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> mascotas;

    //@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    //private List<Booking> booking;
    @ToString.Exclude
    @OneToOne(mappedBy = "ownerId", cascade = CascadeType.ALL, orphanRemoval = true)
    private LocationOwner locationOwner;
}
