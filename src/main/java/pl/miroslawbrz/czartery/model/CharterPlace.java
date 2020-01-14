package pl.miroslawbrz.czartery.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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



}
