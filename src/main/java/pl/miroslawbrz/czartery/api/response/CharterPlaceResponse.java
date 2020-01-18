package pl.miroslawbrz.czartery.api.response;

import lombok.Getter;
import lombok.Setter;
import pl.miroslawbrz.czartery.api.BasicResponse;

public class CharterPlaceResponse extends BasicResponse {

    @Getter @Setter
    private long id;


    public CharterPlaceResponse(String responseMessage, long id) {
        super(responseMessage);
        this.id = id;
    }

    public CharterPlaceResponse(){}
}
