package tacos.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import tacos.entities.User;

public class SecurityContextUtil {
    private SecurityContextUtil(){};

    public static Long getUserId(){
        return getUser().getId();
    }


    public static User getUser(){
        if (getContext()==null) throw new RuntimeException();
        Authentication authentication=getContext().getAuthentication();
        if (authentication==null) throw new RuntimeException();
User user=(User) authentication.getPrincipal();
        if (user==null) throw new RuntimeException();
        return user;
    }
    public static SecurityContext getContext(){
        return SecurityContextHolder.getContext();
    }

}
