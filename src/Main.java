/*
 * Main.java
 *
 * Created on July 30, 2006, 12:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import slots.gui.SlotFrame;
import slots.logic.SlotLogic;

/**
 *
 * @author Mark
 */
public class Main {
    
    /**
     * Creates a new instance of Main
     */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SlotFrame frame = new SlotFrame();
                SlotLogic logic = new SlotLogic( frame );
            }
        });
        
        // TODO code application logic here
    }
    
}
