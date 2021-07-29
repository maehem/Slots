/*
 * ReelPanel.java
 *
 * Created on July 30, 2006, 12:15 PM
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package slots.gui;

import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Mark
 */
public class ReelPanel extends JPanel {
    int numReels = 5;
    int numLines = 3;
    ReelScrollPanel[] reelScrollPanel = new ReelScrollPanel[numReels];

    /** Creates a new instance of ReelPanel */
    public ReelPanel(int numLines, int numReels) {
        this.numLines = numLines;
        this.numReels = numReels;
        setLayout( new FlowLayout() );
        for( int i=0; i<numReels; i++ ) {
            reelScrollPanel[i] = new ReelScrollPanel( new SymbolPanel(), numLines );
            add( reelScrollPanel[i] );
            if ( i > 0 ) {
                reelScrollPanel[i-1].addReelEventListener(reelScrollPanel[i]);
            } else {
                reelScrollPanel[i].setFirstReel(true);
            }
        }
        validate();
    }
    
    public void setSpin( boolean spinning ) {
        for( int i=0; i<numReels; i++ ) {
            reelScrollPanel[i].setSpinning(spinning);
        }
    }

    public SymbolImageIcon[][] getVisibleSymbols() {
        SymbolImageIcon[][] matrix = new SymbolImageIcon[numReels][numLines];
        for ( int i=0; i< numReels; i++ ) {
            matrix[i] = reelScrollPanel[i].getVisibleSymbols();
        }
        return matrix;
    }
    
    public ReelScrollPanel getLastReel() {
        return reelScrollPanel[reelScrollPanel.length-1];
    }
    
    public ReelScrollPanel getReel( int i ) {
        if ( i >= reelScrollPanel.length ) return null;
        return reelScrollPanel[i];
    }
    
    public int getReelCount() {
        return numReels;
    }
}
