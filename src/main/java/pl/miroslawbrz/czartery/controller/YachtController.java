package pl.miroslawbrz.czartery.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.miroslawbrz.czartery.model.Yacht;
import pl.miroslawbrz.czartery.service.YachtService;

import java.util.List;

@RestController
@RequestMapping("api/{charterPlaceId}/yachts")
public class YachtController {

    private YachtService yachtService;

    public YachtController(YachtService yachtService) {
        this.yachtService = yachtService;
    }

    @GetMapping
    public List<Yacht> getAll (@PathVariable Long charterPlaceId){
        return yachtService.getYachtsFromCharterPlace(charterPlaceId);
    }

}
