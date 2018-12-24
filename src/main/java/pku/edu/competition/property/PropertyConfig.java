package pku.edu.competition.property;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pku.edu.competition.property.dao")
public class PropertyConfig {

}
