package com.mv2studio.amarokontrol.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.mv2studio.amarokontrol.App;
import com.mv2studio.amarokontrol.R;
import com.mv2studio.amarokontrol.communicator.IConnector;
import com.mv2studio.amarokontrol.configuration.Config;
import com.mv2studio.amarokontrol.configuration.GlideModule;
import com.mv2studio.amarokontrol.manager.NowPlayingManager;
import com.mv2studio.amarokontrol.model.CurrentState;
import com.mv2studio.amarokontrol.model.PlayingState;
import com.mv2studio.amarokontrol.model.Song;
import com.mv2studio.amarokontrol.ui.activity.MainActivity;

import java.io.File;

import rx.Subscriber;
import rx.Subscription;

import static com.mv2studio.amarokontrol.configuration.Constants.ACTION_EXIT;
import static com.mv2studio.amarokontrol.configuration.Constants.ACTION_NEXT;
import static com.mv2studio.amarokontrol.configuration.Constants.ACTION_PLAY;
import static com.mv2studio.amarokontrol.configuration.Constants.ACTION_PREV;

/**
 * Created by matej on 17.11.14.
 */
public class AppNotification {

    public static final int NOTIFICATION_ID = 1;

    private Context mContext;
    private RemoteViews mExtendView;
    private RemoteViews mSmallView;

    private Notification mNotification;
    private boolean isForegroundEnabled;

    private CurrentState mCurrentState;
    private NowPlayingManager mNowPlayingManager;
    private Subscription mSubscription;
    private IConnector mConnector;

    public AppNotification() {
        mContext = App.getAppContext();

        mSubscription = mNowPlayingManager.subscribe(this::onSongUpdated, this::onErrorReceived);

        buildNotification();
        prepareViews();
    }

    public void unregisterCallbacks() {
        mSubscription.unsubscribe();
    }

    private void onSongUpdated(CurrentState currentState) {
        mCurrentState = currentState;
        updateNotification(mCurrentState.getSong());
    }

    private void onErrorReceived(Throwable error) {
        // update with song with some errors
        updateNotification(null);
    }

    private void prepareViews() {

        // set layouts
        int extendViewID = (Config.showNotification()) ? R.layout.notification_photo : R.layout.notification_expanded;
        mExtendView = new RemoteViews(mContext.getPackageName(), extendViewID);
        mSmallView = new RemoteViews(mContext.getPackageName(), R.layout.notification_small);

        Intent nextIntent = new Intent(ACTION_NEXT);
        PendingIntent pendingNext = PendingIntent.getBroadcast(mContext, 0, nextIntent, 0);
        mExtendView.setOnClickPendingIntent(R.id.notifyNext, pendingNext);
        mSmallView.setOnClickPendingIntent(R.id.notifyNext, pendingNext);

        Intent prevIntent = new Intent(ACTION_PREV);
        PendingIntent pendingPrev = PendingIntent.getBroadcast(mContext, 0, prevIntent, 0);
        mExtendView.setOnClickPendingIntent(R.id.notifyPrev, pendingPrev);

        Intent playIntent = new Intent(ACTION_PLAY);
        PendingIntent pendingPlay = PendingIntent.getBroadcast(mContext, 0, playIntent, 0);
        mExtendView.setOnClickPendingIntent(R.id.notifyPlay, pendingPlay);
        mSmallView.setOnClickPendingIntent(R.id.notifyPlay, pendingPlay);

        Intent exitIntent = new Intent(ACTION_EXIT);
        PendingIntent pendingExit = PendingIntent.getBroadcast(mContext, 0, exitIntent, 0);
        mExtendView.setOnClickPendingIntent(R.id.notifyExit, pendingExit);
        mSmallView.setOnClickPendingIntent(R.id.notifyExit, pendingExit);

        // bind views to notification
        mNotification.contentView = mSmallView;
        mNotification.bigContentView = mExtendView;
    }

    private void buildNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.drawable.ic_stat_notify);
//                .setContentTitle((mCurrentSong != null) ? mCurrentSong.getTitle() : mContext.getResources().getString(R.string.app_name))
//                .setContentText((mCurrentSong != null) ? mCurrentSong.getArtist() : mContext.getResources().getString(R.string.not_playing));

        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        builder.setContentIntent(resultPendingIntent);
        mNotification = builder.build();
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;
    }

    public void updateNotification(Song newSong) {

        // no notification to update
        if(!isForegroundEnabled) return;

//        if(FileHelper.isExternalStorageReadable()) {
        if(true) {

            // UPDATE COVER
            String path = "";
//            if(mContext.getExternalCacheDir() != null)
//                path = mContext.getExternalCacheDir().toString() + "/" +
//                       FileHelper.getSafeString(newSong.getArtist()
//                       + newSong.getAlbum()) + "_mini.jpg";

            File file = new File(path);
            if(file.exists()) {
                Uri uri = Uri.fromFile(file);
                mSmallView.setImageViewUri(R.id.notifyCover, uri);
                mExtendView.setImageViewUri(R.id.notifyCover, uri);
            } else {
                mSmallView.setImageViewResource(R.id.notifyCover, R.drawable.ic_launcher);
                mExtendView.setImageViewResource(R.id.notifyCover, R.drawable.ic_launcher);
            }

            // UPDATE PHOTO
            path = "";
//            if (mContext.getExternalCacheDir() != null) {
//                path = mContext.getExternalCacheDir().toString() + File.separator
//                        + FileHelper.getSafeString(newSong.getArtist()) + ".jpg";
//            }

            file = new File(path);
            if (file.exists()) {
                Uri uri = Uri.fromFile(file);
                mExtendView.setImageViewUri(R.id.notifyPhoto, uri);
            } else {
                mExtendView.setImageViewResource(R.id.notifyPhoto, R.drawable.no_photo);
            }
        }

        mExtendView.setTextViewText(R.id.notifyArtist, newSong.getArtist());
        mExtendView.setTextViewText(R.id.notifyTitle, newSong.getTitle());
        mExtendView.setTextViewText(R.id.notifyAlbum, newSong.getAlbum());

        mSmallView.setTextViewText(R.id.notifyArtist, newSong.getArtist());
        mSmallView.setTextViewText(R.id.notifyTitle, newSong.getTitle());

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);
    }

    /**
     * Update notification content with current song and get system notification
     * @return
     */
    public Notification prepareForeground() {
        isForegroundEnabled = true;
        if (mCurrentState != null) {
            if(mCurrentState.getSong() != null) updateNotification(mCurrentState.getSong());
            setPlayPauseButton(mConnector.resolvePlayingState(mCurrentState.getPlayingState()));
        }
        return mNotification;
    }

    public void foregroundStoped() {
        isForegroundEnabled = false;
    }

    public void setPlayPauseButton(PlayingState state) {
        // no notification to update
        if(!isForegroundEnabled) return;

        int img = (state == PlayingState.PLAYING) ? R.drawable.pause : R.drawable.play;
        mSmallView.setImageViewResource(R.id.notifyPlay, img);
        mExtendView.setImageViewResource(R.id.notifyPlay, img);

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);
    }

}
