package pl.miroslawbrz.czartery.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "userRoles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    private String description;
    @OneToMany
    @JoinColumn(name = "user_role_id")
    private Set<User> userSet;

}