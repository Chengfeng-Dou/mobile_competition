package pku.edu.competition.rent;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pku.edu.competition.rent.dao")
public class RentConfig {
}
