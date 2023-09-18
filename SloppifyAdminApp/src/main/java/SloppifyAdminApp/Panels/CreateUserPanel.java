package SloppifyAdminApp.Panels;

import SloppifyAdminApp.PanelLogic.CreateUserPanelLogic;

import javax.swing.*;
import java.awt.*;

public class CreateUserPanel extends JPanel {

    private final CreateUserPanelLogic createUserPanelLogic;

    public CreateUserPanel(CreateUserPanelLogic createUserPanelLogic) {
        this.createUserPanelLogic = createUserPanelLogic;

        // Config panel
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 30);

        // Create labels
        JLabel emailLabel = new JLabel("Email: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel roleLabel = new JLabel("Role: ");

        // Config labels
        emailLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        roleLabel.setForeground(Color.WHITE);

        // Add labels
        gbc.gridx = 0; gbc.weightx = 0.01;

        gbc.gridy = 0;
        this.add(emailLabel, gbc);

        gbc.gridy = 1;
        this.add(passwordLabel, gbc);

        gbc.gridy = 2;
        this.add(usernameLabel, gbc);

        gbc.gridy = 3;
        this.add(roleLabel, gbc);

        // Create text fields
        JTextField emailTextField = new JTextField();
        JTextField passwordTextField = new JTextField();
        JTextField usernameTextField = new JTextField();

        // Config text fields
        emailTextField.setForeground(Color.WHITE);
        emailTextField.setBackground(Color.DARK_GRAY);
        passwordTextField.setForeground(Color.WHITE);
        passwordTextField.setBackground(Color.DARK_GRAY);
        usernameTextField.setForeground(Color.WHITE);
        usernameTextField.setBackground(Color.DARK_GRAY);

        // Add text fields
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 1;

        gbc.gridy = 0;
        this.add(emailTextField, gbc);

        gbc.gridy = 1;
        this.add(passwordTextField, gbc);

        gbc.gridy = 2;
        this.add(usernameTextField, gbc);

        // Create combo box
        String[] roles = {"Admin", "User"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);

        // Config combo box
        roleComboBox.setBackground(new Color(31, 214, 98));
        roleComboBox.setForeground(Color.WHITE);
        roleComboBox.setFocusable(false);

        // Add combo box
        gbc.gridx = 1; gbc.gridwidth = 1;

        gbc.gridy = 3;
        this.add(roleComboBox, gbc);

        // Fill space with glue
        gbc.gridx = 2;

        gbc.gridy = 3;
        this.add(Box.createHorizontalGlue(), gbc);

        // Create button
        JButton createButton = new JButton("Create");

        // Config button
        createButton.setBackground(new Color(31, 214, 98));
        createButton.setForeground(Color.WHITE);
        createButton.setFocusable(false);

        // Add logic to button
        createButton.addActionListener(e -> {
            createUserPanelLogic.createUser(
                    emailTextField.getText(),
                    passwordTextField.getText(),
                    usernameTextField.getText(),
                    (String) roleComboBox.getSelectedItem());

            emailTextField.setText("");
            passwordTextField.setText("");
            usernameTextField.setText("");
            roleComboBox.setSelectedItem("Admin");
        });

        // Add button
        gbc.gridx = 3; gbc.gridy = 4; gbc.weighty = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        this.add(createButton, gbc);
    }
}
