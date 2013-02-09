package com.azman0101.controller;

import java.util.LinkedList;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

public abstract class LeapListener extends Listener {
    
    private LinkedList<Frame> lastFrames = new LinkedList<Frame>();
    private Frame refFrame = null;  

    public abstract void delete();
    
    protected abstract void finalize();
    
    public abstract void onConnect(Controller arg0);
   
    public abstract void onDisconnect(Controller arg0);

    public abstract void onExit(Controller arg0);

    public abstract void onFrame(Controller arg0);
    
    public abstract void onInit(Controller arg0);

    protected abstract void swigDirectorDisconnect();
    
    public abstract void swigReleaseOwnership();
    
    public abstract void swigTakeOwnership();

    public LinkedList<Frame> getLastFrames() {
        return lastFrames;
    }
    
    public Frame validCurrentFrame(Frame frame) {
        if (frame != null && frame.isValid()) {
          return frame;
        } else {
          return validCurrentFrame(new Frame());
        }
      }

    public void setLastFrames(LinkedList<Frame> lastFrames) {
        this.lastFrames = lastFrames;
    }

    public Frame getRefFrame() {
        return refFrame;
    }

    public void setRefFrame(Frame refFrame) {
        this.refFrame = refFrame;
    }

    

}
