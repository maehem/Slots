/*
 * PayLine.java
 *
 * Created on August 16, 2006, 6:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slots.logic;

import java.util.ArrayList;
import slots.gui.SymbolImageIcon;

/**
 *
 * @author Mark
 */
public class PayLine extends ArrayList {
    private int lineNumber = 0;
    
    /**
     * Creates a new instance of PayLine
     */
    public PayLine() {
    }
    
    int getPayout() {
        int payout = 0;
        int numMatches = getNumMatches();
        if ( numMatches > 0 ) {
            int [] payouts = ((SymbolImageIcon)get(0)).getPayout();
            return payouts[numMatches];
        } else {
            return 0;
        }
    }
    
    int getNumMatches( ) {
        int index = 0;
        for ( int i=1; i<this.size(); i++ ) {
            //System.out.println( "[0:" + ((SymbolImageIcon)get(i)).getName() + "][" + ((SymbolImageIcon)get(index)).getName() + "]" );
            if ( ((SymbolImageIcon)get(i)).getName() == ((SymbolImageIcon)get(index)).getName() ) {
                index++;
            } else {
                return index;
            }
        }
        return index;
    }
    
    public String toString() {
        String s = "<" + getLineNumber() + "> ";
        for ( int i=0; i<size(); i++ ) {
            s += "[" + ((SymbolImageIcon)get(i)).getName() + "]";
        }
        return s;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
