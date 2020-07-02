package panels;

import javax.swing.*;
import java.awt.*;

public class GifPanel extends JPanel {
    ImageIcon image;
    JLabel imageLabel = new JLabel();
    Dimension size = new Dimension(1500,400);
    public GifPanel() {
        setBackground(new Color(6, 6, 40));
        image = new ImageIcon("turbinDark.gif");

        imageLabel.setIcon(image);
        add(imageLabel);
        imageLabel.setVisible(true);
        setMaximumSize(size);
        setMinimumSize(size);
    }
    @Override
    public void paintComponent(Graphics g) {
        imageLabel.setVisible(OnlinePanel.isOpen());
        super.paintComponent(g);
    }

}
