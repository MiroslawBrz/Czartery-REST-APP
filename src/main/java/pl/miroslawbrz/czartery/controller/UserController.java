package pl.miroslawbrz.czartery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.CreateUserResponse;

@RestController
@RequestMapping(value = "user")
public class UserController {


    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUserAccount(@RequestBody CreateUserRequest request){
        return null;
    }


}
