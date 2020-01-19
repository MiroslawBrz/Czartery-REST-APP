package pl.miroslawbrz.czartery;

import org.junit.Assert;
import org.junit.Test;
import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;
import pl.miroslawbrz.czartery.utils.AddressJsonParse;

public class AddressJsonParseTests {

    @Test
    public void ifSetCorrectCreateCharterPlaceRequestShouldReturnAddressWithCoordinates(){
        CreateCharterPlaceRequest request = new CreateCharterPlaceRequest.Builder()
                .charterPlaceName("Czartery Mirek")
                .webSiteUrl("mirek.pl")
                .addressBuildingNumber("17")
                .addressCity("Warszawa")
                .addressStreet("Bogusławskiego")
                .build();

        CharterPlaceAddress address = AddressJsonParse.getFullAddressAndCoordinatesFromRequest(request);
        Assert.assertTrue(address.getMapLatitude()!=0.0);
        Assert.assertTrue(address.getMapLongitude()!=0.0);
        Assert.assertNotNull(address.getAddressPostalCode());
    }


}
