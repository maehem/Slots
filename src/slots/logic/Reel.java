/*
 * Reel.java
 *
 * Created on July 30, 2006, 12:28 PM
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package slots.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.Vector;
import javax.swing.JLabel;
import slots.gui.SymbolImageIcon;

/**
 *
 * @author Mark
 */
public class Reel extends Vector<Symbol> implements ReelEvent, ReelEventListener {
    private boolean spinning = false;
    private ArrayList reelListeners = new ArrayList();
    Timer timer;
    private int value = 0;
    private int maxValue = 0;
    private int revCount = 0;
    private boolean isStopOk = true;
    private boolean firstReel = false;
    private int numLines;
    
    /**
     * Creates a new instance of Reel
     */
    public Reel( int numLines ) {
        this.numLines = numLines;
        initSymbols();
        timer = new Timer();
        timer.schedule(new ReelSpinTask( this ),
                0,        //initial delay
                30);  //subsequent rate
        
    }
    
    public boolean isSpinning() {
        return spinning;
    }
    
    public boolean hit() {
        Symbol s = getSymbol(numLines/2);
        if ( Math.random() < ( 1.0/s.getOdds() ) ) {
            return true;
        } else {
            return false;
        }
    }
    
    public Symbol getSymbol(int lineNum) {
        return this.get(lineNum);
    }
    
    public void setSpinning(boolean spin) {
        setRevCount(0);
        isStopOk = !spin;
        spinning = spin;
    }
    
    public void bumpRevCount() {
        setRevCount(getRevCount() + 1);
    }
    
    public int getRevCount() {
        return revCount;
    }
    
    public void observedReelStopped() {
        isStopOk = true;
    }
    
    public void addReelEventListener(Object o) {
        reelListeners.add(o);
    }
    
    public void notifyReelStoppedListeners() {
        for( int i=0; i<reelListeners.size(); i++ ) {
            ReelEventListener listener = (ReelEventListener)reelListeners.get(i);
            listener.observedReelStopped();
        }
    }
    
    public void notifyReelValueChangedListeners() {
        for( int i=0; i<reelListeners.size(); i++ ) {
            ReelEventListener listener = (ReelEventListener)reelListeners.get(i);
            listener.reelValueChanged(getValue());
        }
    }
    
    public void removeReelEventListener(Object o) {
        int i = reelListeners.indexOf(o);
        if ( i >= 0 ) {
            reelListeners.remove(i);
        }
    }
    
    public boolean okToStop() {
        return isStopOk;
    }
    
    public boolean isFirstReel() {
        return firstReel;
    }
    
    public void setFirstReel(boolean firstReel) {
        this.firstReel = firstReel;
    }
    
    public Symbol[] getVisibleSymbols() {
        Symbol[] symbols = new Symbol[numLines];
        for( int i=0; i < numLines; i++ ) {
            symbols[i] = getSymbol(i);
        }
        
        return symbols;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        //System.out.println("Setting Value: " + value);
        //System.out.println("    Max Value: " + getMaxValue());
        this.value = value;
        if ( getValue() > getMaxValue()) {
            changeValue(-getMaxValue());
            bumpRevCount();
        }
        if ( getValue() < 0 ) {
            changeValue(getMaxValue());
            bumpRevCount();
        }
        notifyReelValueChangedListeners();
    }
    
    public void changeValue( int amount ) {
        setValue(getValue() + amount);
    }
    
    // TODO:  Need to load this from a file.
    // Create a loader that makes SlotSymbols ahead of time.
    String[] imageName = {
        "slot_01.png",
        "BlueBert.png",
        "YellowFellow.png",
        "alien-head-blue.png",
        "alien-head-green.png",
        "alien-head-orange.png",
        "cell-phone.png",
        "constructing.png",
        "credit-card.png",
        "earth-blue.png",
        "earth-gold.png",
        "experimental-lab.png",
        "lady-bug.png",
        "monitor-display.png",
        "mouse.png",
        "music.png",
        "music-cd.png"
    };
    
    String[] symbolName = {
        "Cherry",
        "BlueBert",
        "YellowFellow",
        "alien-head-blue",
        "alien-head-green",
        "alien-head-orange",
        "cell-phone",
        "constructing",
        "credit-card",
        "earth-blue",
        "earth-gold",
        "experimental-lab",
        "lady-bug",
        "monitor-display",
        "mouse",
        "music",
        "music-cd"
    };
    
    // Multiplier per coin?
    int[][] payout = {
        { 0, 1, 2,  8, 40 },
        { 0, 1, 2,  8, 40 },
        { 0, 0, 2,  8, 40 },
        { 0, 0, 2,  8, 40 },
        { 0, 0, 5, 20, 100 },
        { 0, 0, 5, 20, 100 },
        { 0, 0, 5, 20, 100 },
        { 0, 0, 5, 20, 100 },
        { 0, 0, 10, 40, 200 },
        { 0, 0, 10, 40, 200 },
        { 0, 0, 10, 40, 200 },
        { 0, 0, 10, 40, 200 },
        { 0, 0, 10, 40, 200 },
        { 0, 0, 10, 40, 200 },
        { 0, 0, 10, 40, 200 },
        { 0, 0, 10, 40, 200 },
        { 0, 0, 10, 40, 200 }
    };
    
    int[] odds = {
        2,
        2,
        3,
        3,
        4,
        4,
        5,
        6,
        7,
        9,
        11,
        13,
        15,
        18,
        21,
        24,
        28
    };
     /** Creates a new instance of SymbolPanel */
    private void initSymbols() {
        // TODO:  Create a random ordered list of indexes
        //      - Load the symbols.
        //      - Scramble the list.
        //      - Build the final list.
        // with each symbol on twice.

        // Pad the begining of the reel
        for( int i=0; i<imageName.length; i++) {
            add(
                    new Symbol(
                    symbolName[i],
                    getClass().getResource("../images/" + imageName[i]),
                    payout[i],
                    odds[i]
                    )
            );
        }
        
        Collections.shuffle(this);        
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setRevCount(int revCount) {
        this.revCount = revCount;
    }

    public void reelValueChanged(int value) {
        System.out.println("Reel Value Changed: " + value );
    }
    
}
