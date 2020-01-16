package pl.miroslawbrz.czartery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.UpdateUserResponse;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity createUserAccount(@RequestBody CreateUserRequest request){

        return userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){

        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){

        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}/{activationHash}")
    public ResponseEntity<UpdateUserResponse> updateUserActivation(
            @PathVariable Long id,
            @PathVariable int activationHash){

        return userService.activateUserInDB(id, activationHash);
    }

}
