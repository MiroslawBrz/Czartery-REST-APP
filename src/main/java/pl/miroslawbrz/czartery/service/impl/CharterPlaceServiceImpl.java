package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.api.response.CharterPlaceResponse;
import pl.miroslawbrz.czartery.common.MsgSource;
import pl.miroslawbrz.czartery.exception.CommonBadRequestException;
import pl.miroslawbrz.czartery.exception.CommonConflictException;
import pl.miroslawbrz.czartery.model.*;
import pl.miroslawbrz.czartery.repository.CharterPlaceRepository;
import pl.miroslawbrz.czartery.repository.UserRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.CharterPlaceService;
import pl.miroslawbrz.czartery.service.UserService;
import pl.miroslawbrz.czartery.utils.AddressJsonParse;
import pl.miroslawbrz.czartery.utils.UserUtilities;

import static pl.miroslawbrz.czartery.common.ValidationUtils.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharterPlaceServiceImpl extends AbstractCommonService implements CharterPlaceService {

    private CharterPlaceRepository charterPlaceRepository;
    private UserRepository userRepository;

    @Autowired
    public CharterPlaceServiceImpl(MsgSource msgSource, CharterPlaceRepository charterPlaceRepository, UserRepository userRepository) {
        super(msgSource);
        this.charterPlaceRepository = charterPlaceRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity<CharterPlaceResponse> createCharterPlace(CreateCharterPlaceRequest request) {

        validateCreateCharterPlaceRequest(request);
        CharterPlace addedCharterPlace = createCharterPlaceFromRequest(request);
        setRelationWithUser(addedCharterPlace);

        return ResponseEntity.ok(new CharterPlaceResponse(msgSource.OK105, addedCharterPlace.getCharterPlaceId()));

    }

    @Override
    public ResponseEntity<List<CharterPlace>> getAllCharterPlaces() {

        List<CharterPlace> charterPlaces = charterPlaceRepository.findAll();
        return ResponseEntity.ok(charterPlaces);
    }

    @Override
    public ResponseEntity<CharterPlace> getCharterPlace(Long id) {
        Optional<CharterPlace> optionalCharterPlace = charterPlaceRepository.findById(id);
        if(!optionalCharterPlace.isPresent()){
            throw new CommonConflictException(msgSource.ERR101);
        }
        return ResponseEntity.ok(optionalCharterPlace.get());
    }



    @Override
    public ResponseEntity<CharterPlaceResponse> updateCharterPlaceData(Long id, CreateCharterPlaceRequest request) {

        validateCreateCharterPlaceRequest(request);
        checkIfCharterPlaceBelongsToLoggedUser(id);
        CharterPlace charterPlace = createCharterPlaceFromRequest(request);
        charterPlace.setCharterPlaceId(id);
        CharterPlace charterAfterSave = charterPlaceRepository.save(charterPlace);
        setRelationWithUser(charterAfterSave);
        return ResponseEntity.ok(new CharterPlaceResponse(msgSource.OK103, charterAfterSave.getCharterPlaceId()));

    }

    @Override
    @Transactional
    public ResponseEntity<CharterPlaceResponse> deleteCharterPlace(Long id) {
        getCharterPlace(id);
        checkIfCharterPlaceBelongsToLoggedUser(id);
        charterPlaceRepository.deleteById(id);
        return ResponseEntity.ok(new CharterPlaceResponse(msgSource.OK102, id));
    }

    @Override
    public ResponseEntity<List<Weather>> showWeatherForecastInCharterPlace(Long charterPlaceId) {
        return null;
    }

    private void validateCreateCharterPlaceRequest(CreateCharterPlaceRequest request){
        if(isNullOrEmpty(request.getAddressBuildingNumber())
                || isNullOrEmpty(request.getAddressCity())
                || isNullOrEmpty(request.getAddressStreet())
                || isNullOrEmpty(request.getCharterPlaceName())) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
    }


    private void setRelationWithUser(CharterPlace charterPlace){

        User user = userRepository.findByUserMail(UserUtilities.getLoggedUser());
        Long charterPlaceId = charterPlace.getCharterPlaceId();
        charterPlaceRepository.setRelationWithUser(user.getUserId(), charterPlaceId);

    }

    public void checkIfCharterPlaceBelongsToLoggedUser(Long charterPlaceId){

        User user = userRepository.findByUserMail(UserUtilities.getLoggedUser());

        Optional<CharterPlace> charterPlaceOptional = user.getCharterPlaceSet().stream()
                .filter(x -> x.getCharterPlaceId().equals(charterPlaceId)).findFirst();
        if(!charterPlaceOptional.isPresent()){
            throw new CommonBadRequestException(msgSource.ERR102);
        }

    }

    private CharterPlace createCharterPlaceFromRequest(CreateCharterPlaceRequest request){

        CharterPlaceAddress charterPlaceAddress = AddressJsonParse.getFullAddressAndCoordinatesFromRequest(request);

        return new CharterPlace.Builder()
                .charterPlaceAddress(charterPlaceAddress)
                .webSiteUrl(request.getWebSiteUrl())
                .charterPlaceName(request.getCharterPlaceName())
                .build();
    }
}
