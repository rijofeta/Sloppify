package SloppifyAdminApp.Panels;

import SloppifyAdminApp.PanelLogic.OperationPanelLogic;

import javax.swing.*;
import java.awt.*;

public class OperationPanel extends JPanel {

    OperationPanelLogic operationPanelLogic;

    public OperationPanel(OperationPanelLogic operationPanelLogic) {
        this.operationPanelLogic = operationPanelLogic;

        // Set up panel
        this.setPreferredSize(new Dimension(150, 1)); //Setting height changes nothing due to border layout
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Create and config createUserButton
        JButton createUserButton = new JButton("Create User");
        createUserButton.setForeground(new Color(31, 214, 98));
        createUserButton.setBackground(Color.BLACK);
        createUserButton.setFocusable(false);
        createUserButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        createUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createUserButton.setMinimumSize(new Dimension(150, 75));
        createUserButton.setPreferredSize(new Dimension(150, 75));
        createUserButton.setMaximumSize(new Dimension(150, 75));
        createUserButton.addActionListener(e -> operationPanelLogic.createCreateUserInputPanel());

        // Create and config uploadTrackButton
        JButton uploadTrackButton = new JButton("Upload Track");
        uploadTrackButton.setForeground(new Color(31, 214, 98));
        uploadTrackButton.setBackground(Color.BLACK);
        uploadTrackButton.setFocusable(false);
        uploadTrackButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        uploadTrackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadTrackButton.setMinimumSize(new Dimension(150, 75));
        uploadTrackButton.setPreferredSize(new Dimension(150, 75));
        uploadTrackButton.setMaximumSize(new Dimension(150, 75));
        uploadTrackButton.addActionListener(e -> operationPanelLogic.createUploadTrackInputPanel());

        // Add buttons to panel
        this.add(createUserButton);
        this.add(uploadTrackButton);
    }
}
