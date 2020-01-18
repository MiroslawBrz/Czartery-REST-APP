package pl.miroslawbrz.czartery.api.request;

import lombok.Data;

@Data
public class CreateCharterPlaceRequest {

    private String charterPlaceName;
    private String webSiteUrl;
    private String addressStreet;
    private String addressCity;
    private String addressBuildingNumber;

}
