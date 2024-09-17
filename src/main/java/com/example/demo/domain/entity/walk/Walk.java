package com.example.demo.domain.entity.walk;

import com.example.demo.domain.entity.comment.CommentWalker;
import com.example.demo.domain.entity.commentCalification.CommentCalification;
import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.entity.walker.RatingWalker;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Walk")
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking bookingId;

    @Column(name= "start", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp start;

    @Column(name = "duration", nullable = false)
    private LocalTime duration;

    @Column(name = "place_walk", length = 25, nullable = false)
    private String placeWalk;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    //@Column(name = "calification", nullable = false)
    //private Integer calification;

    @Column(name= "cost", precision = 10, scale = 2, nullable = false)
    private Double cost;

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

    ///////// MAPEANDO LA CARDINALIDAD //////////////////////////
    @ToString.Exclude
    @OneToMany(mappedBy = "walkId")
    private List<CommentCalification> CommentsCalifications;

    @ToString.Exclude
    @OneToMany(mappedBy = "walkId")
    private List<CommentWalker> commentWalkerList;

    @ToString.Exclude
    @OneToOne(mappedBy = "walkId")
    private RatingWalker ratingWalker;

}
