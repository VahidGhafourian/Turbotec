package panels;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import res.MyFont;
import res.MySize;

public class Mbutton extends JButton {

    public Mbutton(String lable, final int number){
        super("  " + lable + "  ");

        setMaximumSize(MySize.btnSize);
        setMinimumSize(MySize.btnSize);
        setFont(MyFont.btnFont);

        try {
            UIManager.setLookAndFeel(new AluminiumLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (number == 1) {
                    OnlinePanel.setOpen(true);
                    Receivepanel.setOpen(false);

                } else if (number == 2){
                    OnlinePanel.setOpen(false);
                    Receivepanel.setOpen(true);
                }

                MainPanel.cardLayout.first(MainPanel.cardPanel);
                for (int i=1; i<number; i++)
                    MainPanel.cardLayout.next(MainPanel.cardPanel);


            }
        });

    }
}
