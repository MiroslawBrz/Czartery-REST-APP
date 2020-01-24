package pl.miroslawbrz.czartery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class CharterPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long charterPlaceId;
    private String charterPlaceName;
    private String webSiteUrl;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "charter_place_id")
    private CharterPlaceAddress charterPlaceAddress;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "charter_place_id")
    private Set<Yacht> yachts;

    public static final class Builder{

        private String charterPlaceName = null;
        private String webSiteUrl = null;
        private CharterPlaceAddress charterPlaceAddress = null;

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

        public CharterPlace build(){
            CharterPlace charterPlace = new CharterPlace();
            charterPlace.charterPlaceName = this.charterPlaceName;
            charterPlace.charterPlaceAddress = this.charterPlaceAddress;
            charterPlace.webSiteUrl = this.webSiteUrl;

            return charterPlace;
        }


    }
}
