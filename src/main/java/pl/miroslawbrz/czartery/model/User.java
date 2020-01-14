package pl.miroslawbrz.czartery.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "CharterUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String UserName;
    private String UserLastName;
    @NotEmpty
    private String UserMail;
    @NotEmpty
    private String UserPassword;
    private boolean UserActive;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<CharterPlace> charterPlaces = new HashSet<>();

}
