package SloppifyAdminApp.Panels;

import SloppifyAdminApp.PanelLogic.UploadTrackPanelLogic;

import javax.swing.*;
import java.awt.*;

public class UploadTrackPanel extends JPanel{

    private final UploadTrackPanelLogic uploadTrackPanelLogic;

    public UploadTrackPanel(UploadTrackPanelLogic uploadTrackPanelLogic) {
        // Set panel logic
        this.uploadTrackPanelLogic = uploadTrackPanelLogic;

        // Config panel
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 30);

        // Create labels
        JLabel titleLabel = new JLabel("Title: ");
        JLabel albumLabel = new JLabel("Album: ");
        JLabel artistLabel = new JLabel("Artist: ");
        JLabel releaseDateLabel = new JLabel("Release Date: ");
        JLabel trackFileLabel = new JLabel("Track File: ");
        JLabel coverArtLabel = new JLabel("Cover Art: ");

        // Config labels
        titleLabel.setForeground(Color.WHITE);
        albumLabel.setForeground(Color.WHITE);
        artistLabel.setForeground(Color.WHITE);
        releaseDateLabel.setForeground(Color.WHITE);
        trackFileLabel.setForeground(Color.WHITE);
        coverArtLabel.setForeground(Color.WHITE);

        // Add labels
        gbc.gridx = 0; gbc.weightx = 0.01;

        gbc.gridy = 0;
        this.add(titleLabel, gbc);

        gbc.gridy = 1;
        this.add(albumLabel, gbc);

        gbc.gridy = 2;
        this.add(artistLabel, gbc);

        gbc.gridy = 3;
        this.add(releaseDateLabel, gbc);

        gbc.gridy = 4;
        this.add(trackFileLabel, gbc);

        gbc.gridy = 5;
        this.add(coverArtLabel, gbc);

        // Create text fields
        JTextField titleTextField = new JTextField();
        JTextField albumTextField = new JTextField();
        JTextField artistTextField = new JTextField();
        JTextField dayTextField = new JTextField();
        JTextField monthTextField = new JTextField();
        JTextField yearTextField = new JTextField();

        // Config text fields
        titleTextField.setForeground(Color.WHITE);
        titleTextField.setBackground(Color.DARK_GRAY);
        albumTextField.setForeground(Color.WHITE);
        albumTextField.setBackground(Color.DARK_GRAY);
        artistTextField.setForeground(Color.WHITE);
        artistTextField.setBackground(Color.DARK_GRAY);
        dayTextField.setForeground(Color.WHITE);
        dayTextField.setBackground(Color.DARK_GRAY);
        dayTextField.setText("DD");
        monthTextField.setForeground(Color.WHITE);
        monthTextField.setBackground(Color.DARK_GRAY);
        monthTextField.setText("MM");
        yearTextField.setForeground(Color.WHITE);
        yearTextField.setBackground(Color.DARK_GRAY);
        yearTextField.setText("YYYY");

        // Add text fields
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 1;

        gbc.gridy = 0;
        this.add(titleTextField, gbc);

        gbc.gridy = 1;
        this.add(albumTextField, gbc);

        gbc.gridy = 2;
        this.add(artistTextField, gbc);

        gbc.gridy = 3; gbc.gridwidth = 1;

        gbc.gridx = 1;
        this.add(dayTextField, gbc);

        gbc.gridx = 2;
        this.add(monthTextField, gbc);

        gbc.gridx = 3;
        this.add(yearTextField, gbc);

        // Create buttons
        JButton trackFileButton = new JButton("Select Track File");
        JButton coverArtButton = new JButton("Select Cover Art");
        JButton uploadButton = new JButton("Upload");

        // Add logic to buttons
        trackFileButton.addActionListener(e -> uploadTrackPanelLogic.selectTrackFile());
        coverArtButton.addActionListener(e -> uploadTrackPanelLogic.selectCoverArt());
        uploadButton.addActionListener(e -> {
            // Upload track
            uploadTrackPanelLogic.uploadTrack(
                    titleTextField.getText(),
                    albumTextField.getText(),
                    artistTextField.getText(),
                    dayTextField.getText(),
                    monthTextField.getText(),
                    yearTextField.getText()
            );

            // Clean text fields and file paths
            titleTextField.setText("");
            albumTextField.setText("");
            artistTextField.setText("");
            dayTextField.setText("DD");
            monthTextField.setText("MM");
            yearTextField.setText("YYYY");
            uploadTrackPanelLogic.refresh();
                }
        );

        // Config buttons
        trackFileButton.setBackground(new Color(31, 214, 98));
        trackFileButton.setForeground(Color.WHITE);
        trackFileButton.setFocusable(false);
        coverArtButton.setBackground(new Color(31, 214, 98));
        coverArtButton.setForeground(Color.WHITE);
        coverArtButton.setFocusable(false);
        uploadButton.setBackground(new Color(31, 214, 98));
        uploadButton.setForeground(Color.WHITE);
        uploadButton.setFocusable(false);

        // Add buttons
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.weightx = 1;

        gbc.gridy = 4;
        this.add(trackFileButton, gbc);

        gbc.gridy = 5;
        this.add(coverArtButton, gbc);

        gbc.gridx = 3; gbc.gridy = 6; gbc.weighty = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        this.add(uploadButton, gbc);
    }
}
