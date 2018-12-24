package pku.edu.competition.rent.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@PropertySource("classpath:app.properties")
public class DateChecker {
    @Value("${rent.maximumContinuousTimeInterval}")
    private int MCTI;


    boolean isValidDate(Date startTime, Date endTime){
        if (startTime.getTime() >= endTime.getTime()) { // 如果开始使用的时间晚于结束使用的时间
            return false;
        }

        return (endTime.getTime() - startTime.getTime()) / (1000 * 3600) <= MCTI;
    }
}
