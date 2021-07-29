/*
 * ReelEventListener.java
 *
 * Created on August 12, 2006, 12:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slots.logic;

/**
 *
 * @author Mark
 */
public interface ReelEventListener {
    public void observedReelStopped();
    public void reelValueChanged(int value);
}
