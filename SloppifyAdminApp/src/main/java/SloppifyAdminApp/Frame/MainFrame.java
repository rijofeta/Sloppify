package SloppifyAdminApp.Frame;

import SloppifyAdminApp.PanelLogic.OperationPanelLogic;
import SloppifyAdminApp.Panels.CreateUserPanel;
import SloppifyAdminApp.Panels.LogoPanel;
import SloppifyAdminApp.Panels.OperationPanel;
import SloppifyAdminApp.Panels.UploadTrackPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        // Set up frame
        this.setTitle("Sloppify Administrator App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("images/sloppify_small_icon.png");
        this.setIconImage(icon.getImage());
    }
}
