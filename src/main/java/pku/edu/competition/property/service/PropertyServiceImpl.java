package pku.edu.competition.property.service;

import pku.edu.competition.entity.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pku.edu.competition.property.dao.PointRepository;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PointRepository repository;

    @Autowired
    public PropertyServiceImpl(PointRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Point> showPointsById(String usrId) {
        return repository.findAllByUserId(usrId);
    }

    @Override
    public boolean addPointsSuccess(String usrId, Point.Type type, int num) {
        try {
            repository.addPointToAccount(type, num, usrId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean dropPointsSuccess(String usrId, Point.Type type, int num) {
        Point point = repository.findByUserIdAndType(usrId, type);

        if (point == null) {
            return false;
        }

        if (point.getNum() < num) {
            return false;
        }
        point.setNum(point.getNum() - num);
        repository.save(point);

        return true;
    }
}
