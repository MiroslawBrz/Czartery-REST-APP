package pl.miroslawbrz.czartery.exception;

import lombok.Getter;
import pl.miroslawbrz.czartery.common.ConstErrorMsg;

public class CommonException extends RuntimeException {


    @Getter
    private ConstErrorMsg constErrorMsg;

    public CommonException(ConstErrorMsg constErrorMsg) {
        this.constErrorMsg = constErrorMsg;
    }

}
