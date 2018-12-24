package pku.edu.competition.rent.service;

import pku.edu.competition.classroom.dao.ClassroomRepository;
import pku.edu.competition.entity.Classroom;
import pku.edu.competition.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pku.edu.competition.rent.dao.RentRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ClassRoomChecker {
    private final ClassroomRepository classroomRepository;
    private final RentRepository rentRepository;

    @Autowired
    public ClassRoomChecker(ClassroomRepository classroomRepository, RentRepository rentRepository) {
        this.classroomRepository = classroomRepository;
        this.rentRepository = rentRepository;
    }

    RentResponse checkClassRoom(String roomId, Date startTime, Date endTime){
        if(!hasThisClassRoom(roomId)){
            return RentResponse.NO_SUCH_ROOM;
        }

        if(!classroomIsAvailable(roomId, startTime, endTime)){
            return RentResponse.CANNOT_RENT_ROOM;
        }

        return RentResponse.OK;
    }

    private boolean hasThisClassRoom(String roomId){
        Classroom classroom = classroomRepository.getClassroomById(roomId);
        return classroom != null;
    }

    private boolean classroomIsAvailable(String roomId, Date startTime, Date endTime){
        List<Rent> rents = rentRepository.getAllRentStripBetweenToDate(
                roomId, new Timestamp(startTime.getTime()), new Timestamp(endTime.getTime())
        );
        return rents.isEmpty();
    }


}
