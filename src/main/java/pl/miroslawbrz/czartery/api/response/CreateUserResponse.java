package pl.miroslawbrz.czartery.api.response;


import pl.miroslawbrz.czartery.api.BasicResponse;


public class CreateUserResponse extends BasicResponse {

    private Long id;

    public CreateUserResponse(){}

    public CreateUserResponse(String responseMsg, Long id) {
        super(responseMsg);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
