package pku.edu.competition.rent.controller;

import pku.edu.competition.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pku.edu.competition.rent.service.RentResponse;
import pku.edu.competition.rent.service.RentService;
import pku.edu.competition.common.ResponseMsg;
import pku.edu.competition.utils.SecurityUserHelper;

import java.util.Date;

@Controller
@RequestMapping("/rent")
public class RentController {
    private final RentService rentService;

    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @RequestMapping("/rentRoom")
    @ResponseBody
    public ResponseMsg rentClassroom(String roomId, Date startTime, Date endTime) {
        ResponseMsg msg = new ResponseMsg();
        User user = SecurityUserHelper.getCurrentPrincipal();

        RentResponse rentResponse = rentService.rentRoom(user.getId(), roomId, startTime, endTime);
        msg.state = rentResponse == RentResponse.OK ? 200: 500;
        msg.description = rentResponse.name();

        return msg;
    }

    @RequestMapping("/showRentStrips")
    @ResponseBody
    public ResponseMsg getRentStrips(){
        ResponseMsg responseMsg = new ResponseMsg();
        User user = SecurityUserHelper.getCurrentPrincipal();

        responseMsg.data = rentService.getRentStrips(user.getId());

        return responseMsg;
    }
}
