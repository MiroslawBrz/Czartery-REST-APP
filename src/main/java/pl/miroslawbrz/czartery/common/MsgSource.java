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
    public final String OK204;

    public final String OK301;
    public final String OK302;
    public final String OK303;

    public final ConstErrorMsg ERR001;
    public final ConstErrorMsg ERR002;
    public final ConstErrorMsg ERR003;
    public final ConstErrorMsg ERR004;
    public final ConstErrorMsg ERR005;
    public final ConstErrorMsg ERR006;

    public final ConstErrorMsg ERR101;
    public final ConstErrorMsg ERR102;

    public final ConstErrorMsg ERR201;
    public final ConstErrorMsg ERR202;
    public final ConstErrorMsg ERR203;

    public final ConstErrorMsg ERR301;
    public final ConstErrorMsg ERR302;
    public final ConstErrorMsg ERR303;
    public final ConstErrorMsg ERR304;
    public final ConstErrorMsg ERR305;

    public final ConstErrorMsg ERR401;


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
            @Value("${yacht.ok.msg.ok204}") String ok204MsgValue,

            @Value("${reservation.ok.msg.ok301}") String ok301MsgValue,
            @Value("${reservation.ok.msg.ok302}") String ok302MsgValue,
            @Value("${reservation.ok.msg.ok303}") String ok303MsgValue,


            @Value("${user.const.error.msg.err001}") String err001MsgValue,
            @Value("${user.const.error.msg.err002}") String err002MsgValue,
            @Value("${user.const.error.msg.err003}") String err003MsgValue,
            @Value("${user.const.error.msg.err004}") String err004MsgValue,
            @Value("${user.const.error.msg.err005}") String err005MsgValue,
            @Value("${user.const.error.msg.err006}") String err006MsgValue,

            @Value("${charter.const.error.msg.err101}") String err101MsgValue,
            @Value("${charter.const.error.msg.err102}") String err102MsgValue,

            @Value("${yacht.const.error.msg.err201}") String err201MsgValue,
            @Value("${yacht.const.error.msg.err202}") String err202MsgValue,
            @Value("${yacht.const.error.msg.err203}") String err203MsgValue,

            @Value("${reservation.const.error.msg.err301}") String err301MsgValue,
            @Value("${reservation.const.error.msg.err302}") String err302MsgValue,
            @Value("${reservation.const.error.msg.err303}") String err303MsgValue,
            @Value("${reservation.const.error.msg.err304}") String err304MsgValue,
            @Value("${reservation.const.error.msg.err305}") String err305MsgValue,
            @Value("${jwt.const.error.msg.err401}") String err401MsgValue

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
        OK204 = ok204MsgValue;

        OK301 = ok301MsgValue;
        OK302 = ok302MsgValue;
        OK303 = ok303MsgValue;

        ERR001 = new ConstErrorMsg("ERR001", err001MsgValue);
        ERR002 = new ConstErrorMsg("ERR002", err002MsgValue);
        ERR003 = new ConstErrorMsg("ERR003", err003MsgValue);
        ERR004 = new ConstErrorMsg("ERR004", err004MsgValue);
        ERR005 = new ConstErrorMsg("ERR005", err005MsgValue);
        ERR006 = new ConstErrorMsg("ERR006", err006MsgValue);

        ERR101 = new ConstErrorMsg("ERR101", err101MsgValue);
        ERR102 = new ConstErrorMsg("ERR101", err101MsgValue);

        ERR201 = new ConstErrorMsg("ERR201", err201MsgValue);
        ERR202 = new ConstErrorMsg("ERR202", err202MsgValue);
        ERR203 = new ConstErrorMsg("ERR203", err203MsgValue);

        ERR301 = new ConstErrorMsg("ERR301", err301MsgValue);
        ERR302 = new ConstErrorMsg("ERR302", err302MsgValue);
        ERR303 = new ConstErrorMsg("ERR303", err303MsgValue);
        ERR304 = new ConstErrorMsg("ERR304", err304MsgValue);
        ERR305 = new ConstErrorMsg("ERR305", err305MsgValue);

        ERR401 = new ConstErrorMsg("ERR401", err401MsgValue);
    }
}
