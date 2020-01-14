package pl.miroslawbrz.czartery.exception;


import pl.miroslawbrz.czartery.api.common.ConstErrorMsg;

public class CommonBadRequestException extends CommonException{

    public CommonBadRequestException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }

}
