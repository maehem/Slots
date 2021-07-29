/*
 * Symbol.java
 *
 * Created on August 5, 2006, 2:07 PM
 *
 * Copyright 2008 Maehem Media, Inc.
 * Use is subject to license terms.
 */

package slots.logic;

import java.net.URL;

/**
 *
 * @author Mark
 */
public class Symbol {
    private String name = "";
    private URL imageURL;
    
    // Chance that the wheel will stop at this symbol.
    private int odds = 10;
    // Per coin payout for number of this symbol on a payline.
    private int payout[] = { 0, 0, 10, 40, 200 };
    // Max coins bet payout multiplier.
    private double maxBetMultipier = 1.2;
    
    public Symbol( String name ) {
        this.name = name;
    }
    
    public Symbol( String name, URL imageURL ) {
        this( name );
        this.imageURL = imageURL;
    }
    
    public Symbol( String name, URL imageURL, int[] payout ) {
        this( name, imageURL );
        this.payout = payout;
    }
    
    public Symbol( String name, URL imageURL, int[] payout, int odds ) {
        this( name, imageURL, payout );
        this.odds = odds;
    }
    
    public URL getImageURL() {
        return imageURL;
    }
    
    public void setImageURL( URL imageURL ) {
        this.imageURL = imageURL;
    }
    
    public int getOdds() {
        return odds;
    }
    
    public void setOdds(int odds) {
        this.odds = odds;
    }
    
    public int[] getPayout() {
        return payout;
    }
    
    public void setPayout(int[] payout) {
        this.payout = payout;
    }
    
    public double getMaxBetMultipier() {
        return maxBetMultipier;
    }
    
    public void setMaxBetMultipier(double maxBetMultipier) {
        this.maxBetMultipier = maxBetMultipier;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
