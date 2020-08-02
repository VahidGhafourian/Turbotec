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
    private ErrorDraw alarmBox;
    public PanelOnline(ErrorDraw alarmBox) {
        this.alarmBox = alarmBox;
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

    /**
     * Use 'new' to make unsafe objects
     * @param panels
     */
    private void makeUnsafes (ColumnsPanel[] panels) {
        for (int i = 0; i < panels.length; i++) {
            String title ;
            if (i<9)
                title = "P" + (i+1);
            else title = "P" + (i+1);
            panels[i] = new ColumnsPanel(MyColor.onlineColumn, title);
        }

    }

    /**
     * Adding unsafe columns to panel
     * @param main
     * @param panels
     */
    private void addUnsafes(JPanel main , ColumnsPanel[] panels) {
        for (int i = 0; i < panels.length; i++) {
            main.add(panels[i]);
        }
    }

    /**
     * Updating all things on online panel. Like sensor's value table and result columns.
     * @param sensorPanel
     */
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
        sendStatus();
    }
    private  void sendStatus(){
        if((int) data.getCol(0)>=70)
            alarmBox.setStatus(true);
        else alarmBox.setStatus(false);

    }
}
