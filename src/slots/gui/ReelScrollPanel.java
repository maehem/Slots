/*
 * ReelScrollPanel.java
 *
 * Created on July 30, 2006, 12:28 PM
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package slots.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import slots.logic.Reel;
import slots.logic.ReelEvent;
import slots.logic.ReelEventListener;

/**
 *
 * @author Mark
 */
public class ReelScrollPanel extends JScrollPane implements ReelEvent, ReelEventListener {
    private SymbolPanel symbolPanel;
    private ArrayList reelListeners = new ArrayList();
    private int numLines;
    
    /** Creates a new instance of ReelScrollPanel */
    public ReelScrollPanel( SymbolPanel symbolPanel, int numLines ) {
        super( symbolPanel );
        this.symbolPanel = symbolPanel;
        this.numLines = numLines;
        
        setPreferredSize(new Dimension(100, numLines*100 ));
        
        this.setVerticalScrollBarPolicy( VERTICAL_SCROLLBAR_NEVER );
        //this.setVerticalScrollBarPolicy( VERTICAL_SCROLLBAR_ALWAYS );
        this.setHorizontalScrollBarPolicy( HORIZONTAL_SCROLLBAR_NEVER );
        getReel().setMaxValue(symbolPanel.getHeight());
    }
    
    public boolean isSpinning() {
        return getReel().isSpinning();
    }
    
    public boolean hit() {
        return getReel().hit();
    }
    
    public SymbolImageIcon getSymbol(int lineNum) {
        int value = getVerticalScrollBar().getValue();
        return symbolPanel.getSlotSymbolAt(lineNum, value);
    }
    
    public void setSpinning(boolean spin) {
        getReel().setSpinning(spin);
    }
    
    public void bumpRevCount() {
        getReel().bumpRevCount();
    }
    
    public int getRevCount() {
        return getReel().getRevCount();
    }
    
    public void observedReelStopped() {
        getReel().observedReelStopped();
    }
    
    public void addReelEventListener(Object o) {
        reelListeners.add(o);
    }
    
    public void notifyReelEventListeners() {
        for( int i=0; i<reelListeners.size(); i++ ) {
            ReelEventListener listener = (ReelEventListener)reelListeners.get(i);
            listener.observedReelStopped();
        }
    }
    
    public void removeReelEventListener(Object o) {
        int i = reelListeners.indexOf(o);
        if ( i >= 0 ) {
            reelListeners.remove(i);
        }
    }
    
    public boolean okToStop() {
        return getReel().okToStop();
    }
    
    public boolean isFirstReel() {
        return symbolPanel.getReel().isFirstReel();
    }
    
    public void setFirstReel(boolean firstReel) {
        getReel().setFirstReel(firstReel);
    }
    
    public SymbolImageIcon[] getVisibleSymbols() {
        SymbolImageIcon[] symbols = new SymbolImageIcon[numLines];
        for( int i=0; i < numLines; i++ ) {
            symbols[i] = getSymbol(i);
        }
        
        return symbols;
    }
    
    public int getValue() {
        return getReel().getValue();
    }
    
    public void setValue(int value) {
        getReel().setValue(value);
        getVerticalScrollBar().setValue(getValue());
        System.out.println("Set Scrollbar Value: " + getVerticalScrollBar().getValue());
    }
    
    public void changeValue( int amount ) {
        setValue(getValue() + amount);
    }
    
    public void setMaximum() {
        //System.out.println("Maximum Value: " + getVerticalScrollBar().getMaximum());
        getReel().setMaxValue(getVerticalScrollBar().getMaximum());
    }
    
    public Reel getReel() {
        return symbolPanel.getReel();
    }
    
    public void notifyReelStoppedListeners() {
    }
    
    public void notifyReelValueChangedListeners() {
    }
    
    public void reelValueChanged(int value) {
        getVerticalScrollBar().setValue(value);
    }
}
