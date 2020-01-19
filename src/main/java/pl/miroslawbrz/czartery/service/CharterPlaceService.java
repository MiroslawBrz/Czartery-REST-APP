package pl.miroslawbrz.czartery.service;

import org.springframework.http.ResponseEntity;
import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.api.request.CreateUserRequest;
import pl.miroslawbrz.czartery.api.response.CharterPlaceResponse;
import pl.miroslawbrz.czartery.api.response.UserResponse;
import pl.miroslawbrz.czartery.model.CharterPlace;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;
import pl.miroslawbrz.czartery.model.User;

import java.util.List;

public interface CharterPlaceService {

    ResponseEntity<CharterPlaceResponse> createCharterPlace(CreateCharterPlaceRequest request);

    ResponseEntity<List<CharterPlace>> getAllCharterPlaces();

    ResponseEntity<CharterPlace> getCharterPlace(Long id);

    ResponseEntity<CharterPlaceResponse> updateCharterPlaceAddress(Long id, CharterPlaceAddress address);

    ResponseEntity<CharterPlaceResponse> updateCharterPlaceData(Long id, CreateCharterPlaceRequest request);

    ResponseEntity<CharterPlaceResponse> deleteCharterPlace(Long id);

}
