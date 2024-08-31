package com.example.demo.domain.entity.booking;

import com.example.demo.domain.entity.walk.Walk;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walker_id")
    private Walker walkerId;

    @Column(name= "cost", precision = 10, scale = 2, nullable = false)
    private Double cost;

    @Column(name= "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp date;

    @Column(name = "duration", nullable = false)
    private LocalTime duration;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "place_walk", nullable = false, length = 20)
    private String placeWalk;

    @Column(name = "meeting_point", nullable = false, length = 25)
    private String meetingPoint;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 0", nullable = false)
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

    ////////////// Mapeando cardinalidades //////////////////////

    @OneToOne(mappedBy = "bookingId", cascade = CascadeType.ALL)
    private Walk walk;

}
