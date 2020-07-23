package panels;

import BackWork.DataConfig;
import res.MyColor;
import res.MySize;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class OnlinePanel extends JPanel {

    private SensorPanel sensorPanel;
    private PanelOnline panelOnline;

    private static boolean isOpen = true;
    public OnlinePanel() {
        setBackground(MyColor.panelBack);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panelGif = new JPanel();
        BoxLayout layout = new BoxLayout(panelGif, BoxLayout.X_AXIS);
        panelGif.setLayout(layout);
        panelGif.add(new GifPanel());

        panelOnline = new PanelOnline();

        sensorPanel = new SensorPanel();
        sensorPanel.setMaximumSize(MySize.sensorPanelSize);

        JPanel monitorTitle = new JPanel();
        monitorTitle.setBackground(MyColor.panelBack);
        JLabel monitortitle = new JLabel("\n\n  Online Turbine situation: \n");
        monitortitle.setForeground(Color.WHITE);
        monitortitle.setFont( new Font(Font.SERIF, Font.BOLD, 20) );
        monitorTitle.setLayout(new BorderLayout());
        monitorTitle.add(monitortitle, BorderLayout.WEST);

        JPanel tabeleTitle = new JPanel();
        tabeleTitle.setBackground(MyColor.panelBack);
        JLabel title = new JLabel("\n\n  Online Sensor's values: \n");
        title.setForeground(Color.WHITE);
        title.setFont( new Font(Font.SERIF, Font.BOLD, 20) );
        tabeleTitle.setLayout(new BorderLayout());
        tabeleTitle.add(title, BorderLayout.WEST);

        add(panelGif);
        add(monitorTitle);
        add(panelOnline);
        add(gap(50, MyColor.panelBack));
        add(tabeleTitle);
        add(sensorPanel);
//        add(gap(50, MyColor.panelBack));


    }

    private JPanel gap(int size, Color color) {
        JPanel p = new JPanel();
        p.setBackground(color);
        p.setMaximumSize(new Dimension(size, size));
        p.setSize(new Dimension(size, size));
        return p;
    }


    public static void setOpen(boolean b) {
        isOpen = b;
    }
    public static boolean isOpen() {
        return isOpen;
    }

    public  void setData(DataConfig d) {
        panelOnline.data = d;
        panelOnline.update(sensorPanel);
    }
}
