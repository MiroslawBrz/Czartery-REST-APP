package pl.miroslawbrz.czartery.utils;

import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.constans.ApiUrl;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class GeoCoderApi{


    String apiResponse(String request){

        String response = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(request).openConnection();

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

    String prepareRequest(CreateCharterPlaceRequest request){
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

}
