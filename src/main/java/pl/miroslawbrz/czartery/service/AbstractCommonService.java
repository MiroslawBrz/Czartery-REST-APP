package pl.miroslawbrz.czartery.service;

import pl.miroslawbrz.czartery.common.MsgSource;

public abstract class AbstractCommonService {

    protected MsgSource msgSource;

    public AbstractCommonService(MsgSource msgSource) {
        this.msgSource = msgSource;
    }
}
