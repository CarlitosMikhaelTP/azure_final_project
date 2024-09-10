package com.example.demo.domain.entity.walker;

import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.entity.walk.Walk;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "walker_rating")
public class RatingWalker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walker_id")
    private Walker walkerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walk_id")
    private Walk walkId;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 1")
    private Short state;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private RatingWalker createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private RatingWalker updatedBy;
}
