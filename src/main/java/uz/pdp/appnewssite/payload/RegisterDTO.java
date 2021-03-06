package uz.pdp.appnewssite.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotNull(message = "Fill the full name")
    private String fullName;

    @NotNull(message = "Fill the username")
    private String username;

    @NotNull(message = "Fill the password")
    private String password;

    @NotNull(message = "Fill the prePassword")
    private String prePassword;

    @NotNull(message = "Fill the role")
    private Long roleId;
}
