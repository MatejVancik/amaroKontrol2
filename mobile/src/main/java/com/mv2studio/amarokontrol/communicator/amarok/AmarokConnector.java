package com.mv2studio.amarokontrol.communicator.amarok;

/**
 * Created by matej on 22/10/2016.
 */
public class AmarokConnector {

    private static AmarokConnector sInstance;

    private AmarokConnector() { }

    public static AmarokConnector getInstance() {
        if (sInstance == null) sInstance = new AmarokConnector();
        return sInstance;
    }



}
