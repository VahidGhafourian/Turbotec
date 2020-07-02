package panels;

import res.MyColor;
import res.MySize;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;

public class SensorPanel extends JPanel {

    SensorTable table;
    JScrollPane scrollPane;

    Object[] currentRow =
            new Object[]{0,0,0,0,0,0,0,0};

    public SensorPanel() {
        setBackground(MyColor.panelBack);

        table = new SensorTable(new DefaultTableModel(new Object[]{"Sensor1", "Sensor2", "Sensor3", "Sensor4", "Sensor5", "Sensor6", "Sensor7", "Temp"} , 0)){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JComponent jc = (JComponent)super.prepareRenderer(renderer, row, column);
                jc.setBorder(BorderFactory.createLineBorder(MyColor.tableGridLine, 3));
                return jc;
            }
        };

        scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);

        setSize(MySize.sensorPanelSize);
        setMaximumSize(MySize.sensorPanelSize);

    }

    public void addData(Object[] newRow) {
        if (!Arrays.equals(newRow, currentRow)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.insertRow(0, newRow);
            currentRow = newRow;
        }
    }

}
