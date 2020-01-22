package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.miroslawbrz.czartery.api.request.CreateYachtRequest;
import pl.miroslawbrz.czartery.api.response.YachtResponse;
import pl.miroslawbrz.czartery.common.MsgSource;
import pl.miroslawbrz.czartery.exception.CommonBadRequestException;
import pl.miroslawbrz.czartery.model.Yacht;
import pl.miroslawbrz.czartery.repository.YachtRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.YachtService;

import java.util.List;

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
        yachtRepository.insertIntoYachtCharterPlaceId(charterPlaceId, yacht.getId());

        return ResponseEntity.ok(new YachtResponse(msgSource.OK201, yacht.getId(), charterPlaceId));
    }

    @Override
    public List<Yacht> getYachtsFromCharterPlace(Long id) {
        return yachtRepository.getAllByCharterPlace_CharterPlaceId(id);
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
