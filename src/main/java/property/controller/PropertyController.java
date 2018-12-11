package property.controller;

import entity.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import property.dao.PointRepository;

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
    public List<Point> showPoints(String id) {
        return repository.findAllByUserId(id);
    }


    @RequestMapping(value = "/addPoint", method = RequestMethod.POST)
    @ResponseBody
    public List<Point> addPoint(String id, String type, int num) {
        try {
            Point.Type pointType = Point.Type.valueOf(type);
            repository.addPointToAccount(pointType, num, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return repository.findAllByUserId(id);
    }

    @RequestMapping(value = "/dropPoint", method = RequestMethod.POST)
    @ResponseBody
    public List<Point> dropPoint(String id, String type, int num) {
        try {
            Point.Type pointType = Point.Type.valueOf(type);
            Point point = repository.findByUserIdAndType(id, pointType);
            if (point.getNum() < num) {
                return null;
            }
            point.setNum(point.getNum() - num);
            repository.saveAndFlush(point);
        } catch (Exception e) {
            return null;
        }

        return repository.findAllByUserId(id);
    }
}
