package authentication.dao;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthenticationRepository extends JpaRepository<User, String> {

    /**
     * 用于查询用户信息
     *
     * @param userName 用户名
     * @return 如果存在该用户，则返回用户信息，否则，返回null
     */
    @Query("select u from User u where u.name = ?1")
    User findUserByName(String userName);

    
}
