package SloppifyAdminApp.PanelLogic;

import SloppifyAdminApp.Panels.CreateUserPanel;
import SloppifyAdminApp.Panels.UploadTrackPanel;

import javax.swing.*;
import java.awt.*;

public class OperationPanelLogic {

    private final JFrame mainFrame;
    private final CreateUserPanel createUserPanel;
    private final UploadTrackPanel uploadTrackPanel;

    public OperationPanelLogic(JFrame mainFrame, CreateUserPanel createUserPanel, UploadTrackPanel uploadTrackPanel) {
        this.mainFrame = mainFrame;
        this.createUserPanel = createUserPanel;
        this.uploadTrackPanel = uploadTrackPanel;
    }

    public void createCreateUserInputPanel() {
        JPanel currentInputPanel = findPanelInLayout();
        if (currentInputPanel != null) {
            mainFrame.remove(currentInputPanel);
        }
        mainFrame.add(createUserPanel, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void createUploadTrackInputPanel() {
        JPanel currentInputPanel = findPanelInLayout();
        if (currentInputPanel != null) {
            mainFrame.remove(currentInputPanel);
        }
        mainFrame.add(uploadTrackPanel, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private JPanel findPanelInLayout() {
        Container contentPane = mainFrame.getContentPane();
        LayoutManager layoutManager = contentPane.getLayout();

        if (layoutManager instanceof BorderLayout borderLayout) {
            Component component = borderLayout.getLayoutComponent(contentPane, BorderLayout.CENTER);

            if (component instanceof JPanel) {
                return (JPanel) component;
            }
        }
        return null;
    }
}
