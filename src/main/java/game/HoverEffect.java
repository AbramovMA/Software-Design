package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoverEffect extends MouseAdapter{
    Main lol;
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if(lol.iSeq>0){
            lol.currSeq.colourfulSequence(lol.updateSequence, lol.matrix.get_highlighted_value(e), lol.sequenceFlow);
        }
        else{
            lol.currSeq.colourfulSequence(lol.ourPuzzle.pickedSequence, lol.matrix.get_highlighted_value(e), lol.sequenceFlow);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(lol.iSeq>0){
            lol.currSeq.uncolourfulSequence(lol.updateSequence, lol.matrix.get_highlighted_value(e), lol.sequenceFlow);
        }
        else{
            lol.currSeq.uncolourfulSequence(lol.ourPuzzle.pickedSequence, lol.matrix.get_highlighted_value(e), lol.sequenceFlow);
        }
        super.mouseExited(e);
    }
}
/*
matrix.button_grid[3][3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(iSeq>0){
                    currSeq.uncolourfulSequence(updateSequence, matrix.get_highlighted_value(evt), sequenceFlow);
                }
                else{
                    currSeq.colourfulSequence(ourPuzzle.pickedSequence, matrix.get_highlighted_value(evt), sequenceFlow);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(iSeq>0){
                    currSeq.uncolourfulSequence(updateSequence, matrix.get_highlighted_value(evt), sequenceFlow);
                }
                else{
                    currSeq.uncolourfulSequence(ourPuzzle.pickedSequence, matrix.get_highlighted_value(evt), sequenceFlow);

                }

            }
        });
 */