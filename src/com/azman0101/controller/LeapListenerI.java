package com.azman0101.controller;

import com.leapmotion.leap.Controller;

public interface LeapListenerI {

       
        public void swigTakeOwnership();
        
   
        public void swigReleaseOwnership();
        
        
        public void onInit(Controller arg0);
        
  
        public void onFrame(Controller arg0);
        
        
        public void onExit(Controller arg0);
        
        
        public void onDisconnect(Controller arg0);
        
        
        public void onConnect(Controller arg0);    
        
        public void delete();
    
    
}
