package SloppifyAdminApp.PanelLogic;

import SloppifyAdminApp.Drivers.UploadTrackDriver;

import javax.swing.*;

public class UploadTrackPanelLogic {

    private final UploadTrackDriver uploadTrackDriver;
    private String trackFilePath;
    private String coverArtPath;

    public UploadTrackPanelLogic(UploadTrackDriver uploadTrackDriver) {
        // Set logic Driver
        this.uploadTrackDriver = uploadTrackDriver;
    }

    public void selectTrackFile() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            trackFilePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    public void selectCoverArt() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            coverArtPath = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    public void uploadTrack(String title, String album, String artist, String day, String month, String year) {
        // Check if song exists
        if (uploadTrackDriver.findTrack(title, album, artist)) {
            JOptionPane.showMessageDialog(null, "Track already exists!");
            return;
        }

        // Send information to driver
        String releaseDate = day + "/" + month + "/" + year;
        uploadTrackDriver.uploadTrack(title, album, artist, releaseDate, trackFilePath, coverArtPath);

        // Show message of success
        JOptionPane.showMessageDialog(null, "Track uploaded successfully!");
    }

    public void refresh() {
        trackFilePath = null;
        coverArtPath = null;
    }
}
