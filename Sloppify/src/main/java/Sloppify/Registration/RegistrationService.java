package Sloppify.Registration;

import Sloppify.User.SloppifyUser;
import Sloppify.User.SloppifyUserRole;
import Sloppify.User.SloppifyUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final SloppifyUserService sloppifyUserService;

    public String register(RegistrationDTO registrationDTO) {

        return sloppifyUserService.signUpUser(
                new SloppifyUser(
                        registrationDTO.getEmail(),
                        registrationDTO.getPassword(),
                        registrationDTO.getUsername(),
                        SloppifyUserRole.USER
                )
        );
    }
}
