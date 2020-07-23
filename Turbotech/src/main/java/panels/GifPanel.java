package panels;

import javax.swing.*;
import java.awt.*;

public class GifPanel extends JPanel {
    ImageIcon image;
    JLabel imageLabel = new JLabel();
    public GifPanel() {
        setBackground(new Color(6, 6, 40));
        image = new ImageIcon("Turbine2.gif");

        imageLabel.setIcon(image);
        add(imageLabel);
        imageLabel.setVisible(true);
    }
    @Override
    public void paintComponent(Graphics g) {
        imageLabel.setVisible(OnlinePanel.isOpen());
        super.paintComponent(g);
    }

}
