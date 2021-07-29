/*
 * SlotLogic.java
 *
 * Created on August 14, 2006, 9:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slots.logic;

import java.util.ArrayList;
import slots.gui.ReelPanel;
import slots.gui.SlotFrame;
import slots.gui.SymbolImageIcon;

/**
 *
 * @author Mark
 */
public class SlotLogic implements ReelEventListener {
    SlotFrame frame;
    int[][] payLines;
    int[][] payLines3x5 = {
        { 2,2,2,2,2 },  // 1
        { 3,3,3,3,3 },  // 2
        { 1,1,1,1,1 },  // 3
        { 1,2,3,2,1 },  // 4
        { 3,2,1,2,3 },  // 5
        { 2,1,1,1,2 },  // 6
        { 2,3,3,3,2 },  // 7
        { 1,1,2,3,3 },  // 8
        { 3,3,2,1,1 },  // 9
        { 2,1,2,1,2 },  // 10
        { 2,3,2,3,2 },  // 11
        { 1,2,2,2,3 },  // 12
        { 3,2,2,2,1 },  // 13
        { 2,2,1,2,3 },  // 14
        { 2,2,3,2,1 },  // 15
        { 3,2,3,2,3 },  // 16
        { 3,2,3,2,3 },  // 17
        { 1,1,3,1,1 },  // 18
        { 3,3,1,3,3 },  // 19
        { 2,1,3,1,2 },  // 20
        { 2,3,1,3,2 },  // 21
        { 1,3,1,3,1 },  // 22
        { 3,1,3,1,3 },  // 23
        { 1,3,3,3,1 },  // 24
        { 3,1,1,1,3 }   // 25
    };  
    
    /** Creates a new instance of SlotLogic */
    public SlotLogic( SlotFrame frame ) {
        this.frame = frame;
        payLines = payLines3x5;
        ReelPanel reelPanel = frame.getReelPanel();
        reelPanel.getLastReel().addReelEventListener(this);
    }
    
    public void printMatrix() {
        SymbolImageIcon[][] matrix = getMatrix();
        for ( int i=0; i< matrix[0].length; i++ ) {
            for( int j=0; j<matrix.length; j++ ){
                System.out.print( "[" + matrix[j][i].getName() + "]" );
            }
            System.out.println();
        }
    }
    
    public void observedReelStopped() {
        printWinnings();
    }
    
    private void printWinnings() {
        int payout = 0;
        PayLine[] p = getWinningPayLines();
        for ( int i=0; i<p.length; i++) {
            System.out.println( p[i].toString() + " Line Pays: " + p[i].getPayout() );
            payout += p[i].getPayout();
        }
        System.out.println("Game Pays: " + payout );
    }
    
    public SymbolImageIcon[][] getMatrix() {
        return frame.getReelPanel().getVisibleSymbols();
    }
    
    public PayLine[] getPayLines() {
        PayLine[] p = new PayLine[payLines.length];
        SymbolImageIcon[][] matrix = getMatrix();
        for( int i=0; i<payLines.length; i++) {
            p[i] = new PayLine();
            p[i].setLineNumber(i+1);
            for( int j=0; j<matrix.length; j++) {
                p[i].add(matrix[j][payLines[i][j]-1]);
            }
        }
        
        return p;
    }
    
    public PayLine[] getWinningPayLines() {
        PayLine[] pl = getPayLines();
        PayLine[] w = new PayLine[pl.length];
        
        int j=0;
        for ( int i=0; i<pl.length; i++) {
            if ( pl[i].getPayout() > 0 ) {
                w[j] =  pl[i];
                j++;
            }
        }
        pl = new PayLine[j];
        for( int k=0; k<j; k++ ) {
            pl[k] = w[k];
        }
        return pl;
    }

    public void reelValueChanged(int value) {
    }
}
