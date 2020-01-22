package pl.miroslawbrz.czartery.service;

import org.springframework.http.ResponseEntity;
import pl.miroslawbrz.czartery.api.request.CreateYachtRequest;
import pl.miroslawbrz.czartery.api.response.YachtResponse;
import pl.miroslawbrz.czartery.model.Yacht;

import java.util.List;

public interface YachtService {

    public ResponseEntity<YachtResponse> createYacht(CreateYachtRequest yachtRequest, Long charterPlaceId);

    public ResponseEntity<List<Yacht>> getYachtsFromCharterPlace(Long id);

    public ResponseEntity<YachtResponse> deleteYachtById(Long id);

    public ResponseEntity<Yacht> findYachtById(Long id);

    public ResponseEntity<YachtResponse> updateYachtData(Long id, CreateYachtRequest request);
}
