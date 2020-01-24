package pl.miroslawbrz.czartery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data
@Entity
@Table(name = "CharterUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userLastName;
    private Integer activationHash;
    @NotEmpty
    private String userMail;
    @NotEmpty
    private String userPassword;
    private boolean userActive;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<CharterPlace> charterPlaceSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<ReservationDetails> reservationDetails;


    public static final class Builder{
        private String userName = null;
        private String userLastName = null;
        private String userMail = null;
        private String userPassword = null;
        private Integer userActivationHash = null;

        public Builder userName(String userName){
            this.userName=userName;
            return this;
        }
        public Builder userLastName(String userLastName){
            this.userLastName=userLastName;
            return this;
        }
        public Builder userMail(String userMail){
            this.userMail=userMail;
            return this;
        }
        public Builder userPassword(String userPassword){
            this.userPassword=userPassword;
            return this;
        }
        public Builder userActivationHash(Integer userActivationHash){
            this.userActivationHash=userActivationHash;
            return this;
        }

        public User build(){
            User user = new User();
            user.userName = this.userName;
            user.userLastName = this.userLastName;
            user.userMail = this.userMail;
            user.userPassword = this.userPassword;
            user.activationHash = this.userActivationHash;
            return user;
        }

    }

}
