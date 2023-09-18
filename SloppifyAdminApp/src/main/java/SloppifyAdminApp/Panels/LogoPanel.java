package SloppifyAdminApp.Panels;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    public LogoPanel() {
        ImageIcon logo = new ImageIcon("images/sloppify_icon.png");
        JLabel logoLabel = new JLabel(logo);
        this.add(logoLabel);
        this.setBackground(Color.BLACK);
    }
}
