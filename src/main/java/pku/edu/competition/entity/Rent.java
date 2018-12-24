package pku.edu.competition.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rent")
@IdClass(RentPk.class)
public class Rent {
    @Column(name = "rent_id")
    private int rentId;

    @Id
    @Column(name = "owner_id")
    private String ownerId;

    @Id
    @Column(name = "class_room_id")
    private String classRoomId;

    @Id
    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;




    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "rentId=" + rentId +
                ", ownerId='" + ownerId + '\'' +
                ", classRoomId='" + classRoomId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
