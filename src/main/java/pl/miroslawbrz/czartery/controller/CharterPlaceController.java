package pl.miroslawbrz.czartery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import pl.miroslawbrz.czartery.api.request.CreateCharterPlaceRequest;
import pl.miroslawbrz.czartery.api.response.CharterPlaceResponse;
import pl.miroslawbrz.czartery.model.CharterPlace;
import pl.miroslawbrz.czartery.model.CharterPlaceAddress;
import pl.miroslawbrz.czartery.service.CharterPlaceService;

import java.util.List;

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
        return charterPlaceService.createCharterPlace(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharterPlace> getCharterPlaceById(@PathVariable Long id){
        return charterPlaceService.getCharterPlace(id);
    }

    @GetMapping
    public ResponseEntity<List<CharterPlace>> getAllCharterPlaces(){
        return charterPlaceService.getAllCharterPlaces();
    }


    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CharterPlaceResponse> updateCharterPlace(
            @PathVariable Long id,
            @RequestBody CreateCharterPlaceRequest request
    ){
        return charterPlaceService.updateCharterPlaceData(id, request);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CharterPlaceResponse> deleteCharterPlace(@PathVariable Long id){
        return charterPlaceService.deleteCharterPlace(id);
    }


}
