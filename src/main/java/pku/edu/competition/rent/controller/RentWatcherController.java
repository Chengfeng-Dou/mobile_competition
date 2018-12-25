package pku.edu.competition.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pku.edu.competition.common.ResponseMsg;
import pku.edu.competition.rent.service.RentService;

import java.util.Date;

@Controller
@RequestMapping("/showRent")
public class RentWatcherController {
    private final RentService rentService;

    @Autowired
    public RentWatcherController(RentService rentService) {
        this.rentService = rentService;
    }

    @RequestMapping("/byBNameAndDate")
    @ResponseBody
    public ResponseMsg showRentByBuildingAndDate(String buildingName,
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.data = rentService.getRentStripsByBuildingNameAndDate(buildingName, date);
        return responseMsg;
    }

}
