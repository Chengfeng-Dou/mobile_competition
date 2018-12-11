package authentication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableJpaRepositories(basePackages = {"authentication.dao"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "authentication")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(
                (PasswordEncoder) getApplicationContext().getBean("passwordEncoder")
        );
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()

                .and()

                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?msg=error")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()

                .logout().permitAll();
    }

}
