package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.miroslawbrz.czartery.api.request.CreateYachtRequest;
import pl.miroslawbrz.czartery.api.response.YachtResponse;
import pl.miroslawbrz.czartery.common.MsgSource;
import pl.miroslawbrz.czartery.exception.CommonBadRequestException;
import pl.miroslawbrz.czartery.exception.CommonConflictException;
import pl.miroslawbrz.czartery.model.Yacht;
import pl.miroslawbrz.czartery.repository.YachtRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.YachtService;

import java.util.List;
import java.util.Optional;

import static pl.miroslawbrz.czartery.common.ValidationUtils.*;

@Service
public class YachtServiceImpl extends AbstractCommonService implements YachtService {

    private YachtRepository yachtRepository;

    @Autowired
    public YachtServiceImpl(MsgSource msgSource, YachtRepository yachtRepository) {
        super(msgSource);
        this.yachtRepository = yachtRepository;
    }

    @Override
    public ResponseEntity<YachtResponse> createYacht(CreateYachtRequest yachtRequest, Long charterPlaceId) {

        validateCreateYacht(yachtRequest);
        Yacht yacht = addYachtToDB(yachtRequest);
        setRelationWithCharterPlace(yacht, charterPlaceId);

        return ResponseEntity.ok(new YachtResponse(msgSource.OK201, yacht.getId(), charterPlaceId));
    }

    @Override
    public ResponseEntity<List<Yacht>> getYachtsFromCharterPlace(Long id) {
        return ResponseEntity.ok(yachtRepository.getAllYachtsFromSingleCharterPlace(id));
    }

    @Override
    public ResponseEntity<YachtResponse> deleteYachtById(Long id) {
        Yacht yacht = findYachtById(id).getBody();
        yachtRepository.deleteById(id);
        assert yacht != null;
        return ResponseEntity.ok(new YachtResponse(msgSource.OK202, id));
    }

    @Override
    public ResponseEntity<Yacht> findYachtById(Long id) {
        Optional<Yacht> yachtOptional = yachtRepository.findById(id);
        if(!yachtOptional.isPresent()){
            throw new CommonConflictException(msgSource.ERR201);
        }
        return ResponseEntity.ok(yachtOptional.get());
    }

    @Override
    public ResponseEntity<YachtResponse> updateYachtData(Long id, CreateYachtRequest request) {

        Yacht yacht = findYachtById(id).getBody();
        assert yacht != null;
        yacht.setYachtName(request.getYachtName());
        yacht.setYachtCapacity(request.getYachtCapacity());
        yacht.setYachtDescription(request.getYachtDescription());
        yacht.setYachtLength(request.getYachtLength());
        yacht.setYachtType(request.getYachtType());

        Yacht yachtAfterSave = yachtRepository.save(yacht);
        return ResponseEntity.ok(new YachtResponse(msgSource.OK203, yachtAfterSave.getId()));
    }

    private void setRelationWithCharterPlace(Yacht yacht, Long CharterPlaceId){

    }



    private Yacht addYachtToDB(CreateYachtRequest yachtRequest){

        Yacht yacht = new Yacht.Builder()
                .yachtName(yachtRequest.getYachtName())
                .yachtType(yachtRequest.getYachtType())
                .yachtCapacity(yachtRequest.getYachtCapacity())
                .yachtDescription(yachtRequest.getYachtDescription())
                .yachtLength(yachtRequest.getYachtLength())
                .build();

        return yachtRepository.save(yacht);
    }

    private void validateCreateYacht(CreateYachtRequest yachtRequest){
        if(isNullOrEmpty(yachtRequest.getYachtDescription())
                || isNullOrEmpty(yachtRequest.getYachtName())
                || isNullOrEmpty(yachtRequest.getYachtType())
                || yachtRequest.getYachtCapacity()==0
                || yachtRequest.getYachtLength()==0.0) {

            throw new CommonBadRequestException(msgSource.ERR001);
        }

    }

}
