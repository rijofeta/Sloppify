package Sloppify.Registration;

import Sloppify.User.SloppifyUser;
import Sloppify.User.SloppifyUserRole;
import Sloppify.User.SloppifyUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    @Mock
    private SloppifyUserService mockService;

    @InjectMocks
    private RegistrationService underTest;

    private RegistrationDTO registrationDTO;
    private SloppifyUser sloppifyUser;

    @BeforeEach
    void setup() {
        registrationDTO = new RegistrationDTO("layla.rafeira@gmail.com", "ILikeHam", "Layla");
        sloppifyUser = new SloppifyUser("layla.rafeira@gmail.com", "ILikeHam", "Layla", SloppifyUserRole.USER);
    }

    @Test
    void registerCallsServiceSignUpUser() {
        underTest.register(registrationDTO);
        ArgumentCaptor<SloppifyUser> signUpUserCaptor = ArgumentCaptor.forClass(SloppifyUser.class);
        Mockito.verify(mockService).signUpUser(signUpUserCaptor.capture());
        SloppifyUser capturedUser = signUpUserCaptor.getValue();
        assertEquals(sloppifyUser, capturedUser);
    }
}
