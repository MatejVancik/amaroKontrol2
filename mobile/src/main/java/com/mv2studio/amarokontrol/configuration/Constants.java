package com.mv2studio.amarokontrol.configuration;

import com.mv2studio.amarokontrol.BuildConfig;

/**
 * Created by matej on 23/10/2016.
 */

public abstract class Constants {

    public static final String PACKAGE_NAME = BuildConfig.APPLICATION_ID;
    public static final String ACTION_PLAY = PACKAGE_NAME + ".PLAY";
    public static final String ACTION_PREV = PACKAGE_NAME + ".PREV";
    public static final String ACTION_NEXT = PACKAGE_NAME + ".NEXT";
    public static final String ACTION_EXIT = PACKAGE_NAME + ".EXIT";

}
