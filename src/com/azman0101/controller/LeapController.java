package com.azman0101.controller;

import com.leapmotion.leap.Config;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.ScreenList;

public final class LeapController extends Controller {

    private static volatile LeapController instance = null;

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static LeapController getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (LeapController.instance == null) {
           // Le mot-clé synchronized sur ce bloc empêche toute instanciation multiple même par différents "threads".
           // Il est TRES important.
           synchronized(LeapController.class) {
             if (LeapController.instance == null) {
                 LeapController.instance = new LeapController();
             }
           }
        }
        return LeapController.instance;
    }
    /**
     * 
     */
    private LeapController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    private LeapController(Listener arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     * @param arg1
     */
    private LeapController(long arg0, boolean arg1) {
        super(arg0, arg1);
    }

    public boolean addListener(AbstractLeapDecorator<?> leapDecorator) {
        return super.addListener(leapDecorator.getLeapListener());
    }

    @Override
    public ScreenList calibratedScreens() {

        return super.calibratedScreens();
    }

    @Override
    public Config config() {

        return super.config();
    }

    @Override
    public synchronized void delete() {

        super.delete();
    }

    @Override
    protected void finalize() {

        super.finalize();
    }

    @Override
    public Frame frame() {

        return super.frame();
    }

    @Override
    public Frame frame(int arg0) {

        return super.frame(arg0);
    }

    @Override
    public boolean isConnected() {

        return super.isConnected();
    }

    public boolean removeListener(AbstractLeapDecorator<?> leapDecorator) {
        return super.removeListener(leapDecorator.getLeapListener());
    }

}
