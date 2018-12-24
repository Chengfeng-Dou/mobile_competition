package pku.edu.competition.entity;

import javax.persistence.*;

@Entity
@Table(name = "classrooms")
@IdClass(ClassroomPK.class)
public class Classroom {
    @Column(name = "id")
    private String id;

    @Id
    @Column(name = "building_name")
    private String buildingName;

    @Id
    @Column(name = "room_number")
    private String roomNumber;


    @Column(name = "capacity")
    private int capacity;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
