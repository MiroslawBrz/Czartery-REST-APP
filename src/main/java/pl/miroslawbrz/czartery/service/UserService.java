package pl.miroslawbrz.czartery.service;

import org.springframework.http.ResponseEntity;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.UserResponse;
import pl.miroslawbrz.czartery.model.User;

import java.util.List;

public interface UserService {

    ResponseEntity<UserResponse> createUser(CreateUserRequest createUserRequest);

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<UserResponse> activateUserInDB(Long id, int hash);

    ResponseEntity<UserResponse> updateUserData(Long id, CreateUserRequest request);

    ResponseEntity<UserResponse> deleteUser(Long id);

}
