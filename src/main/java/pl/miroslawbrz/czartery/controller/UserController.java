package pl.miroslawbrz.czartery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.UserResponse;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<UserResponse> createUserAccount(@RequestBody CreateUserRequest request){

        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){

        return userService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        return userService.getAllUsers();
    }

    @PutMapping("/{id}/{activationHash}")
    public ResponseEntity<UserResponse> updateUserActivation(
            @PathVariable Long id,
            @PathVariable int activationHash){

        return userService.activateUserInDB(id, activationHash);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody CreateUserRequest request){

        return userService.updateUserData(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id){

        return userService.deleteUser(id);
    }
}
