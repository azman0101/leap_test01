package example;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.onformative.leap.LeapMotionP5;
import com.onformative.leap.gestures.OneDollarGestures;
import com.onformative.leap.gestures.PullGesture;
import com.onformative.leap.gestures.PushGesture;
import com.onformative.leap.gestures.SwipeDownGesture;
import com.onformative.leap.gestures.SwipeLeftGesture;
import com.onformative.leap.gestures.SwipeRightGesture;
import com.onformative.leap.gestures.SwipeUpGesture;
import com.onformative.leap.gestures.VictoryGesture;

import controlP5.ControlP5;

import processing.core.PApplet;

public class GestureExample extends PApplet implements MouseListener, MouseMotionListener {

  LeapMotionP5 leap;
  
  OneDollarGestures one;
  
  SwipeLeftGesture sl;
  SwipeRightGesture sr;
  SwipeUpGesture su;
  SwipeDownGesture sd;
  PushGesture pg;
  PullGesture pug;

  VictoryGesture vg;

  ControlP5 cp5;
  boolean showGui = false;
  String lastGesture = "";
  
  @Override
public void init() {
    // TODO Auto-generated method stub
    super.init();
    addMouseListener( this );
    addMouseMotionListener( this );
}

public void setup() {
    size(500, 500);
    leap = new LeapMotionP5(this);
    one = new OneDollarGestures(this, leap);
    
    one.addGesture("circle");
    one.addGesture("rectangle");
    one.addGesture("triangle");

    one.start();
    
    sl = new SwipeLeftGesture(this, leap);
    sr = new SwipeRightGesture(this, leap);
    su = new SwipeUpGesture(this, leap);
    sd = new SwipeDownGesture(this, leap);
    pg = new PushGesture(this, leap);
    pug = new PullGesture(this, leap);
    vg = new VictoryGesture(this, leap);

    cp5 = new ControlP5(this);
    cp5.setAutoDraw(false);

    cp5.addSlider("velocityThreshold", 0, 2500, 1500, 20, 20, 100, 10);
    cp5.addSlider("gestureTimeoutMillis", 0, 1000, 100, 20, 40, 100, 10);
    
    this.execute();
  }

  public void velocityThreshold(int val) {
    sl.setVelocityThreshold(val);
    sr.setVelocityThreshold(val);
    su.setVelocityThreshold(val);
    sd.setVelocityThreshold(val);
    pg.setVelocityThreshold(val);
    pug.setVelocityThreshold(val);
  }

  public void gestureTimeoutMillis(int val) {
    sl.setGestureTimeoutMillis(val);
    sr.setGestureTimeoutMillis(val);
    su.setGestureTimeoutMillis(val);
    sd.setGestureTimeoutMillis(val);
    pg.setGestureTimeoutMillis(val);
    pug.setGestureTimeoutMillis(val);
  }

  public void draw() {
    fill(0, 40);
    noStroke();
    rect(0, 0, width, height);
    
    one.draw();
    one.update();
    
    if (sl.check()) {
      System.out.println("swipe left");
      fill(255, 0, 0);
      rect(0, 0, width, height);
    } else if (sr.check()) {
      System.out.println("swipe right");
      fill(0, 255, 0);
      rect(0, 0, width, height);
    } else if (su.check()) {
      System.out.println("swipe up");
      fill(0, 0, 255);
      rect(0, 0, width, height);
    } else if (sd.check()) {
      System.out.println("swipe down");
      fill(255, 255, 0);
      rect(0, 0, width, height);
    } else if (pg.check()) {
      System.out.println("pushed");
      fill(255, 0, 255);
      rect(0, 0, width, height);
    } else if (pug.check()) {
      System.out.println("pulled");
      fill(0, 255, 255);
      rect(0, 0, width, height);
    }

    if (showGui) {
      cp5.draw();
    }
  }

  public void keyPressed() {
    if (key == ' ') {
      showGui = !showGui;
    }
  }
  
  public void detected(String gesture, int x, int y, int c_x, int c_y) {
      System.out.println(gesture + " recognized");
      lastGesture = gesture;
    }


  public void stop() {
    leap.stop();
    super.stop();
  }

  
@Override
public void mouseEntered() {
    // TODO Auto-generated method stub
    super.mouseEntered();
   System.out.println("Mouse dans la souricière");

}

@Override
public void mouseExited() {
    // TODO Auto-generated method stub
    super.mouseExited();
    System.out.println("Mouse dans la échappée");

}

@Override
public void mouseMoved() {
    // TODO Auto-generated method stub
    super.mouseMoved();
   // System.out.println("Mouse moved");
}

@Override
public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    super.mouseMoved(e);
   // System.out.println("Mouse moved !!");
}

public void execute() {
    new Thread( new MouseMoveThread( 100, 50, 50, 10 ) ).start();
}

private class MouseMoveThread implements Runnable {

    private Robot robot;
    private int startX;
    private int startY;
    private int currentX;
    private int currentY;
    private int xAmount;
    private int yAmount;
    private int xAmountPerIteration;
    private int yAmountPerIteration;
    private int numberOfIterations;
    private long timeToSleep;

    public MouseMoveThread( int xAmount, int yAmount, int numberOfIterations, long timeToSleep ) {

        this.xAmount = xAmount;
        this.yAmount = yAmount;
        this.numberOfIterations = numberOfIterations;
        this.timeToSleep = timeToSleep;

        try {

            robot = new Robot();

            Point startLocation = MouseInfo.getPointerInfo().getLocation();
            startX = startLocation.x;
            startY = startLocation.y;

        } catch ( AWTException exc ) {
            exc.printStackTrace();
        }

    }

    @Override
    public void run() {

        currentX = startX;
        currentY = startY;

        xAmountPerIteration = xAmount / numberOfIterations;
        yAmountPerIteration = yAmount / numberOfIterations;

        while ( currentX < startX + xAmount &&
                currentY < startY + yAmount ) {

            currentX += xAmountPerIteration;
            currentY += yAmountPerIteration;

            robot.mouseMove( currentX, currentY );

            try {
                Thread.sleep( timeToSleep );
            } catch ( InterruptedException exc ) {
                exc.printStackTrace();
            }

        }

    }

}

}
