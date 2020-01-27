package pl.miroslawbrz.czartery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.miroslawbrz.czartery.api.request.CreateReservationRequest;
import pl.miroslawbrz.czartery.api.response.ReservationResponse;
import pl.miroslawbrz.czartery.common.MsgSource;
import pl.miroslawbrz.czartery.exception.CommonBadRequestException;
import pl.miroslawbrz.czartery.exception.CommonConflictException;
import pl.miroslawbrz.czartery.model.ReservationDetails;
import pl.miroslawbrz.czartery.model.Yacht;
import pl.miroslawbrz.czartery.repository.ReservationRepository;
import pl.miroslawbrz.czartery.repository.YachtRepository;
import pl.miroslawbrz.czartery.service.AbstractCommonService;
import pl.miroslawbrz.czartery.service.ReservationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.miroslawbrz.czartery.common.ValidationUtils.isNullOrEmpty;

@Service
public class ReservationServiceImpl extends AbstractCommonService implements ReservationService {

    private ReservationRepository reservationRepository;
    private YachtRepository yachtRepository;

    @Autowired
    public ReservationServiceImpl(MsgSource msgSource, ReservationRepository reservationRepository, YachtRepository yachtRepository) {
        super(msgSource);
        this.reservationRepository = reservationRepository;
        this.yachtRepository = yachtRepository;
    }




    @Override
    public ResponseEntity<List<LocalDate>> getAllReservedDaysOfYacht(Long yachtId) {
        List<ReservationDetails> reservationDetailsList = reservationRepository.getAllReservationsOfYacht(yachtId);

        List<LocalDate> dateList = new ArrayList<>();

        for(ReservationDetails reservationDetails : reservationDetailsList){
            for(int i = 0; i<reservationDetails.getReservationLength(); i++){
                dateList.add(reservationDetails.getLocalDate().plusDays(i));
            }
        }

        return ResponseEntity.ok(dateList);
    }

    @Override
    public ResponseEntity<List<ReservationDetails>> getAllReservations() {
        List<ReservationDetails> reservationDetailsList = reservationRepository.findAll();
        return ResponseEntity.ok(reservationDetailsList);
    }

    @Override
    public ResponseEntity<ReservationResponse> addReservation(CreateReservationRequest request, Long YachtId) {

        createReservationRequestValidate(request);
        checkIfBookingHasCorrectDate(request);
        checkIfBookingWithinThisPeriodIsPossible(request);
        ReservationDetails reservationDetails = createReservationDetailsFromRequest(request);
        ReservationDetails reservationAfterSave = reservationRepository.save(reservationDetails);


        return ResponseEntity.ok(new ReservationResponse(msgSource.OK301, reservationAfterSave.getReservationId()));

    }

    @Override
    public ResponseEntity<ReservationResponse> changeReservation(Long reservationId, CreateReservationRequest request) {

        createReservationRequestValidate(request);
        checkIfBookingHasCorrectDate(request);
        checkIfBookingWithinThisPeriodIsPossible(request);

        ReservationDetails oldReservation = getReservationDetails(reservationId).getBody();

        assert oldReservation != null;
        oldReservation.setLocalDate(request.getLocalDate());
        oldReservation.setReservationLength(request.getReservationLength());

        ReservationDetails afterSaveReservation = reservationRepository.save(oldReservation);

        return ResponseEntity.ok(new ReservationResponse(msgSource.OK302, afterSaveReservation.getReservationId()));

    }

    @Override
    public ResponseEntity<ReservationDetails> getReservationDetails(Long reservationId) {

        Optional<ReservationDetails> optional = reservationRepository.findById(reservationId);
        if(!optional.isPresent()){
            throw new CommonConflictException(msgSource.ERR303);
        }

        return ResponseEntity.ok(optional.get());

    }

    @Override
    public ResponseEntity<ReservationResponse> deleteReservation(Long reservationId) {
        getReservationDetails(reservationId);

        reservationRepository.deleteById(reservationId);

        Optional<ReservationDetails> optional = reservationRepository.findById(reservationId);
        if(optional.isPresent()){
            throw new CommonConflictException(msgSource.ERR304);
        }

        return ResponseEntity.ok(new ReservationResponse(msgSource.OK303, reservationId));
    }

    private void createReservationRequestValidate(CreateReservationRequest request){

        if(     isNullOrEmpty(request.getLocalDate().toString())
                || request.getReservationLength()>0
                || request.getYachtId()>0)
        {
            throw new CommonBadRequestException(msgSource.ERR001);
        }

        checkIfYachtExist(request);
        checkIfBookingHasCorrectDate(request);
        checkIfBookingWithinThisPeriodIsPossible(request);

    }



    private void checkIfBookingWithinThisPeriodIsPossible(CreateReservationRequest request){

        List<LocalDate> reservedDaysList = getAllReservedDaysOfYacht(request.getYachtId()).getBody();

        List<LocalDate> reservationDaysList = new ArrayList<>();

        for(int i = 0; i<request.getReservationLength(); i++){
            reservationDaysList.add(request.getLocalDate().plusDays(i));
        }

        for(LocalDate resLocalDate: reservationDaysList){
            if(reservedDaysList==null){
                break;
            }
            for(LocalDate localDate: reservedDaysList){
                if(localDate.equals(resLocalDate)){
                    throw new CommonConflictException(msgSource.ERR302);
                }
            }
        }
    }

    private void checkIfBookingHasCorrectDate(CreateReservationRequest request){

        LocalDate requestLocalDate = request.getLocalDate();
        LocalDate presentLocalDate = LocalDate.now();
        if(requestLocalDate.isBefore(presentLocalDate)){
            throw new CommonConflictException(msgSource.ERR301);
        }

    }

    private void checkIfYachtExist(CreateReservationRequest request){

        Optional<Yacht> yachtOptional = yachtRepository.findById(request.getYachtId());
        if(!yachtOptional.isPresent()){
            throw new CommonConflictException(msgSource.ERR201);
        }

    }

    private ReservationDetails createReservationDetailsFromRequest(CreateReservationRequest request){

        ReservationDetails reservationDetails = new ReservationDetails();
        reservationDetails.setLocalDate(request.getLocalDate());
        reservationDetails.setReservationLength(request.getReservationLength());

        return reservationDetails;

    }

}
