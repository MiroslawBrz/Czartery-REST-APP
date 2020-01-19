package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pl.miroslawbrz.czartery.common.MsgSource;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.UserResponse;
import pl.miroslawbrz.czartery.constans.PatternsForValidator;
import pl.miroslawbrz.czartery.exception.CommonBadRequestException;
import pl.miroslawbrz.czartery.exception.CommonConflictException;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.repository.UserRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.UserService;
import pl.miroslawbrz.czartery.utils.Mail;

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
    public ResponseEntity<UserResponse> createUserAndSendMail(CreateUserRequest createUserRequest) {

        validateCreateUser(createUserRequest);
        checkUserMailAlreadyExist(createUserRequest.getUserMail());

        User addedUser = addUserToDB(createUserRequest);
        Mail.sendEmail(addedUser);
        return ResponseEntity.ok(new UserResponse(msgSource.OK001, addedUser.getUserId()));
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

    @Override
    public ResponseEntity<UserResponse> activateUserInDB(Long id, int hash) {
        User user = getUserById(id).getBody();
        assert user != null;
        checkIsActivationHashIsCorrect(user, hash);
        user.setUserActive(true);
        user.setActivationHash(null);
        userRepository.save(user);
        return ResponseEntity.ok(new UserResponse(msgSource.OK002, user.getUserId()));
    }

    @Override
    public ResponseEntity<UserResponse> updateUserData(Long id, CreateUserRequest request) {
        User user = getUserById(id).getBody();
        validateCreateUser(request);
        assert user != null;
        user.setUserName(request.getUserName());
        user.setUserLastName(request.getUserLastName());
        user.setUserPassword(request.getUserPassword());
        if(!user.getUserMail().equals(request.getUserMail())){
            checkUserMailAlreadyExist(request.getUserMail());
            user.setUserMail(request.getUserMail());
        }
        userRepository.save(user);
        return ResponseEntity.ok(new UserResponse(msgSource.OK003, user.getUserId()));
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponse> deleteUser(Long id) {
        getUserById(id); //jeśli nie ma usera o takim id to ERR005
        userRepository.deleteById(id);
        return ResponseEntity.ok(new UserResponse(msgSource.OK004, id));
    }


    private User addUserToDB(CreateUserRequest request){
        User user = new User.Builder()
                .userName(request.getUserName())
                .userLastName(request.getUserLastName())
                .userMail(request.getUserMail())
                .userPassword(request.getUserPassword())
                .build();

        user.setActivationHash(user.hashCode());

        return userRepository.save(user);
    }

    private void validateCreateUser(CreateUserRequest request){
        if(     isNullOrEmpty(request.getUserName())
                || isNullOrEmpty(request.getUserLastName())
                || isNullOrEmpty(request.getUserMail())
                || isNullOrEmpty(request.getUserPassword()) ) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
        if(!isStringValidate(PatternsForValidator.emailPattern, request.getUserMail())){
            throw new CommonBadRequestException(msgSource.ERR002);
        }
        if(!isStringValidate(PatternsForValidator.passwordPattern, request.getUserPassword())){
            throw new CommonBadRequestException(msgSource.ERR003);
        }
    }

    private void checkUserMailAlreadyExist(String userMail){

        User user = userRepository.findByUserMail(userMail);

        if(user!=null){
            throw new CommonConflictException(msgSource.ERR004);
        }
    }

    private void checkIsActivationHashIsCorrect(User user, int hash){

        if(user.getActivationHash()!=hash){
            throw new CommonConflictException(msgSource.ERR006);
        }

    }





}
