package pl.miroslawbrz.czartery.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateYachtRequest {

    String yachtName;
    String yachtDescription;
    String yachtType;
    int yachtCapacity;
    double yachtLength;

}
