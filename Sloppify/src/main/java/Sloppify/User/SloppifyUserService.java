package Sloppify.User;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SloppifyUserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG = "User with email %s was not found";
    private SloppifyUserRepository sloppifyUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return sloppifyUserRepository.findByEmail(email).orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email))
        );
    }

    public String signUpUser(SloppifyUser sloppifyUser) {

        boolean userExists = sloppifyUserRepository.findByEmail(sloppifyUser.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("User already exists");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(sloppifyUser.getPassword());
        sloppifyUser.setPassword(encodedPassword);
        sloppifyUser.setEnabled(true);
        sloppifyUserRepository.save(sloppifyUser);

        return "Confirmed";
    }
}
