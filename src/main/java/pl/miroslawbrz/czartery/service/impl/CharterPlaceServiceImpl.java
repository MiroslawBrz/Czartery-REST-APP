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
import pl.miroslawbrz.czartery.model.Yacht;
import pl.miroslawbrz.czartery.repository.CharterPlaceRepository;
import pl.miroslawbrz.czartery.repository.UserRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.CharterPlaceService;
import pl.miroslawbrz.czartery.service.UserService;
import pl.miroslawbrz.czartery.utils.AddressJsonParse;

import static pl.miroslawbrz.czartery.common.ValidationUtils.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharterPlaceServiceImpl extends AbstractCommonService implements CharterPlaceService {

    private CharterPlaceRepository charterPlaceRepository;

    @Autowired
    public CharterPlaceServiceImpl(MsgSource msgSource, CharterPlaceRepository charterPlaceRepository) {
        super(msgSource);
        this.charterPlaceRepository = charterPlaceRepository;
    }


    @Override
    public ResponseEntity<CharterPlaceResponse> createCharterPlace(CreateCharterPlaceRequest request) {

        validateCreateCharterPlaceRequest(request);
        CharterPlace addedCharterPlace = createCharterPlaceFromRequest(request);

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
        CharterPlace charterPlace = createCharterPlaceFromRequest(request);
        charterPlace.setCharterPlaceId(id);
        CharterPlace charterAfterSave = charterPlaceRepository.save(charterPlace);
        setRelationWithUser(charterAfterSave, request.getUserId());
        return ResponseEntity.ok(new CharterPlaceResponse(msgSource.OK103, charterAfterSave.getCharterPlaceId()));

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


    private void setRelationWithUser(CharterPlace charterPlace, Long userId){

        Long charterPlaceId = charterPlace.getCharterPlaceId();
        charterPlaceRepository.setRelationWithUser(userId, charterPlaceId);

    }

    private CharterPlace createCharterPlaceFromRequest(CreateCharterPlaceRequest request){

        CharterPlaceAddress charterPlaceAddress = AddressJsonParse.getFullAddressAndCoordinatesFromRequest(request);

        CharterPlace charterPlace = new CharterPlace.Builder()
                .charterPlaceAddress(charterPlaceAddress)
                .webSiteUrl(request.getWebSiteUrl())
                .charterPlaceName(request.getCharterPlaceName())
                .build();

        return charterPlace;
    }
}
