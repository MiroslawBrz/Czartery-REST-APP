package pl.miroslawbrz.czartery.exception;

import pl.miroslawbrz.czartery.common.ConstErrorMsg;

public class CommonConflictException extends CommonException {
    public CommonConflictException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
