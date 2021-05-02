package uz.pdp.appnewssite.payload;

import lombok.Data;
import uz.pdp.appnewssite.entity.Role;

@Data
public class UserDTO {

    private String fullName;
    private String username;
    private String password;
    private Role role;
}
