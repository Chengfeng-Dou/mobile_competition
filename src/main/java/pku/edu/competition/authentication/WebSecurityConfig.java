package pku.edu.competition.authentication;


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
@EnableJpaRepositories(basePackages = {"pku.edu.competition.authentication.dao"})
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
                .antMatchers("property/*").hasAnyAuthority("admin", "student", "teacher")
                .antMatchers("rent/*").hasAnyAuthority("admin", "student", "teacher")

                .and()

                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/loginFailed")
                    .defaultSuccessUrl("/loginSuccess")
                    .permitAll()
                .and()

                .logout().permitAll();
    }

}
