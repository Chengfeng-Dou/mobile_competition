package pku.edu.competition.rent.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pku.edu.competition.entity.Rent;
import pku.edu.competition.rent.dao.RentRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class RentServiceImpl implements RentService {
    private final DateChecker dateChecker;
    private final ClassRoomChecker roomChecker;
    private final Account account;
    private final RentRepository rentRepository;

    private Lock rentLock = new ReentrantLock();

    @Autowired
    public RentServiceImpl(DateChecker dateChecker, ClassRoomChecker roomChecker, Account account, RentRepository rentRepository) {
        this.dateChecker = dateChecker;
        this.roomChecker = roomChecker;
        this.account = account;
        this.rentRepository = rentRepository;
    }

    @Override
    public RentResponse rentRoom(String userId, String roomId, Date startTime, Date endTime) {
        RentResponse temp = RentResponse.TIME_OUT;
        try {
            rentLock.tryLock(8000, TimeUnit.MILLISECONDS);

            if (!dateChecker.isValidDate(startTime, endTime)) {
                rentLock.unlock();
                return RentResponse.INVALID_DATE;
            }

            temp = roomChecker.checkClassRoom(roomId, startTime, endTime);
            if (temp != RentResponse.OK) {
                rentLock.unlock();
                return temp;
            }


            temp = account.tryToPay(userId, roomId, startTime, endTime);
            rentLock.unlock();
        } catch (InterruptedException ignored) {}

        return temp;
    }

    @Override
    public List<Rent> getRentStrips(String userId) {

        return rentRepository.getRentsByOwnerId(userId);
    }

    @Override
    public List<Rent> getRentStripsByBuildingNameAndDate(String buildingName, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Timestamp startTime = new Timestamp(calendar.getTimeInMillis());
        System.out.println(startTime.toString());

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Timestamp endTime = new Timestamp(calendar.getTimeInMillis());
        System.out.println(endTime.toString());

        return rentRepository.getRentsByBuildingNameAndDate(buildingName, startTime, endTime);
    }


}

