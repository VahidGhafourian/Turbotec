package panels;

import res.MyColor;
import res.MyFont;
import res.MySize;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Receivepanel extends JPanel {
    private static boolean isOpen = false;

    private final String BTN_DIR = "Dir";
    private final String BTN_FILE = "File";

    private static JLabel addressTextFile;
    private static JTextField txtFile;
    private static JTextField txtDir;

    private File selectedFile;
    public Receivepanel() {
        setBackground(MyColor.panelBack);

        JPanel ptitle = new JPanel();
        ptitle.setBackground(MyColor.panelBack);
        JLabel title = new JLabel("Upload Files: ");
        title.setForeground(Color.WHITE);
        title.setFont( new Font(Font.SERIF, Font.BOLD, 80) );
        ptitle.setLayout(new BorderLayout());
        ptitle.add(title, BorderLayout.WEST);
        ptitle.setMaximumSize(MySize.receivePanelTitleSize);

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        DropPanel dropPane = new DropPanel();

        JLabel addressTextDir = new JLabel("Directory Address: ");
        addressTextDir.setFont( MyFont.receivePanelTextLabelFont );
        JButton btnDir = new JButton("Select");
        txtDir = new JTextField();
        txtDir.setFont(MyFont.receivePanelTextFieldFont);

        addressTextFile = new JLabel("File Address: ");
        addressTextFile.setFont( MyFont.receivePanelTextLabelFont );
        JButton btnFile = new JButton("Select");
        txtFile = new JTextField();
        txtFile.setFont(MyFont.receivePanelTextFieldFont);


        btnDir.setMaximumSize(MySize.receivePanelBtnSize);
        btnDir.setMinimumSize(MySize.receivePanelBtnSize);
        btnDir.setFont( MyFont.receivePanelBtnFont );
        btnFile.setMaximumSize(MySize.receivePanelBtnSize);
        btnFile.setMinimumSize(MySize.receivePanelBtnSize);
        btnFile.setFont( MyFont.receivePanelBtnFont );
        btnActionListener(btnFile, this.BTN_FILE);

        p1.add(addressTextDir);
        p1.add(addressTextFile);
        p2.add(txtDir);
        p2.add(txtFile);
        p3.add(btnDir);
        p3.add(btnFile);

        BoxLayout p1Layout = new BoxLayout(p1, BoxLayout.Y_AXIS);
        BoxLayout p2Layout = new BoxLayout(p2, BoxLayout.Y_AXIS);
        BoxLayout p3Layout = new BoxLayout(p3, BoxLayout.Y_AXIS);
        p1.setLayout(p1Layout);
        p2.setLayout(p2Layout);
        p3.setLayout(p3Layout);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setMaximumSize(new Dimension(1500,80));
        p.setMinimumSize(new Dimension(1000,80));
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

        JButton btnStart = new JButton("Start");
        btnStart.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        btnStart.setMaximumSize(MySize.receivePanelBtnSize);

        JPanel footer = new JPanel();
        footer.setBackground(MyColor.panelBack);
        footer.setLayout(new BorderLayout());
        footer.add(btnStart, BorderLayout.CENTER);
        footer.add(Box.createRigidArea(MySize.receivePanelBtnSize), BorderLayout.SOUTH);
        footer.add(Box.createRigidArea(MySize.receivePanelBtnSize), BorderLayout.NORTH);
        footer.setMinimumSize(MySize.receivePanelBtnSize);
        footer.setMaximumSize(MySize.receivePanelBtnSize);

        add(ptitle);
        add(p);
        add(dropPane);
        add(footer);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

    }

    private void btnActionListener (JButton btn,String type){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Choose a directory to save your file: ");
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = jfc.showSaveDialog(null);
            }
        });
    }
    public static void setAddressTextFile (String str){
        txtFile.setText(str);
    }

    @Override
    public void repaint() {
        super.repaint();
        setVisible(isOpen());
    }

    public static void setOpen(boolean open) {
        isOpen = open;
    }

    public static boolean isOpen() {
        return isOpen;
    }
}
