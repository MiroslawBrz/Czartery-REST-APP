package pl.miroslawbrz.czartery.api.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsgSource {

    public final String OK001;

    public final ConstErrorMsg ERR001;

    public MsgSource(

            @Value("${common.ok.msg.ok001}") String ok001MsgValue,

            @Value("${common.const.error.msg.err001}") String err001MsgValue

    ){

        OK001 = ok001MsgValue;

        ERR001 = new ConstErrorMsg("ERR001", err001MsgValue);

    }


}
