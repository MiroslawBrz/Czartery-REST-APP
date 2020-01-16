package pl.miroslawbrz.czartery.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.miroslawbrz.czartery.api.BasicResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserResponse extends BasicResponse {

    @Getter @Setter
    private Long id;

    public UpdateUserResponse(String responseMsg, Long id){
        super(responseMsg);
        this.id = id;
    }

    public UpdateUserResponse(){}


}
