package com.example.demo.domain.entity.pet;

import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_pet_id", nullable = false)
    private TypePet typePetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bad_habit_id", nullable = false)
    private BadHabits badHabits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name="foto")
    private String foto;

    @Column(name = "race", length = 25, nullable = false)
    private String race;

    @Column(name = "weight", length = 20, nullable = false)
    private String weight;

    @Column(name = "age", length = 20, nullable = false)
    private String age;

    @Column(name = "needs", columnDefinition = "TEXT", nullable = false)
    private String needs;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private Short state;

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



}
