package panels;

import res.MyColor;
import res.MyFont;

import javax.swing.*;
import java.awt.*;

public class ErrorDraw extends JPanel {
    private final String dangerMessage = "Danger!!! Warning!!!";
    private final String safeMessage = "Every thing are OK.";
    private String messege;
    private boolean status = true ;
    Graphics g;
    protected void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);
        if (status){
            g.setColor(MyColor.safeMessage);
            messege = safeMessage;
        }
        else {
            g.setColor(MyColor.onlineColumn);
            messege = dangerMessage;
        }
        g.setFont(MyFont.message);
        g.fillOval(230,15,50,50);
        g.fillOval(10,15,50,50);
        g.drawString(messege, 65, 45);
    }

    public Dimension getPreferredSize() {
        return new Dimension(30, 30); // appropriate constants
    }
    public void setStatus (boolean s){
        this.status = s;
        System.out.println(status);
        repaint();
    }
}
