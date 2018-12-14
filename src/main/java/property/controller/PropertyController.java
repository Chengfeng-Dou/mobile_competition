package property.controller;

import entity.Point;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import property.dao.PointRepository;
import utils.SecurityUserHelper;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/property")
public class PropertyController {
    private final PointRepository repository;

    @Autowired
    public PropertyController(PointRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/points", method = RequestMethod.POST)
    @ResponseBody
    public List<Point> showPoints() {
        User user = SecurityUserHelper.getCurrentPrincipal();
        return repository.findAllByUserId(user.getId());
    }


    @RequestMapping(value = "/addPoint", method = RequestMethod.POST)
    @ResponseBody
    public List<Point> addPoint(String type, int num) {
        User user = SecurityUserHelper.getCurrentPrincipal();
        try {
            Point.Type pointType = Point.Type.valueOf(type);
            repository.addPointToAccount(pointType, num, user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return repository.findAllByUserId(user.getId());
    }

    @RequestMapping(value = "/dropPoint", method = RequestMethod.POST)
    @ResponseBody
    public List<Point> dropPoint(String type, int num) {
        User user = SecurityUserHelper.getCurrentPrincipal();

        Point.Type pointType = Point.Type.valueOf(type);
        Point point = repository.findByUserIdAndType(user.getId(), pointType);

        if (point.getNum() < num) {
            return new LinkedList<>();
        }
        point.setNum(point.getNum() - num);
        repository.saveAndFlush(point);

        return repository.findAllByUserId(user.getId());
    }
}
