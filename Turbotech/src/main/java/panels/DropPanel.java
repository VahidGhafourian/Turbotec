package panels;

import res.MyColor;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.File;
import java.util.List;
import java.util.TooManyListenersException;

public class DropPanel extends JPanel {

    private DropTarget dropTarget;
    private DropTargetHandler dropTargetHandler;
    private Point dragPoint;

    private boolean dragOver = false;
    private File target;

    private JLabel message;

    public DropPanel() {
        target = new File("target.png");

        setLayout(new GridBagLayout());
        message = new JLabel("Drag your file here");
        message.setFont(message.getFont().deriveFont(Font.BOLD, 54));
        add(message);

        setBackground(MyColor.dropPanel);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    protected DropTarget getMyDropTarget() {
        if (dropTarget == null) {
            dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, null);
        }
        return dropTarget;
    }

    protected DropTargetHandler getDropTargetHandler() {
        if (dropTargetHandler == null) {
            dropTargetHandler = new DropTargetHandler();
        }
        return dropTargetHandler;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        try {
            getMyDropTarget().addDropTargetListener(getDropTargetHandler());
        } catch (TooManyListenersException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        getMyDropTarget().removeDropTargetListener(getDropTargetHandler());
    }

    protected void importFiles(final List files) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                message.setText("You dropped " + files.size() + " files");
                Receivepanel.setAddressTextFile(files.get(0).toString());
            }
        };
        SwingUtilities.invokeLater(run);
    }

    protected class DropTargetHandler implements DropTargetListener {

        protected void processDrag(DropTargetDragEvent dtde) {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else {
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            processDrag(dtde);
            SwingUtilities.invokeLater(new DragUpdate(true, dtde.getLocation()));
            repaint();
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            processDrag(dtde);
            SwingUtilities.invokeLater(new DragUpdate(true, dtde.getLocation()));
            repaint();
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
            SwingUtilities.invokeLater(new DragUpdate(false, null));
            repaint();
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {

            SwingUtilities.invokeLater(new DragUpdate(false, null));

            Transferable transferable = dtde.getTransferable();
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrop(dtde.getDropAction());
                try {

                    List transferData = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    if (transferData != null /*&& transferData.size() > 0*/) {
                        importFiles(transferData);
                        dtde.dropComplete(true);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                dtde.rejectDrop();
            }
        }
    }

    public class DragUpdate implements Runnable {

        private boolean dragOver;
        private Point dragPoint;

        public DragUpdate(boolean dragOver, Point dragPoint) {
            this.dragOver = dragOver;
            this.dragPoint = dragPoint;
        }

        @Override
        public void run() {
            DropPanel.this.dragOver = dragOver;
            DropPanel.this.dragPoint = dragPoint;
            DropPanel.this.repaint();
        }
    }

    @Override
    public void repaint() {
        super.repaint();

    }
}
