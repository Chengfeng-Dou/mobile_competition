package authentication.service;

import authentication.dao.AuthenticationRepository;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final AuthenticationRepository repository;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public RegisterService(AuthenticationRepository repository) {
        this.repository = repository;
    }

    public boolean register(String id, String userName, String password) {
        User user = new User();
        user.setId(id);
        user.setName(userName);
        user.setPassword(encoder.encode(password));
        user.setRole(User.Role.student);
        try {
            repository.saveAndFlush(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
