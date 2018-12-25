package pku.edu.competition.property.controller;

import pku.edu.competition.entity.Point;
import pku.edu.competition.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pku.edu.competition.property.service.PropertyService;
import pku.edu.competition.common.ResponseMsg;
import pku.edu.competition.utils.SecurityUserHelper;

@Controller
@RequestMapping("/property")
public class PropertyController {
    private final PropertyService service;

    @Autowired
    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @RequestMapping(value = "/points", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMsg showPoints() {
        User user = SecurityUserHelper.getCurrentPrincipal();
        ResponseMsg msg = new ResponseMsg();
        msg.data = service.showPointsById(user.getId());

        return msg;
    }


    @RequestMapping(value = "/addPoint", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMsg addPoint(String type, int num) {
        User user = SecurityUserHelper.getCurrentPrincipal();
        ResponseMsg msg = new ResponseMsg();
        if(!service.addPointsSuccess(user.getId(), Point.Type.valueOf(type), num)){
            msg.state = 500;
            msg.description = "internal error!";
        }

        return msg;
    }

    @RequestMapping(value = "/dropPoint", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMsg dropPoint(String type, int num) {
        User user = SecurityUserHelper.getCurrentPrincipal();
        Point.Type pointType = Point.Type.valueOf(type);
        ResponseMsg msg = new ResponseMsg();

        if(!service.dropPointsSuccess(user.getId(), pointType, num)){
            msg.state = 500;
            msg.description = "points is not enough";
        }

        return msg;
    }
}
