package pl.miroslawbrz.czartery.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class CharterPlaceAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String adminName;
    private String adminLastName;
    private Integer adminActivationHash;
    @NotEmpty
    private String adminMail;
    @NotEmpty
    private String adminPassword;
    private boolean adminActive;
    @OneToMany(mappedBy = "charterPlaceAdmin")
    private Set<CharterPlace> charterPlaceSet;



}
