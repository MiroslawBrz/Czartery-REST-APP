package pl.miroslawbrz.czartery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;
import pl.miroslawbrz.czartery.utils.AddressJsonParse;
import pl.miroslawbrz.czartery.utils.GeoCoderApi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoCoderApiTest {

    @Test
    void connectionTest() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://geocoder.ls.hereapi.com/6.2/geocode.json?apikey=B6z_NyiG71oD6yT8FTXsj6pVvH2-VfuJWaO3dP5c4RI&searchtext=17+Bogus%C5%82awskiego+Warszawa+Poland").openConnection();
        int responseCode = connection.getResponseCode();
        Assertions.assertEquals(200, responseCode);
    }

    @Test
    void prepareRequestWithEmptyAddressTest(){
        String expectedUrl = "https://geocoder.ls.hereapi.com/6.2/geocode.json?apikey=B6z_NyiG71oD6yT8FTXsj6pVvH2-VfuJWaO3dP5c4RI&searchtext=Poland";
        CreateCharterPlaceRequest request = new CreateCharterPlaceRequest();
        GeoCoderApi geocoderApi = new GeoCoderApi();
        String url = geocoderApi.prepareRequest(request);
        Assertions.assertEquals(expectedUrl, url);
    }
    @Test
    void prepareRequestWithFullAddressTest(){
        String expectedUrl = "https://geocoder.ls.hereapi.com/6.2/geocode.json?apikey=B6z_NyiG71oD6yT8FTXsj6pVvH2-VfuJWaO3dP5c4RI&searchtext=1+x+x+Poland";
        CreateCharterPlaceRequest request = new CreateCharterPlaceRequest();
        request.setAddressCity("x");
        request.setAddressBuildingNumber("1");
        request.setAddressStreet("x");
        GeoCoderApi geocoderApi = new GeoCoderApi();
        String url = geocoderApi.prepareRequest(request);
        Assertions.assertEquals(expectedUrl, url);
    }


}
