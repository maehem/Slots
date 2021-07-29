/*
 * ReelSpinTask.java
 *
 * Created on July 30, 2006, 2:18 PM
 *
 * Copyright 2007 Maehem Media, Inc. All rights reserved.
 * Use is subject to license terms.
 */

package slots.logic;

import java.util.TimerTask;
import slots.gui.ReelScrollPanel;

/**
 *
 * @author Mark
 */
public class ReelSpinTask extends TimerTask {
    Reel reel;
    static int rate = -15;
    
    
    /** Creates a new instance of ReelSpinTask */
    public ReelSpinTask( Reel reel ) {
        this.reel = reel;
    }
    
    public void run() {
        if( reel.isSpinning() ) {
            reel.changeValue(rate);

            if ( reel.isFirstReel() && reel.getRevCount() >= 1 ) {
                reel.observedReelStopped();
            }
            
            //System.out.println( "Value: " + reel.value );
            //System.out.println( "Rate: " + rate );
            if ( reel.okToStop() && reel.hit() ) {
                reel.setSpinning(false);
                // reel.alignValue() 
                reel.setValue(((int)((reel.getValue()+66)/100) * 100));

                //System.out.println( "Symbol Name: " + reel.getSymbol(2).getName() );
                reel.notifyReelStoppedListeners();
                //System.out.println( "Final Value: " + reel.value );
            }
            //reel.repaint();
        }
        
    }
    
}
