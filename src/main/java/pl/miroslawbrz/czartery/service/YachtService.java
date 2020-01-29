package pl.miroslawbrz.czartery.service;

import org.springframework.http.ResponseEntity;
import pl.miroslawbrz.czartery.api.request.CreateYachtRequest;
import pl.miroslawbrz.czartery.api.response.YachtResponse;
import pl.miroslawbrz.czartery.model.Yacht;

import java.util.List;

public interface YachtService {

    ResponseEntity<YachtResponse> createYacht(CreateYachtRequest yachtRequest, Long charterPlaceId);

    ResponseEntity<List<Yacht>> getYachtsFromCharterPlace(Long id);

    ResponseEntity<YachtResponse> deleteYachtById(Long id);

    ResponseEntity<Yacht> findYachtById(Long id);

    ResponseEntity<YachtResponse> updateYachtData(Long id, CreateYachtRequest request);

    ResponseEntity<YachtResponse> changePricePerDay(Long yachtId, double price);

    ResponseEntity<YachtResponse> changePricePerWeek(Long yachtId, double price);

    ResponseEntity<List<YachtResponse>> giveDiscountForAllYachtsInSingleCharterPlace(Long charterPlaceId, double discountInPercents);
}
