package SloppifyAdminApp.PanelLogic;

import SloppifyAdminApp.Drivers.CreateUserDriver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.*;

public class CreateUserPanelLogic {

    private final CreateUserDriver createUserDriver;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateUserPanelLogic(CreateUserDriver createUserDriver, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.createUserDriver = createUserDriver;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createUser(String email, String password, String username, String role) {
        CreateUserDriver createUserDriver = new CreateUserDriver();
        // Check if email already exists
        if (createUserDriver.findUser(email)) {
            JOptionPane.showMessageDialog(null, "User with email: " + email + " already exists!");
            return;
        }

        // Hash the password
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = bCryptPasswordEncoder.encode(password);

        // Create User
        createUserDriver.createUser(email, hashedPassword, username, role);

        // Show message of success
        JOptionPane.showMessageDialog(null, "User created successfully!");
    }
}
