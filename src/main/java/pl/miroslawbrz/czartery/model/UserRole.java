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
    @ManyToMany(cascade=CascadeType.ALL)
    private Set<User> userSet;

    public UserRole(String role, String description) {
        this.role = role;
        this.description = description;
    }
    public UserRole(){}
}