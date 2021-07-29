/*
 * SymbolImageIcon.java
 *
 * Created on August 5, 2006, 2:07 PM
 *
 * Copyright 2008 Maehem Media, Inc.
 * Use is subject to license terms.
 */

package slots.gui;

import java.net.URL;
import javax.swing.ImageIcon;
import slots.logic.Symbol;

/**
 *
 * @author Mark
 */
public class SymbolImageIcon extends ImageIcon {
    private Symbol symbol;
    
    public SymbolImageIcon( Symbol symbol ) {
        super( symbol.getImageURL() );
        this.symbol = symbol;
    }
    
    public SymbolImageIcon( URL imageURL, String name ) {
        this( new Symbol( name ) );
    }
    
    public SymbolImageIcon( URL imageURL, String name, int[] payout ) {
        this( new Symbol( name, imageURL, payout ) );
    }
    
    public SymbolImageIcon( URL imageURL, String name, int[] payout, int odds ) {
        this( new Symbol( name, imageURL, payout, odds ));
    }
    
    public int getOdds() {
        return symbol.getOdds();
    }
    
    public void setOdds(int odds) {
        symbol.setOdds(odds);
    }
    
    public int[] getPayout() {
        return symbol.getPayout();
    }
    
    public void setPayout(int[] payout) {
        symbol.setPayout(payout);
    }
    
    public double getMaxBetMultipier() {
        return symbol.getMaxBetMultipier();
    }
    
    public void setMaxBetMultipier(double maxBetMultipier) {
        symbol.setMaxBetMultipier(maxBetMultipier);
    }
    
    public String getName() {
        return symbol.getName();
    }
    
    public void setName(String name) {
        symbol.setName(name);
    }
}
