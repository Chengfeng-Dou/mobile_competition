package pku.edu.competition.rent.service;

import pku.edu.competition.entity.Rent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceTest {
    @Autowired
    private RentServiceImpl service;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

    @Test
    public void rentRoom() throws ParseException {
        String id = "1801210818";
        String roomId = "研发楼3101";
        Date startTime = sdf.parse("2018-12-28 12:00:00");
        Date endTime = sdf.parse("2018-12-28 15:00:00");

        RentResponse rentResponse = service.rentRoom(id, roomId, startTime, endTime);
        assertEquals(RentResponse.OK, rentResponse);

    }

    @Test
    public void getRentStrips() {
        String id = "1801210818";
        List<Rent> rents = service.getRentStrips(id);
        assertEquals(rents.size(), 3);
        System.out.println(Arrays.toString(rents.toArray()));
    }

    @Test
    public void rentRoomOutOfDate() throws ParseException{
        String id = "1801210818";
        String roomId = "研发楼3101";
        Date startTime = sdf.parse("2019-1-10 12:00:00");
        Date endTime = sdf.parse("2019-1-10 15:00:00");

        RentResponse rentResponse = service.rentRoom(id, roomId, startTime, endTime);
        assertEquals(RentResponse.OK, rentResponse);
    }
}