package pku.edu.competition.rent.service;


import pku.edu.competition.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pku.edu.competition.rent.dao.RentRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class RentServiceImpl implements RentService {
    private final DateChecker dateChecker;
    private final ClassRoomChecker roomChecker;
    private final Account account;
    private final RentRepository rentRepository;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    @Autowired
    public RentServiceImpl(DateChecker dateChecker, ClassRoomChecker roomChecker, Account account, RentRepository rentRepository) {
        this.dateChecker = dateChecker;
        this.roomChecker = roomChecker;
        this.account = account;
        this.rentRepository = rentRepository;
    }

    @Override
    public RentResponse rentRoom(String userId, String roomId, Date startTime, Date endTime) {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        if(!dateChecker.isValidDate(startTime, endTime)){
            writeLock.unlock();
            return RentResponse.INVALID_DATE;
        }

        RentResponse temp = roomChecker.checkClassRoom(roomId, startTime, endTime);
        if(temp != RentResponse.OK){
            writeLock.unlock();
            return temp;
        }


        temp = account.tryToPay(userId, roomId, startTime, endTime);
        writeLock.unlock();
        return temp;
    }

    @Override
    public List<Rent> getRentStrips(String userId) {
        Lock readLock = readWriteLock.readLock();
        readLock.lock();

        List<Rent> rents = rentRepository.getRentsByOwnerId(userId);

        readLock.unlock();
        return rents;
    }
}
