/*
 * ReelEvent.java
 *
 * Created on August 12, 2006, 12:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package slots.logic;

/**
 *
 * @author Mark
 */
public interface ReelEvent {
    void addReelEventListener( Object o );
    void removeReelEventListener( Object o );
    void notifyReelStoppedListeners();
    void notifyReelValueChangedListeners();
}
