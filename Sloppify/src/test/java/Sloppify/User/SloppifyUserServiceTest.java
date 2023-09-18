package Sloppify.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SloppifyUserServiceTest {

    @Mock
    private SloppifyUserRepository mockRepo;

    @Spy
    private BCryptPasswordEncoder spyEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private SloppifyUserService underTest;

    private SloppifyUser user;

    @BeforeEach
    void setup() {
        user = new SloppifyUser(
                "layla.rafeira@gmail.com",
                "ILikeHam",
                "Layla",
                SloppifyUserRole.USER
        );
    }

    @Test
    void loadUserByUsernameCallsFindByEmail() {
        Mockito.when(mockRepo.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        underTest.loadUserByUsername(user.getEmail());
        ArgumentCaptor<String> findByEmailArgumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(mockRepo).findByEmail(findByEmailArgumentCaptor.capture());
        String capturedEmail = findByEmailArgumentCaptor.getValue();
        assertEquals(user.getEmail(), capturedEmail);
    }

    @Test
    void loadUserByUsernameThrowsUsernameNotFoundException() {
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> underTest.loadUserByUsername(user.getEmail())
        );
        assertEquals(exception.getMessage(), "User with email " + user.getEmail() + " was not found");
    }

    @Test
    void signUpUserCallsFindByEmail() {
        underTest.signUpUser(user);
        ArgumentCaptor<String> findByEmailArgumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(mockRepo).findByEmail(findByEmailArgumentCaptor.capture());
        String capturedEmail = findByEmailArgumentCaptor.getValue();
        assertEquals(user.getEmail(), capturedEmail);
    }

    @Test
    void signUpUserCallsPasswordEncoder() {
        String rawPassword = user.getPassword();
        underTest.signUpUser(user);
        ArgumentCaptor<String> encodeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(spyEncoder).encode(encodeArgumentCaptor.capture());
        String capturedPassword = encodeArgumentCaptor.getValue();
        assertEquals(rawPassword, capturedPassword);

    }

    @Test
    void signUpUserHashedPasswordMatches() {
        String rawPassword = user.getPassword();
        underTest.signUpUser(user);
        assertTrue(spyEncoder.matches(rawPassword, user.getPassword()));
    }

    @Test
    void signUpUserThrowsIllegalStateException() {
        Mockito.when(mockRepo.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> underTest.signUpUser(user)
        );
        assertEquals(exception.getMessage(), "User already exists");
    }

    @Test
    void signUpUserCallsSave() {
        underTest.signUpUser(user);
        ArgumentCaptor<SloppifyUser> saveArgumentCaptor = ArgumentCaptor.forClass(SloppifyUser.class);
        Mockito.verify(mockRepo).save(saveArgumentCaptor.capture());
        SloppifyUser capturedUser = saveArgumentCaptor.getValue();
        assertEquals(user, capturedUser);
    }

}