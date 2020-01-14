package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.miroslawbrz.czartery.api.common.MsgSource;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.CreateUserResponse;
import pl.miroslawbrz.czartery.exception.CommonBadRequestException;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.repository.UserRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.UserService;

import static pl.miroslawbrz.czartery.api.common.ValidationUtils.*;


@Service
public class UserServiceImpl extends AbstractCommonService implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(MsgSource msgSource, UserRepository userRepository) {
        super(msgSource);
        this.userRepository = userRepository;
    }



    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {
        validateCreateUser(createUserRequest);
        User addedUser = addUserToDB(createUserRequest);
        return ResponseEntity.ok(new CreateUserResponse(msgSource.OK001, addedUser.getUserId()));
    }

    private User addUserToDB(CreateUserRequest request){
        User user = new User.Builder()
                .userName(request.getUserName())
                .userLastName(request.getUserLastName())
                .userMail(request.getUserMail())
                .userPassword(request.getUserPassword())
                .build();

        return userRepository.save(user);
    }

    private void validateCreateUser(CreateUserRequest request){
        if(     isNullOrEmpty(request.getUserName())
                || isNullOrEmpty(request.getUserLastName())
                || isNullOrEmpty(request.getUserMail())
                || isNullOrEmpty(request.getUserPassword()) ) {

            throw new CommonBadRequestException(msgSource.ERR001);
        }
    }



}
