package uz.pdp.appnewssite.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.ecxeptions.ForbiddenException;
import uz.pdp.appnewssite.entity.User;

@Component
@Aspect
public class CheckPermissionExecute {

    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.values())) {
                exist = true;
                break;
            }
        }
        if (!exist)
            throw  new ForbiddenException(checkPermission.values(), "Ruxsat yo'q");
    }
}
