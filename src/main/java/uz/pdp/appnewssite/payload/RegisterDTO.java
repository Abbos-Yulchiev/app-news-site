package uz.pdp.appnewssite.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotNull
    private String fullName;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String prePassword;
}
