package pku.edu.competition.property.service;

import pku.edu.competition.entity.Point;

import java.util.List;

public interface PropertyService {

    List<Point> showPointsById(String usrId);

    boolean addPointsSuccess(String usrId, Point.Type type, int num);

    boolean dropPointsSuccess(String usrId, Point.Type type, int num);
}
