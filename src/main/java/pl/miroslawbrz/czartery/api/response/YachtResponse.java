package pl.miroslawbrz.czartery.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.miroslawbrz.czartery.api.BasicResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class YachtResponse extends BasicResponse {

    private Long YachtId;
    private Long CharterPlaceId;

    public YachtResponse(String responseMessage, Long yachtId, Long charterPlaceId) {
        super(responseMessage);
        YachtId = yachtId;
        CharterPlaceId = charterPlaceId;
    }

    public YachtResponse(String responseMessage, Long yachtId) {
        super(responseMessage);
        YachtId = yachtId;
    }

    public Long getYachtId() {
        return YachtId;
    }

    public void setYachtId(Long yachtId) {
        YachtId = yachtId;
    }

    public Long getCharterPlaceId() {
        return CharterPlaceId;
    }

    public void setCharterPlaceId(Long charterPlaceId) {
        CharterPlaceId = charterPlaceId;
    }

}
