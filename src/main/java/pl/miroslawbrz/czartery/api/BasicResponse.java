package pl.miroslawbrz.czartery.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.miroslawbrz.czartery.api.type.ResponseStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicResponse {

    private String responseMessage;
    private String errorCode;
    private String errorMessage;
    private ResponseStatus responseStatus;

    public BasicResponse(){}

    public BasicResponse(String responseMessage){
        this.responseMessage = responseMessage;
        this.responseStatus = ResponseStatus.SUCCESS;
    }

    private BasicResponse(String errorCode, String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
        this.responseStatus=ResponseStatus.ERROR;
    }

    public static BasicResponse of(String responseMessage){
        return new BasicResponse(responseMessage);
    }

    public static BasicResponse ofError(String errorCode, String errorMessage){
        return new BasicResponse(errorCode, errorMessage);
    }
}
