package authentication.service;

import authentication.dao.AuthenticationRepository;
import entity.Point;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import property.dao.PointRepository;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class RegisterService {
    private final AuthenticationRepository repository;
    private final PointRepository pointRepository;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder encoder;

    @Autowired
    public RegisterService(AuthenticationRepository repository, PointRepository pointRepository) {
        this.repository = repository;
        this.pointRepository = pointRepository;
    }

    public boolean register(String id, String userName, String password, User.Role role) {
        User user = new User();
        user.setId(id);
        user.setName(userName);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        try {
            user = repository.saveAndFlush(user);
            createUserAccount(user.getId());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private void createUserAccount(String id) {
        List<Point> pointList = new LinkedList<>();
        for (Point.Type type : Point.Type.values()) {
            Point point = new Point();
            point.setUserId(id);
            point.setType(type);
            point.setNum(0);
            pointList.add(point);
        }
        pointRepository.saveAll(pointList);
        pointRepository.flush();
    }
}
