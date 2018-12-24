package pku.edu.competition.rent.service;

import pku.edu.competition.entity.Rent;

import java.util.Date;
import java.util.List;

public interface RentService {

    /**
     * 用于预定一个房间
     * @param userId 用户的id
     * @param roomId 教室的id
     * @param startTime 开始使用的时间
     * @param endTime 结束使用的时间
     * @return 预定返回信息
     */
    RentResponse rentRoom(String userId, String roomId, Date startTime, Date endTime);



    List<Rent> getRentStrips(String userId);

}
