package pl.miroslawbrz.czartery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.miroslawbrz.czartery.api.request.CreateYachtRequest;
import pl.miroslawbrz.czartery.api.response.YachtResponse;
import pl.miroslawbrz.czartery.model.Yacht;
import pl.miroslawbrz.czartery.service.YachtService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class YachtController {

    private YachtService yachtService;

    @Autowired
    public YachtController(YachtService yachtService) {
        this.yachtService = yachtService;
    }

    @GetMapping("/{charterPlaceId}/yachts")
    public ResponseEntity<List<Yacht>> getAll (@PathVariable Long charterPlaceId){
        return yachtService.getYachtsFromCharterPlace(charterPlaceId);
    }

    @GetMapping("/yachts/{yachtId}")
    public ResponseEntity<Yacht> getYachtById(@PathVariable Long yachtId){
        return yachtService.findYachtById(yachtId);
    }

    @PostMapping("/{charterPlaceId}/yachts")
    public ResponseEntity<YachtResponse> createYacht(@PathVariable Long charterPlaceId, @RequestBody CreateYachtRequest request){
        return yachtService.createYacht(request, charterPlaceId);
    }
    @PutMapping("/yachts/{yachtId}")
    public ResponseEntity<YachtResponse> updateYacht(@PathVariable Long yachtId, @RequestBody CreateYachtRequest request){
        return yachtService.updateYachtData(yachtId, request);
    }

    @DeleteMapping("/yachts/{yachtId}")
    public ResponseEntity<YachtResponse> deleteYacht(@PathVariable Long yachtId){
        return yachtService.deleteYachtById(yachtId);
    }

}
