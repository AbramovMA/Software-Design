package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoverEffect extends MouseAdapter{
    //Main lol;
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        //lol.currSeq.colourfulSequence(lol.updateSequence, lol.matrix.get_selected_value(e), lol.sequenceFlow);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }
}
