package com.example.demo.domain.entity.chat;

import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.transaction.StateTransaction;
import com.example.demo.domain.entity.transaction.TypeTransaction;
import com.example.demo.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "type_room")
public class TypeRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_room", nullable = false, length = 30)
    private String nameRoom;

    @Column(name = "state", columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private Short state;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private TypeRoom createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private TypeRoom updatedBy;

    // Marcando la cardinalidad
    @ToString.Exclude
    @OneToMany(mappedBy = "typeRoom", cascade = CascadeType.ALL)
    private List<Room> roomList;
}
