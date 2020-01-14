package pl.miroslawbrz.czartery.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.CreateUserResponse;

public interface UserService {

    ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest);
}
