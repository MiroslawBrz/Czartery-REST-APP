package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.miroslawbrz.czartery.common.MsgSource;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.CreateUserResponse;
import pl.miroslawbrz.czartery.common.PatternsForValidator;
import pl.miroslawbrz.czartery.exception.CommonBadRequestException;
import pl.miroslawbrz.czartery.exception.CommonConflictException;
import pl.miroslawbrz.czartery.model.CharterPlace;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.repository.UserRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.UserService;

import java.util.List;
import java.util.Optional;

import static pl.miroslawbrz.czartery.common.ValidationUtils.*;


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
        checkUserMailAlreadyExist(createUserRequest.getUserMail());

        User addedUser = addUserToDB(createUserRequest);

        return ResponseEntity.ok(new CreateUserResponse(msgSource.OK001, addedUser.getUserId()));
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> userList = userRepository.findAll();

        return ResponseEntity.ok(userList);
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new CommonConflictException(msgSource.ERR005);
        }
        return ResponseEntity.ok(userOptional.get());
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
        if(isStringValidate(PatternsForValidator.emailPattern, request.getUserMail())){
            throw new CommonBadRequestException(msgSource.ERR002);
        }
        if(isStringValidate(PatternsForValidator.passwordPattern, request.getUserPassword())){
            throw new CommonBadRequestException(msgSource.ERR003);
        }
    }

    private void checkUserMailAlreadyExist(String userMail){

        User user = userRepository.findByUserMail(userMail);

        if(user!=null){
            throw new CommonBadRequestException(msgSource.ERR004);
        }
    }





}
