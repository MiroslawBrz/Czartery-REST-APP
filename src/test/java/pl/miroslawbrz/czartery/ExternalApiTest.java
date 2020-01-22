package pl.miroslawbrz.czartery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.model.CharterPlace;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;
import pl.miroslawbrz.czartery.utils.ExternalApi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalApiTest {

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
        ExternalApi geocoderApi = new ExternalApi();
        String url = geocoderApi.prepareGeoCoderURL(request);
        Assertions.assertEquals(expectedUrl, url);
    }
    @Test
    void prepareRequestWithFullAddressTest(){
        String expectedUrl = "https://geocoder.ls.hereapi.com/6.2/geocode.json?apikey=B6z_NyiG71oD6yT8FTXsj6pVvH2-VfuJWaO3dP5c4RI&searchtext=1+x+x+Poland";
        CreateCharterPlaceRequest request = new CreateCharterPlaceRequest();
        request.setAddressCity("x");
        request.setAddressBuildingNumber("1");
        request.setAddressStreet("x");
        ExternalApi geocoderApi = new ExternalApi();
        String url = geocoderApi.prepareGeoCoderURL(request);
        Assertions.assertEquals(expectedUrl, url);
    }

    @Test
    void prepareOpenWeatherURLWithFullContentTest(){

        String expectedUrl = "http://api.openweathermap.org/data/2.5/forecast?lat=52.279&lon=20.918&APPID=ba17805e18031a0ba390fcfa385254d6";

        CharterPlace charterPlace = new CharterPlace();
        CharterPlaceAddress address = new CharterPlaceAddress();
        address.setMapLatitude(52.279);
        address.setMapLongitude(20.918);
        charterPlace.setCharterPlaceAddress(address);

        ExternalApi externalApi = new ExternalApi();
        String url = externalApi.prepareOpenWeatherURL(charterPlace);

        Assertions.assertEquals(expectedUrl, url);


    }


}
