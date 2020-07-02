package panels;

import res.MyColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ColumnR extends JPanel {
    private double rects ;
    private double height = 3;
    private int weight = 70;
    private int X = 10;
    private double Y = 400;
    private double r = 10;
    private int gap = 10;
    private Color fillColor ;
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

    public ColumnR(Color color) {
        setBackground(MyColor.onlineColumnBack);
        fillColor = color;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(fillColor);
        Y = (100 - rects)*4;
        height = (430 - Y);
        Shape shape = new RoundRectangle2D.Double(X, Y, weight, height, 25, 25);
        g2.draw(shape);
        g2.fill(shape);
        g.setColor(MyColor.onlineColumnNum);
        g.setFont(font);
        if (Double.compare(100.0, rects)==0)
            g.drawString(" "+(int)rects+"%" , X-4, (int) (Y+(height/2) + 9));
        else g.drawString(" "+rects+"%" , X-5, (int) (Y+(height/2) + 9));
    }

    public void setRects(double rects) {
        this.rects = rects;
    }

    public void start () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (r>rects)
                        for (double i=rects; i<=r; i++) {
                            Thread.sleep(20);
                            setRects(i);
                            if (OnlinePanel.isOpen())
                                repaint();
                        }
                    else
                        for (double j=rects; j>=r; j--){
                            Thread.sleep(20);
                            setRects(j);
                            if (OnlinePanel.isOpen())
                                repaint();
                        }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    public void setR(double r) {
        this.r = r;
    }


}
