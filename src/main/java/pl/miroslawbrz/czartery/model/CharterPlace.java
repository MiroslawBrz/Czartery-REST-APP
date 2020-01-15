package pl.miroslawbrz.czartery.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CharterPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long charterPlaceId;
    private String charterPlaceName;
    private String webSiteUrl;
    private double mapLatitude;
    private double mapLongitude;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private CharterPlaceAddress charterPlaceAddress;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "adminId")
    private CharterPlaceAdmin charterPlaceAdmin;


}
