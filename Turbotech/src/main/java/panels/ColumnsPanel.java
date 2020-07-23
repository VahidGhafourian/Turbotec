package panels;

import res.MyColor;

import javax.swing.*;
import java.awt.*;

public class ColumnsPanel extends JPanel {
    ColumnR resultPanel;
    JPanel txtPanel;

    private Dimension size = new Dimension(10,10);
    public ColumnsPanel( Color color, String title) {
        setBackground(MyColor.onlineColumnBack);
        resultPanel = new ColumnR(color);
        txtPanel = new JPanel();
        JLabel label = new JLabel(title);
        txtPanel.add(label);
        txtPanel.setSize(size);
        txtPanel.setBackground(MyColor.onlineColumnBack);
        label.setFont(new Font(Font.SERIF, Font.ITALIC, 13));
        label.setBackground(Color.RED);
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        resultPanel.setPreferredSize(new Dimension(30,150));
        add(resultPanel);
        add(txtPanel);



    }

    public void setRects(double rects) {
        resultPanel.setRects(rects);
    }

    public void setR(double r) {
        resultPanel.setR(r);
    }

    public void start () {
        resultPanel.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private JPanel gap(int size, Color color) {
        JPanel p = new JPanel();
        p.setBackground(color);
        p.setMaximumSize(new Dimension(size, size));
        return p;
    }
}
