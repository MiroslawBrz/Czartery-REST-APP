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
import pl.miroslawbrz.czartery.model.CharterPlace;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.repository.CharterPlaceRepository;
import pl.miroslawbrz.czartery.repository.UserRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.CharterPlaceService;
import pl.miroslawbrz.czartery.service.UserService;
import pl.miroslawbrz.czartery.utils.AddressJsonParse;

import static pl.miroslawbrz.czartery.common.ValidationUtils.*;

import java.util.List;
import java.util.Optional;

@Service
public class CharterPlaceServiceImpl extends AbstractCommonService implements CharterPlaceService {

    private CharterPlaceRepository charterPlaceRepository;
    private UserService userService;

    @Autowired
    public CharterPlaceServiceImpl(MsgSource msgSource, CharterPlaceRepository charterPlaceRepository, UserService userService) {
        super(msgSource);
        this.charterPlaceRepository = charterPlaceRepository;
        this.userService = userService;
    }


    @Override
    public ResponseEntity<CharterPlaceResponse> createCharterPlace(CreateCharterPlaceRequest request) {

        validateCreateCharterPlaceRequest(request);
        CharterPlace addedCharterPlace = addCharterPlaceToDB(request);

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
    public ResponseEntity<CharterPlaceResponse> updateCharterPlaceAddress(Long id, CharterPlaceAddress address) {

        validateCharterPlaceAddress(address);
        CharterPlace charterPlace = getCharterPlace(id).getBody();
        assert charterPlace != null;

        CreateCharterPlaceRequest request = new CreateCharterPlaceRequest.Builder()
                .addressStreet(address.getAddressStreet())
                .addressCity(address.getAddressCity())
                .addressBuildingNumber(address.getAddressBuildingNumber())
                .charterPlaceName(charterPlace.getCharterPlaceName())
                .build();

        CharterPlace charterPlaceFromRequest = createCharterPlaceFromRequest(request);
        charterPlace.setCharterPlaceAddress(charterPlaceFromRequest.getCharterPlaceAddress());
        charterPlaceRepository.save(charterPlace);

        return ResponseEntity.ok(new CharterPlaceResponse(msgSource.OK101, charterPlace.getCharterPlaceId()));

    }

    @Override
    public ResponseEntity<CharterPlaceResponse> updateCharterPlaceData(Long id, CreateCharterPlaceRequest request) {

        validateCreateCharterPlaceRequest(request);
        CharterPlace charterPlace = createCharterPlaceFromRequest(request);
        charterPlace.setCharterPlaceId(id);
        charterPlaceRepository.save(charterPlace);
        return ResponseEntity.ok(new CharterPlaceResponse(msgSource.OK103, id));

    }

    @Override
    @Transactional
    public ResponseEntity<CharterPlaceResponse> deleteCharterPlace(Long id) {
        getCharterPlace(id);
        charterPlaceRepository.deleteById(id);
        return ResponseEntity.ok(new CharterPlaceResponse(msgSource.OK102, id));
    }

    private void validateCreateCharterPlaceRequest(CreateCharterPlaceRequest request){
        if(isNullOrEmpty(request.getAddressBuildingNumber())
                || isNullOrEmpty(request.getAddressCity())
                || isNullOrEmpty(request.getAddressStreet())
                || isNullOrEmpty(request.getCharterPlaceName())) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
    }

    private void validateCharterPlaceAddress(CharterPlaceAddress address){
        if(isNullOrEmpty(address.getAddressBuildingNumber())
        ||isNullOrEmpty(address.getAddressCity())
        ||isNullOrEmpty(address.getAddressStreet())){
            throw new CommonBadRequestException(msgSource.ERR001);
        }
    }

    private CharterPlace addCharterPlaceToDB(CreateCharterPlaceRequest request){

        return charterPlaceRepository.save(createCharterPlaceFromRequest(request));

    }

    private CharterPlace createCharterPlaceFromRequest(CreateCharterPlaceRequest request){

        CharterPlaceAddress charterPlaceAddress = AddressJsonParse.getFullAddressAndCoordinatesFromRequest(request);
        User user = userService.getUserById(request.getUserId()).getBody();

        return new CharterPlace.Builder()
                .user(user)
                .charterPlaceAddress(charterPlaceAddress)
                .webSiteUrl(request.getWebSiteUrl())
                .charterPlaceName(request.getCharterPlaceName())
                .build();
    }
}
