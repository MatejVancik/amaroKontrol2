package com.mv2studio.amarokontrol.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mv2studio.amarokontrol.utils.Utils;

import butterknife.ButterKnife;

/**
 * Created by matej on 22/10/2016.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            getExtras(getArguments());
    }

    /**
     * Called during onCreate if needs to resolve arguments. If arguments == null method is not called.
     * @param args bundle passed to this fragment
     */
    public void getExtras(@NonNull Bundle args) {

    }

    /**
     * Returns root layout resource for this fragment.
     */
    @LayoutRes
    public abstract int getLayoutRes();

    /**
     * Place to setup all views. This is called only during initial setup, within
     * {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)} after inflating layout
     * provided by {@link BaseFragment#getLayoutRes()} and binding views using ButterKnife.
     * @param rootView Root view of this fragment.
     */
    public abstract void setupViews(View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, rootView);

        // set up keyboard hiding on this fragment if it's necessary. Don't forget to implement
        // hideKeyboardOnTouchOutside() method if you need different behavior.
        if (hideKeyboardOnTouchOutside())
            Utils.hideKeyboardOnTouchOutside(rootView, getActivity());

        setupViews(rootView);

        return rootView;
    }

    /**
     * Returns true if keyboard has to be dismissed when tapped outside of EditText in this fragment.
     */
    public boolean hideKeyboardOnTouchOutside() {
        return true;
    }
}
