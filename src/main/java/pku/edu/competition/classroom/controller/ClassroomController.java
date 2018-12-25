package pku.edu.competition.classroom.controller;

import pku.edu.competition.classroom.dao.ClassroomRepository;
import pku.edu.competition.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pku.edu.competition.common.ResponseMsg;

import java.util.List;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {
    private final ClassroomRepository repository;

    @Autowired
    public ClassroomController(ClassroomRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/showByBName")
    @ResponseBody
    public ResponseMsg showClassRoomByBuildingName(String buildingName) {
        ResponseMsg msg = new ResponseMsg();
        msg.state = 200;
        msg.description = "request success!";
        msg.data = repository.getClassroomsByBuildingName(buildingName);
        return msg;
    }

    @RequestMapping("/buildings")
    @ResponseBody
    public List<String> getAllBuildings(){
        return repository.getAllBuilding();
    }

    @ResponseBody
    @RequestMapping("/addClassroom")
    public ResponseMsg addClassroom(String buildingName, String roomNumber, int capacity){
        Classroom classroom = new Classroom();
        classroom.setBuildingName(buildingName);
        classroom.setRoomNumber(roomNumber);
        classroom.setCapacity(capacity);
        classroom.setId(buildingName + roomNumber);

        ResponseMsg msg = new ResponseMsg();
        try{
            repository.saveAndFlush(classroom);
            msg.state = 200;
            msg.description = "ok";

        }catch (Exception e){
            msg.state = 500;
            msg.description = "pku.edu.competition.classroom has already existed";
        }

        return msg;
    }
}
