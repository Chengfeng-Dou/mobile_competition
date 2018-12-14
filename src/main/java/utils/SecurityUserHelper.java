package utils;

import entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUserHelper {

    private static Authentication getCurrentUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public static User getCurrentPrincipal(){
        return (User) getCurrentUserAuthentication().getPrincipal();
    }
}
