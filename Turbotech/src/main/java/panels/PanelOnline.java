package panels;

import BackWork.DataConfig;
import res.MyColor;
import res.MySize;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelOnline extends JPanel {
    public DataConfig data;
    private ColumnsPanel[] unsafes ;
    private ColumnsPanel safe;
    public PanelOnline() {
        BoxLayout resultLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(resultLayout);
        safe = new ColumnsPanel(Color.GREEN, "H");
        unsafes = new ColumnsPanel[14];
        makeUnsafes(unsafes);
        add(safe);
        addUnsafes(this, unsafes);
        setPreferredSize(MySize.ColumnPanelSize);
        setBackground(MyColor.onlineColumnBack);
        setBorder(new LineBorder(MyColor.onlineColumnBorder, 18, false));

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
                title = "P" + (i+1);
            else title = "P" + (i+1);
            panels[i] = new ColumnsPanel(MyColor.onlineColumn, title);
        }

    }
    private void addUnsafes(JPanel main , ColumnsPanel[] panels) {
        for (int i = 0; i < panels.length; i++) {
            main.add(panels[i]);
        }
    }

    public void update(SensorPanel sensorPanel) {
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
}
