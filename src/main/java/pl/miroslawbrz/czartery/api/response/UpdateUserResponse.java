package pl.miroslawbrz.czartery.api.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.miroslawbrz.czartery.api.BasicResponse;


public class UpdateUserResponse extends BasicResponse {

    @Getter @Setter
    private Long id;

    public UpdateUserResponse(String responseMsg, Long id){
        super(responseMsg);
        this.id = id;
    }

    public UpdateUserResponse(){}


}
