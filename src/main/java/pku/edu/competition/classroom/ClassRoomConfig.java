package pku.edu.competition.classroom;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pku.edu.competition.classroom.dao")
public class ClassRoomConfig {

}
