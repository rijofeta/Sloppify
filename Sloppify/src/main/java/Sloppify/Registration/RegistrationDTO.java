package Sloppify.Registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationDTO {

    private String email;
    private String password;
    private String username;
}
