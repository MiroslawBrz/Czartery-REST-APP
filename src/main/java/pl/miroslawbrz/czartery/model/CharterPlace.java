package pl.miroslawbrz.czartery.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class CharterPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long charterPlaceId;
    private String charterPlaceName;
    private String webSiteUrl;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private CharterPlaceAddress charterPlaceAddress;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    public static final class Builder{

        private String charterPlaceName = null;
        private String webSiteUrl = null;
        private CharterPlaceAddress charterPlaceAddress = null;
        private User user = null;

        public Builder charterPlaceName(String charterPlaceName){
            this.charterPlaceName = charterPlaceName;
            return this;
        }
        public Builder webSiteUrl(String webSiteUrl){
            this.webSiteUrl = webSiteUrl;
            return this;
        }
        public Builder charterPlaceAddress(CharterPlaceAddress charterPlaceAddress){
            this.charterPlaceAddress = charterPlaceAddress;
            return this;
        }
        public Builder user(User user){
            this.user = user;
            return this;
        }
        public CharterPlace build(){
            CharterPlace charterPlace = new CharterPlace();
            charterPlace.charterPlaceName = this.charterPlaceName;
            charterPlace.charterPlaceAddress = this.charterPlaceAddress;
            charterPlace.webSiteUrl = this.webSiteUrl;
            charterPlace.user = this.user;

            return charterPlace;
        }


    }
}
