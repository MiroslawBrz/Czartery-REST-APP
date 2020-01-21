package pl.miroslawbrz.czartery.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;

public class AddressJsonParse {

    public static CharterPlaceAddress getFullAddressAndCoordinatesFromRequest(CreateCharterPlaceRequest request) {

        CharterPlaceAddress address = null;

        try {
            address = getFullAddressFromRequest(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return address;
    }

    private static CharterPlaceAddress getFullAddressFromRequest(CreateCharterPlaceRequest request) throws JSONException {

        CharterPlaceAddress address = new CharterPlaceAddress();

        ExternalApi externalApi = new ExternalApi();
        String url = externalApi.prepareGeoCoderURL(request);
        String json = externalApi.apiResponse(url);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("Response").getJSONArray("View");
        JSONArray jsonArrayResult = null;
        for (int i = 0; i < jsonArray.length(); i++){
            jsonArrayResult = jsonArray.getJSONObject(i).getJSONArray("Result");
        }
        JSONObject jsonArrayLocation = null;
        for (int i = 0; i < jsonArray.length(); i++){
            assert jsonArrayResult != null;
            jsonArrayLocation = jsonArrayResult.getJSONObject(i).getJSONObject("Location");
        }

        JSONArray jsonArrayNavPosition = null;
        JSONObject jsonObjectAddress = null;

        if(jsonArrayLocation!=null){
            for(int i = 0; i < jsonArrayLocation.length(); i++) {
                jsonObjectAddress = jsonArrayLocation.getJSONObject("Address");
                jsonArrayNavPosition = jsonArrayLocation.getJSONArray("NavigationPosition");
            }

            if(jsonObjectAddress!=null) {
                address.setAddressCity(jsonObjectAddress.getString("City"));
                address.setAddressBuildingNumber(jsonObjectAddress.getString("HouseNumber"));
                address.setAddressPostalCode(jsonObjectAddress.getString("PostalCode"));
                address.setAddressStreet(jsonObjectAddress.getString("Street"));
            }

            if(jsonArrayNavPosition!=null){
                for(int i = 0; i < jsonArray.length(); i++){
                    address.setMapLongitude(jsonArrayNavPosition.getJSONObject(i).getDouble("Longitude"));
                    address.setMapLatitude(jsonArrayNavPosition.getJSONObject(i).getDouble("Latitude"));
                }
            }
        }
        return address;
    }

}
