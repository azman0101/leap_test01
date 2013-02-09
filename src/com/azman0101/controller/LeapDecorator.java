package com.azman0101.controller;

import javax.accessibility.AccessibleAction;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleSelection;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.util.LinkedList;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Vector;

public class LeapDecorator<E extends JComponent> extends AbstractLeapDecorator<E> {

    private static final long serialVersionUID = 8653083813760677619L;
    
    public LeapDecorator(final E component) {
        super(component);
        this.listener = new LeapListener() {
            
            private int i = 0; 
            
            @Override
            public void onInit(Controller arg0) {
                System.out.println("Initialized");
                
            }
            
            
            @Override
            public void onFrame(Controller controller) {
                
                // Get the most recent frame and report some basic information
                Frame frame = controller.frame();
                boolean isHandPresent = frame.hands().empty();
                if (!isHandPresent && getLastFrames().size() > 250) { // Si une main est presente dans le champs et si on a déjà mémorisé assez de position precedentes de main pour faire des calculs relative
                    
                    if (this.getRefFrame() == null)
                       this.setRefFrame(this.validCurrentFrame(frame));                            

                    float relativeRadianAngle = this.getRefFrame().rotationAngle(getLastFrames().get(getLastFrames().size() - 100));
                    if (component instanceof JComboBox<?>) {
                        int count = ((JComboBox<?>) component).getItemCount();
                        AccessibleContext context = ((JComboBox<?>) component).getAccessibleContext();
                        AccessibleComponent componentAcc = context.getAccessibleComponent();
                        AccessibleAction accessibleAct = context.getAccessibleAction();
                        String s = null, a = null;
                        for (int i = 0; i < count; i++) {
                            
                            s = accessibleAct.getAccessibleActionDescription(i);
                            a = acc
                            System.out.println("Description = " + s);
                          }
                        System.out.println(String.valueOf(relativeRadianAngle*180/Math.PI) + " | " + String.valueOf((relativeRadianAngle*180/Math.PI) % (180/count)));
                        if (((int)((relativeRadianAngle*180/Math.PI) % (180/count)) == 0) && relativeRadianAngle != 0.0)
                            i++;
                        ((JComboBox<?>) component).setSelectedIndex(i);
                    }
                    
                } 
                if (getLastFrames().size() >= 300) {
                    getLastFrames().removeFirst();
                  }
                if (frame.isValid())
                    getLastFrames().add(frame);
              /*  System.out.println("Frame id: " + frame.id()
                                 + ", timestamp: " + frame.timestamp()
                                 + ", hands: " + frame.hands().count()
                                 + ", fingers: " + frame.fingers().count()
                                 + ", tools: " + frame.tools().count());

                
                if (!frame.hands().empty()) {
                    // Get the first hand
                    Hand hand = frame.hands().get(0);

                    // Check if the hand has any fingers
                    FingerList fingers = hand.fingers();
                    if (!fingers.empty()) {
                        // Calculate the hand's average finger tip position
                        Vector avgPos = Vector.zero();
                        for (Finger finger : fingers) {
                            avgPos = avgPos.plus(finger.tipPosition());
                        }
                        avgPos = avgPos.divide(fingers.count());
                        System.out.println("Hand has " + fingers.count()
                                         + " fingers, average finger tip position: " + avgPos);
                    }

                    // Get the hand's sphere radius and palm position
                    System.out.println("Hand sphere radius: " + hand.sphereRadius()
                                     + " mm, palm position: " + hand.palmPosition());

                    // Get the hand's normal vector and direction
                    Vector normal = hand.palmNormal();
                    Vector direction = hand.direction();

                    // Calculate the hand's pitch, roll, and yaw angles
                    System.out.println("Hand pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
                                     + "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
                                     + "yaw: " + Math.toDegrees(direction.yaw()) + " degrees\n");
                }
                */
            }
            
            @Override
            public void onExit(Controller arg0) {
                System.out.println("Exited");
                
            }
            
            @Override
            public void onDisconnect(Controller arg0) {
                System.out.println("Disonnected");
                
            }
            
            @Override
            public void onConnect(Controller arg0) {
                System.out.println("Connected");
                
            }
            
            @Override
            protected void finalize() {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void delete() {
                // TODO Auto-generated method stub
                
            }

            @Override
            protected void swigDirectorDisconnect() {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void swigReleaseOwnership() {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void swigTakeOwnership() {
                // TODO Auto-generated method stub
                
            }
        }; 
    }

}
