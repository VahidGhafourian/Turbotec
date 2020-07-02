package panels;

import BackWork.DataConfig;
import res.MyColor;
import res.MySize;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class OnlinePanel extends JPanel {

    ColumnsPanel[] unsafes ;
    ColumnsPanel safe;
    DataConfig data;
    SensorPanel sensorPanel;

    private static boolean isOpen = true;
    public OnlinePanel() {
        setBackground(MyColor.panelBack);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panelGif = new JPanel();
        BoxLayout layout = new BoxLayout(panelGif, BoxLayout.X_AXIS);
        panelGif.setLayout(layout);
        panelGif.add(new GifPanel());

        JPanel panelOnline = new JPanel();
        BoxLayout resultLayout = new BoxLayout(panelOnline, BoxLayout.X_AXIS);
        panelOnline.setLayout(resultLayout);
        panelOnline.setSize(MySize.ColumnPanelSize);
        safe = new ColumnsPanel(Color.GREEN, "H ");
//        safe.setSize(new Dimension(500, 1000));
        safe.setMinimumSize(new Dimension(800, 1000));
        unsafes = new ColumnsPanel[14];
        makeUnsafes(unsafes);
        panelOnline.add(gap(40, MyColor.onlineColumnBack));
        panelOnline.add(safe);
        addUnsafes(panelOnline, unsafes);
        panelOnline.add(gap(30, MyColor.onlineColumnBack));
        panelOnline.setMaximumSize(MySize.ColumnPanelSize);
        panelOnline.setBackground(MyColor.onlineColumnBack);
        panelOnline.setBorder(new LineBorder(MyColor.onlineColumnBorder, 18, false));

        sensorPanel = new SensorPanel();

        add(panelGif);
        add(panelOnline);
        add(gap(300, MyColor.panelBack));
        add(sensorPanel);
        add(gap(300, MyColor.panelBack));


    }

    private JPanel gap(int size, Color color) {
        JPanel p = new JPanel();
        p.setBackground(color);
        p.setMaximumSize(new Dimension(size, size));
        p.setSize(new Dimension(size, size));
        return p;
    }
    private void makeUnsafes (ColumnsPanel[] panels) {
        for (int i = 0; i < panels.length; i++) {
            String title ;
            if (i<9)
                title = "P" + (i+1) + " ";
            else title = "p" + (i+1);
            panels[i] = new ColumnsPanel(MyColor.onlineColumn, title);
            panels[i].setMaximumSize(new Dimension(26000, 1000));
        }

    }
    private void addUnsafes(JPanel main , ColumnsPanel[] panels) {
        for (int i = 0; i < panels.length; i++) {
            main.add(gap(30,MyColor.onlineColumnBack));
            main.add(panels[i]);
        }
    }

    public void update() {
        if (data!=null) {
            safe.setR(data.getCol(0));
            for (int i = 0; i < unsafes.length; i++) {
                unsafes[i].setR(data.getCol(i + 1));
                unsafes[i].start();
            }
            safe.start();

            // getting ready data to add to table
            Object[] newRow = new Object[8];
            for (int i=0; i<8; i++){
                newRow[i] = data.getSensor(i+1);
            }
            sensorPanel.addData(newRow);
        }
    }
    public static void setOpen(boolean b) {
        isOpen = b;
    }
    public static boolean isOpen() {
        return isOpen;
    }

    public  void setData(DataConfig d) {
        data = d;
        update();
    }
}
