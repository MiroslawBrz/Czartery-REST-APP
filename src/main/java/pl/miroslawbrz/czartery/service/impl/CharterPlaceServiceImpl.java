package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.miroslawbrz.czartery.common.MsgSource;
import pl.miroslawbrz.czartery.repository.CharterPlaceRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.CharterPlaceService;

@Service
public class CharterPlaceServiceImpl extends AbstractCommonService implements CharterPlaceService {

    private CharterPlaceRepository charterPlaceRepository;

    @Autowired
    public CharterPlaceServiceImpl(MsgSource msgSource, CharterPlaceRepository charterPlaceRepository) {
        super(msgSource);
        this.charterPlaceRepository = charterPlaceRepository;
    }


}
