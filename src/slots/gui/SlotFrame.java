/*
 * SlotFrame.java
 *
 * Created on July 30, 2006, 12:14 PM
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package slots.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Mark
 */
public class SlotFrame extends JFrame {
    private int numReels = 5;
    private ReelPanel reelPanel = new ReelPanel(3,5);
    private ButtonPanel buttons = new ButtonPanel(getReelPanel());
    
    /** Creates a new instance of SlotFrame */
    public SlotFrame() {
        this("Swingin' Slots!");
    }
    
    /** Creates a new instance of SlotFrame */
    public SlotFrame( String title ) {
        super(title);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(getReelPanel(), BorderLayout.CENTER);
        add(getButtons(), BorderLayout.SOUTH);
        validate();
        pack();
        setVisible(true);
        for( int i=0; i<reelPanel.getReelCount(); i++ ) {
            reelPanel.getReel(i).setMaximum();
        }

        repaint();
    }
    
    public ReelPanel getReelPanel() {
        return reelPanel;
    }
    
    public ButtonPanel getButtons() {
        return buttons;
    }
    
}
