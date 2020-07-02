package panels;

import res.MyColor;
import res.MyFont;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class SensorTable extends JTable implements MouseMotionListener {
    int mouseHoverRow = -1;

    public SensorTable(TableModel dm) {
        super(dm, (TableColumnModel) null, (ListSelectionModel) null);
        addMouseMotionListener(this);

        JTableHeader tableHeader = getTableHeader();
        tableHeader.setBackground(MyColor.tableHeader);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(MyFont.sensorPanel);
        setTableHeader(tableHeader);
        setShowGrid(true);
        setGridColor(MyColor.tableGridLine);
        setBackground(MyColor.onlineSensorRow);
        setFont(MyFont.sensorPanel);
        setRowHeight(80);

    }

    //codes

    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
                                     int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        Color back = getSelectionBackground();
        int red = back.getRed();
        int green = back.getGreen();
        int blue = back.getBlue();
        if (rowIndex == mouseHoverRow) {
            c.setBackground(MyColor.tableFocus);
        } else {
            c.setBackground(getBackground());
        }
        return c;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    public void mouseMoved(MouseEvent e) {
        Point p = new Point(e.getX(), e.getY());
        mouseHoverRow = rowAtPoint(p);
        repaint();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


}
