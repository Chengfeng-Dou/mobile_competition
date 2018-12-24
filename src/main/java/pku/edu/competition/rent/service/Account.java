package pku.edu.competition.rent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pku.edu.competition.classroom.dao.ClassroomRepository;
import pku.edu.competition.entity.Classroom;
import pku.edu.competition.entity.Point;
import pku.edu.competition.entity.Rent;
import pku.edu.competition.property.service.PropertyService;
import pku.edu.competition.rent.dao.RentRepository;

import java.sql.Timestamp;
import java.util.Date;

@Service
@PropertySource("classpath:app.properties")
public class Account {
    private final ClassroomRepository classroomRepository;
    private final PropertyService propertyService;
    private final RentRepository rentRepository;

    private int[] pointsPerHours = {0, 10, 25, 45, 45};
    private int[] capacityRate = {0, 50, 100, 150};
    private int[] pointLevel = {0, 7, 30};

    @Autowired
    public Account(ClassroomRepository classroomRepository, PropertyService propertyService, RentRepository rentRepository) {
        this.classroomRepository = classroomRepository;
        this.propertyService = propertyService;
        this.rentRepository = rentRepository;
    }

    @Transactional
    RentResponse tryToPay(String userId, String roomId, Date startTime, Date endTime) {
        Point.Type type = getPointType(startTime);
        int pointsShouldPay = getRentRate(startTime, endTime) * getCapRate(roomId);

        System.out.println(type);
        System.out.println(pointsShouldPay);

        if(!propertyService.dropPointsSuccess(userId, type, pointsShouldPay)){
            return RentResponse.INSUFFICIENT_FUNDS;
        }

        Rent rent = new Rent();
        rent.setOwnerId(userId);
        rent.setStartTime(new Timestamp(startTime.getTime()));
        rent.setEndTime(new Timestamp(endTime.getTime()));
        rent.setClassRoomId(roomId);

        rentRepository.saveAndFlush(rent);

        return RentResponse.OK;
    }




    private Point.Type getPointType(Date startTime){
        int days = (int) ((startTime.getTime() - new Date().getTime()) / (1000 * 3600 * 24) + 1);
        System.out.println(days);
        return Point.Type.values()[getRate(days, pointLevel)];
    }


    private int getRentRate(Date startTime, Date endTime) {
        int rentHours =  (int) ((endTime.getTime() - startTime.getTime()) / (1000 * 3600) + 1);
        return pointsPerHours[rentHours];
    }

    private int getCapRate(String roomId) {
        Classroom classroom = classroomRepository.getClassroomById(roomId);
        return getRate(classroom.getCapacity(), capacityRate);
    }

    private int getRate(int value, int[] rates){
        int rate = 0;
        for(Integer item: rates){
            if(value <= item){
                return rate;
            }
            rate ++;
        }
        return rate;
    }

}
