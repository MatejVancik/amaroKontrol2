package com.mv2studio.amarokontrol.manager;

import com.mv2studio.amarokontrol.communicator.IConnector;
import com.mv2studio.amarokontrol.model.CurrentState;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.ReplaySubject;

/**
 * Created by matej on 22/10/2016.
 */

public class NowPlayingManager {

    private ReplaySubject<CurrentState> mObservable;

    private static NowPlayingManager sInstance;
    private IConnector mConnector; // TODO fillup connector

    private NowPlayingManager() {
        mObservable = ReplaySubject.create(1);
    }

    public static NowPlayingManager getInstance() {
        if (sInstance == null) sInstance = new NowPlayingManager();
        return sInstance;
    }

    public Subscription subscribe(Subscriber<CurrentState> subscriber) {
        return mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    public Subscription subscribe(Action1<CurrentState> onNext) {
        return mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(onNext);
    }

    public Subscription subscribe(Action1<CurrentState> onNext, Action1<Throwable> onError) {
        return mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError);
    }

    public void playPause() {
        mConnector.playPause(null);
    }

    public void next() {
        mConnector.next(null);
    }

    public void prev() {
        mConnector.prev(null);
    }

    public void getPlayNowState() {
        mConnector.getPlayNowState(mObservable::onNext);
    }

}
