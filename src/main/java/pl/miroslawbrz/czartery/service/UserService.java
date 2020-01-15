package pl.miroslawbrz.czartery.service;

import org.springframework.http.ResponseEntity;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.CreateUserResponse;
import pl.miroslawbrz.czartery.api.response.UpdateUserResponse;
import pl.miroslawbrz.czartery.model.User;

import java.util.List;

public interface UserService {

    ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest);

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<UpdateUserResponse> activateUserInDB(Long id, int hash);

}
