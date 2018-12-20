package classroom.dao;

import entity.Classroom;
import entity.ClassroomPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, ClassroomPK> {

    @Query(value = "select * from classrooms c where c.building_name = ?1", nativeQuery = true)
    List<Classroom> getClassroomsByBuildingName(String buildingName);

    @Query(value = "select distinct building_name from classrooms", nativeQuery = true)
    List<String> getAllBuilding();
}
