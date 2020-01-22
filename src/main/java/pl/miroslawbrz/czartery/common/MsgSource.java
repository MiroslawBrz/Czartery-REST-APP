package pl.miroslawbrz.czartery.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsgSource {

    public final String OK001;
    public final String OK002;
    public final String OK003;
    public final String OK004;

    public final String OK101;
    public final String OK102;
    public final String OK103;
    public final String OK105;

    public final String OK201;
    public final String OK202;
    public final String OK203;

    public final ConstErrorMsg ERR001;
    public final ConstErrorMsg ERR002;
    public final ConstErrorMsg ERR003;
    public final ConstErrorMsg ERR004;
    public final ConstErrorMsg ERR005;
    public final ConstErrorMsg ERR006;

    public final ConstErrorMsg ERR101;

    public final ConstErrorMsg ERR201;


    public MsgSource(

            @Value("${user.ok.msg.ok001}") String ok001MsgValue,
            @Value("${user.ok.msg.ok002}") String ok002MsgValue,
            @Value("${user.ok.msg.ok003}") String ok003MsgValue,
            @Value("${user.ok.msg.ok004}") String ok004MsgValue,

            @Value("${charter.ok.msg.ok101}") String ok101MsgValue,
            @Value("${charter.ok.msg.ok102}") String ok102MsgValue,
            @Value("${charter.ok.msg.ok103}") String ok103MsgValue,
            @Value("${charter.ok.msg.ok105}") String ok105MsgValue,

            @Value("${yacht.ok.msg.ok201}") String ok201MsgValue,
            @Value("${yacht.ok.msg.ok202}") String ok202MsgValue,
            @Value("${yacht.ok.msg.ok203}") String ok203MsgValue,


            @Value("${user.const.error.msg.err001}") String err001MsgValue,
            @Value("${user.const.error.msg.err002}") String err002MsgValue,
            @Value("${user.const.error.msg.err003}") String err003MsgValue,
            @Value("${user.const.error.msg.err004}") String err004MsgValue,
            @Value("${user.const.error.msg.err005}") String err005MsgValue,
            @Value("${user.const.error.msg.err006}") String err006MsgValue,

            @Value("${charter.const.error.msg.err101}") String err101MsgValue,

            @Value("${yacht.const.error.msg.err201}") String err201MsgValue

    ){

        OK001 = ok001MsgValue;
        OK002 = ok002MsgValue;
        OK003 = ok003MsgValue;
        OK004 = ok004MsgValue;

        OK101 = ok101MsgValue;
        OK102 = ok102MsgValue;
        OK103 = ok003MsgValue;
        OK105 = ok105MsgValue;

        OK201 = ok201MsgValue;
        OK202 = ok202MsgValue;
        OK203 = ok203MsgValue;

        ERR001 = new ConstErrorMsg("ERR001", err001MsgValue);
        ERR002 = new ConstErrorMsg("ERR002", err002MsgValue);
        ERR003 = new ConstErrorMsg("ERR003", err003MsgValue);
        ERR004 = new ConstErrorMsg("ERR004", err004MsgValue);
        ERR005 = new ConstErrorMsg("ERR005", err005MsgValue);
        ERR006 = new ConstErrorMsg("ERR006", err006MsgValue);

        ERR101 = new ConstErrorMsg("ERR101", err101MsgValue);

        ERR201 = new ConstErrorMsg("ERR201", err201MsgValue);
    }
}
