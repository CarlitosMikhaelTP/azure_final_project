package com.example.demo.domain.entity.walker;

import com.example.demo.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Walker")
public class Walker {
    // Id del paseador
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = false)
    private Category categoryId;

    @Column(name = "calification", nullable = false)
    private Integer calification;

    @Column(name = "number_walk", nullable = false, columnDefinition = "DEFAULT 0")
    private Integer number_walk;

    @Column(name = "foto")
    private String foto;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "experience", columnDefinition = "TEXT", nullable = false)
    private String experience;

    @Column(name = "prefer_race", length = 25, nullable = false)
    private String prefer_race;

    @Column(name = "location", length = 20)
    private String location;

    @Column(name = "cost", precision = 10, scale = 2, nullable = false)
    private Double cost;

    @Column(name = "money", precision = 10, scale = 2, nullable = false)
    private Double money;

    @Column(name = "availability", nullable = false)
    private Boolean availability;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 0", nullable = false)
    private Short state;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private User updatedBy;


    //////////// MAPEO DE CARDINALIDADES ////////////////////////
   // @OneToMany(mappedBy = "walker", cascade = CascadeType.ALL)
    //private List<Transaction> transacciones;

    //@OneToMany(mappedBy = "walker", cascade = CascadeType.ALL)
    //private List<Booking> booking;

    @OneToOne(mappedBy = "walkerId", cascade = CascadeType.ALL)
    private LocationWalker locationWalker;

    @OneToMany(mappedBy = "walkerId", cascade = CascadeType.ALL)
    private List<RatingWalker> ratingWalkerList;
}
