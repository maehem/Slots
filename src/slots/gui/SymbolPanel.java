/*
 * SymbolPanel.java
 *
 * Created on July 30, 2006, 12:48 PM
 *
 * Copyright 2008 Maehem Media, Inc.
 * Use is subject to license terms.
 */

package slots.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import slots.logic.Reel;

/**
 *
 * @author Mark
 */
public class SymbolPanel extends JPanel {
    private Reel reel;
    
     /** Creates a new instance of SymbolPanel */
    public SymbolPanel() {
        this.setReel(new Reel(3));
    
        // TODO:  Create a random ordered list of indexes
        //      - Load the symbols.
        //      - Scramble the list.
        //      - Build the final list.
        // with each symbol on twice.
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));        

        for( int i=0; i<getReel().size(); i++ ) {
            SymbolImageIcon symbolImage =
                    new SymbolImageIcon( getReel().get(i) );
            add(new JLabel((SymbolImageIcon)symbolImage));
        }
        for( int i=0; i<3; i++ ) {
            SymbolImageIcon symbolImage =
                    new SymbolImageIcon( getReel().get(i) );
            add(new JLabel((SymbolImageIcon)symbolImage));
        }
    }
        
    public SymbolImageIcon getSlotSymbolAt( int lineNum, int sbValue ) {
        return (SymbolImageIcon)((JLabel)getComponent( (int)(sbValue/100) + lineNum )).getIcon()   ;
    }

    public Reel getReel() {
        return reel;
    }

    public void setReel(Reel reel) {
        this.reel = reel;
    }
}
