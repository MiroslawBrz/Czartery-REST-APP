package pl.miroslawbrz.czartery.api.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String userName;
    private String userLastName;
    private String userMail;
    private String userPassword;

}
