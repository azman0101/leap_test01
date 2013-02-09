package example;

import java.util.ArrayList;
import java.util.Iterator;

import com.leapmotion.leap.Finger;
import com.onformative.leap.LeapMotionP5;

import processing.core.PApplet;
import processing.core.PVector;

public class TheParticuleSystem extends PApplet {
  LeapMotionP5 leap;
  ParticleSystem ps;
  
  public void setup() {
      size(640,360);
      smooth();
      ps = new ParticleSystem(new PVector(width/2,50));
      leap = new LeapMotionP5(this);
    }





  public void draw() {
    background(0);
    fill(255);
    PVector origin = new PVector();
    for (Finger finger : leap.getFingerList()) {
        origin.set(leap.leapToScreenX(finger.tipPosition().getX()), leap.leapToScreenY(finger.tipPosition().getY()), leap.leapToScreenZ(finger.tipPosition().getZ()));
        ps.addParticle(origin);
        ps.run();
    }
  }

//A class to describe a group of Particles
//An ArrayList is used to manage the list of Particles 

class ParticleSystem {
 ArrayList<Particle> particles;
 PVector origin;

 ParticleSystem(PVector location) {
   origin = location.get();
   particles = new ArrayList<Particle>();
 }

 void addParticle(PVector position) {
   particles.add(new Particle(position));
 }
 

 void run() {
   Iterator<Particle> it = particles.iterator();
   while (it.hasNext()) {
     Particle p = it.next();
     p.run();
     if (p.isDead()) {
       it.remove(); 
     }
   }
 }
}



//A simple Particle class

class Particle {
 PVector location;
 PVector velocity;
 PVector acceleration;
 float lifespan;

 Particle(PVector l) {
   acceleration = new PVector(0.0F,0.05F);
   velocity = new PVector(random(-1,1),random(-2,0));
   location = l.get();
   lifespan = 255.0F;
 }

 void run() {
   update();
   display();
 }

 // Method to update location
 void update() {
   velocity.add(acceleration);
   location.add(velocity);
   lifespan -= 1.0;
 }

 // Method to display
 void display() {
   stroke(255,lifespan);
   fill(255,lifespan);
   ellipse(location.x,location.y,8,8);
 }
 
 // Is the particle still useful?
 boolean isDead() {
   if (lifespan < 0.0) {
     return true;
   } else {
     return false;
   }
 }
}
  public void stop() {
    leap.stop();
    super.stop();
  }
}