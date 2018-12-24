package pku.edu.competition.property.dao;

import pku.edu.competition.entity.Point;
import pku.edu.competition.entity.PointPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PointRepository extends JpaRepository<Point, PointPk> {

    @Query("select p from Point p where p.userId = ?1")
    List<Point> findAllByUserId(String userId);

    @Modifying
    @Transactional
    @Query("update Point p set p.num = p.num + ?2 where p.userId = ?3 and p.type = ?1")
    void addPointToAccount(Point.Type type, int num, String userId);

    Point findByUserIdAndType(String userId, Point.Type type);
}
