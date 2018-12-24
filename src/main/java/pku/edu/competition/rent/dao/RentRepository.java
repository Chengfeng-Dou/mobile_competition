package pku.edu.competition.rent.dao;

import pku.edu.competition.entity.Rent;
import pku.edu.competition.entity.RentPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, RentPk> {

    @Query("select r from Rent r where r.classRoomId = ?1 and r.startTime >= ?2 and r.endTime <= ?3")
    List<Rent> getAllRentStripBetweenToDate(String roomId, Timestamp startTime, Timestamp endTime);

    @Query("select r from Rent r where r.ownerId = ?1")
    List<Rent> getRentsByOwnerId(String ownerId);
}
