package pl.miroslawbrz.czartery.utils;

import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.constans.ApiUrl;
import pl.miroslawbrz.czartery.model.CharterPlace;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ExternalApi {


    String apiResponse(String URL){

        String response = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getResponseCode() == 200 ? connection.getInputStream():connection.getErrorStream()
            ));
            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            response = stringBuilder.toString();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return response;
    }

    public String prepareGeoCoderURL(CreateCharterPlaceRequest request){
        String url = ApiUrl.GEO_CODER_URL;
        String apiKey = ApiUrl.GEO_CODER_API_KEY;

        StringBuilder sb = new StringBuilder();
        sb.append(url).append(apiKey);

        if(request.getAddressBuildingNumber() != null){
            sb.append(request.getAddressBuildingNumber()).append("+");
        }
        if(request.getAddressStreet() != null){
            sb.append(request.getAddressStreet()).append("+");
        }
        if (request.getAddressCity() != null){
            sb.append(request.getAddressCity()).append("+");
        }
        sb.append(CharterPlaceAddress.addressCountry);

        return sb.toString();
    }

    public String prepareOpenWeatherURL(CharterPlace charterPlace){

        double longitude = charterPlace.getCharterPlaceAddress().getMapLongitude();
        double latitude = charterPlace.getCharterPlaceAddress().getMapLatitude();
        int numberOfDaysForecast = 7;

        StringBuilder sb = new StringBuilder();

        sb.append(ApiUrl.OPEN_WEATHER_API_URL)
                .append("?lat=")
                .append(latitude)
                .append("&lon=")
                .append(longitude)
                .append("&cnt=")
                .append(numberOfDaysForecast)
                .append("&APPID=")
                .append(ApiUrl.OPEN_WEATHER_API_KEY);

        return sb.toString();

    }

}
