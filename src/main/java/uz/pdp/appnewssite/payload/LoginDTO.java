package uz.pdp.appnewssite.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotNull(message = "Fill the username")
    private String username;

    @NotNull(message = "Fill the password")
    private String password;
}
