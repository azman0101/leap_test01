package com.azman0101.controller;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

public abstract class AbstractLeapDecorator<E extends JComponent> extends JComponent implements FocusListener  {

    private static final long serialVersionUID = -8313014745932930101L;
    protected E child;
    protected Frame frame;
    protected LeapListener listener;

    @Override
    public void focusGained(FocusEvent e) {
        
        try {
            LeapController controller = LeapController.getInstance();
            controller.addListener(this);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this.child.getParent(), "Votre leapMotion est-il bien branché ?");
        }
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        
        try {
            LeapController controller = LeapController.getInstance();
            controller.removeListener(this);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this.child.getParent(), "Votre leapMotion est-il bien branché ?");
        }
        
    }

    public E getComponent() {
        return (E) this.child;
        
    }
    /**
     * @param component
     */
    public AbstractLeapDecorator(E component) {
        //super();
        
        this.child = component; 
        this.listener = null;
        (this.child).addFocusListener(this);
        this.add(this.child);
    }

    public Listener getLeapListener() {    
        return this.listener;
    }
    
    
   
}
