package com.example.demo.domain.entity.user;

import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.entity.owner.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_user_id", nullable = false)
    private TypeUser typeUserId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;

    @Column(name = "address", nullable = false, length = 20)
    private String address;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "cellphone", nullable = false, length = 9, unique = true)
    private String celphone;

    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String  password;

    @Column(name = "state", columnDefinition = "TYNI DEFAULT 1")
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

    /////////// MAPEANDO CARDINALIDAD ////////////////////////////
    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL)
    private Walker walker;

    // En la clase User
    @OneToOne(mappedBy = "userId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Owner owner;

    ///////// Implementando los métodos de la Interfaz User Details ////////////////////
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.emptyList();
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", TypeUserId=" + (typeUserId != null ? typeUserId.getNameTypeUser() : null) +
                ", Names='" + name + '\'' +
                ", LastNames='" + lastName + '\'' +
                ", Nickname='" + nickname + '\'' +
                ", Address='" + address + '\'' +
                ", Age=" + age +
                ", Celphone='" + celphone + '\'' +
                ", Dni='" + dni + '\'' +
                ", Email='" + email + '\'' +
                ", Password='" + password + '\'' +
                ", State=" + state +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                '}';
    }



}
