package SloppifyAdminApp;

import SloppifyAdminApp.Drivers.CreateUserDriver;
import SloppifyAdminApp.Drivers.UploadTrackDriver;
import SloppifyAdminApp.Frame.MainFrame;
import SloppifyAdminApp.Identifier.Identifier;
import SloppifyAdminApp.PanelLogic.CreateUserPanelLogic;
import SloppifyAdminApp.PanelLogic.OperationPanelLogic;
import SloppifyAdminApp.PanelLogic.UploadTrackPanelLogic;
import SloppifyAdminApp.Panels.CreateUserPanel;
import SloppifyAdminApp.Panels.LogoPanel;
import SloppifyAdminApp.Panels.OperationPanel;
import SloppifyAdminApp.Panels.UploadTrackPanel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Create frame instance where elements will be displayed
        MainFrame mainFrame = new MainFrame();

        // Create and set up all the CreateUserPanel branch
        CreateUserDriver createUserDriver = new CreateUserDriver();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        CreateUserPanelLogic createUserPanelLogic = new CreateUserPanelLogic(createUserDriver, bCryptPasswordEncoder);
        CreateUserPanel createUserPanel = new CreateUserPanel(createUserPanelLogic);

        // Create and set up all the UploadTrackPanel branch
        Identifier identifier = new Identifier();
        UploadTrackDriver uploadTrackDriver = new UploadTrackDriver(identifier);
        UploadTrackPanelLogic uploadTrackPanelLogic = new UploadTrackPanelLogic(uploadTrackDriver);
        UploadTrackPanel uploadTrackPanel = new UploadTrackPanel(uploadTrackPanelLogic);

        // Create and set up all the OperationPanel branch
        OperationPanelLogic operationPanelLogic = new OperationPanelLogic(mainFrame, createUserPanel, uploadTrackPanel);
        OperationPanel operationPanel = new OperationPanel(operationPanelLogic);

        // Create LogoPanel
        LogoPanel logoPanel = new LogoPanel();

        // Add panels to frame
        mainFrame.add(logoPanel, BorderLayout.NORTH);
        mainFrame.add(operationPanel, BorderLayout.WEST);

        // Show frame
        mainFrame.setVisible(true);
    }
}
