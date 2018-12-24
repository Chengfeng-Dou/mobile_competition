package pku.edu.competition.authentication.service;

import pku.edu.competition.authentication.dao.AuthenticationRepository;
import pku.edu.competition.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "authentication")
public class AuthenticationService implements UserDetailsService {

    private AuthenticationRepository authenticationRepository;

    @Autowired
    public void setAuthenticationRepository(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        User user = authenticationRepository.findUserByName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        System.out.println(user.getName() + " " + user.getId() + " " + user.getPassword() + " " + user.getRole().name());

        return user;
    }
}
