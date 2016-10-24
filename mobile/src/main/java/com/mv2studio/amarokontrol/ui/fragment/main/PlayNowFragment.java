package com.mv2studio.amarokontrol.ui.fragment.main;

import android.view.View;

import com.mv2studio.amarokontrol.R;
import com.mv2studio.amarokontrol.manager.NowPlayingManager;
import com.mv2studio.amarokontrol.model.CurrentState;
import com.mv2studio.amarokontrol.ui.fragment.BaseFragment;

import rx.Subscription;

/**
 * Created by matej on 22/10/2016.
 */

public class PlayNowFragment extends BaseFragment {

    private NowPlayingManager mNowPlayingManager;
    private Subscription mSubscription;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_play_now;
    }

    @Override
    public void setupViews(View rootView) {
        mSubscription = mNowPlayingManager.subscribe(this::bindData);
    }

    public void bindData(CurrentState state) {

    }

    public void onClickPlayPause() {
        mNowPlayingManager.playPause();
    }

    public void onNextClick() {
        mNowPlayingManager.next();
    }

    public void onPrevClick() {
        mNowPlayingManager.prev();
    }
}
