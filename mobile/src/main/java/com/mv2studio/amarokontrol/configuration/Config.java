package com.mv2studio.amarokontrol.configuration;

import com.mv2studio.amarokontrol.utils.Storage;

/**
 * Created by matej on 23/10/2016.
 */

public class Config {

    private static String ipPortKey = "pref_remote_server";
    private static String useVolButtonsKey = "pref_volume_change";
    private static String showNotifyKey = "pref_show_notify";
    private static String notifyShowPhotoKey = "pref_notify_photo";
    private static String closePlaylistKey = "pref_close_playlist";
    private static String notifyUpdateIntervalKey = "pref_notify_interval";
    private static String updateIntervalKey = "pref_interval";
    private static String volumeStepKey = "pref_vol_step";
    private static String clearCacheKey = "pref_clear_cache";
    private static String use3gKey = "pref_allow_3g";
    private static String blurKey = "pref_blur";

    public static boolean useVolumeButton() {
        return Storage.getBoolValue(useVolButtonsKey);
    }

    public static void setUseVolumeButtons(boolean useVolumeButtons) {
        Storage.storeBoolValue(Config.useVolButtonsKey, useVolumeButtons);
    }

    public static boolean showNotification() {
        return Storage.getBoolValue(showNotifyKey);
    }

    public static void setShowNotification(boolean showNotification) {
        Storage.storeBoolValue(showNotifyKey, showNotification);
    }

}
