package pl.miroslawbrz.czartery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.api.response.CharterPlaceResponse;
import pl.miroslawbrz.czartery.service.CharterPlaceService;

@Controller
@RequestMapping("/api/charterplace")
public class CharterPlaceController {

    private CharterPlaceService charterPlaceService;

    @Autowired
    public CharterPlaceController(CharterPlaceService charterPlaceService) {
        this.charterPlaceService = charterPlaceService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<CharterPlaceResponse> addNewCharterPlaceWithFullAddress(
            @RequestBody CreateCharterPlaceRequest request){
        return null;
    }

}
