package com.mv2studio.amarokontrol.ui.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Returns root layout resource for this activity.
     */
    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        getExtras(getIntent());
    }

    protected void getExtras(Intent intent) {

    }

    /**
     * Adds fragment into specific layout. Note this uses simple
     * {@link android.support.v4.app.FragmentTransaction#add(int, Fragment, String)}
     * @param fragment fragment to insert
     * @param res layout id which will host fragment
     */
    public void addFragmentInto(Fragment fragment, @IdRes int res) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(res, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .show(fragment)
                .commit();
    }

}
