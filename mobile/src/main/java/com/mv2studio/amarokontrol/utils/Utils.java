package com.mv2studio.amarokontrol.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.mv2studio.amarokontrol.App;

import java.text.Normalizer;
import java.util.Collection;

/**
 * Created by matej on 22/10/2016.
 */

public class Utils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmptyNull(CharSequence text) {
        return TextUtils.isEmpty(text) || "null".equals(text) || "NULL".equals(text);
    }

    public static String normalizeString(String text) {
        if (TextUtils.isEmpty(text)) return text;
        return Normalizer
                .normalize(text, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = App.getAppContext().getResources().getDisplayMetrics();
        return (int)((dp * displayMetrics.density) + 0.5);
    }

    public static void hideKeyboard(Activity activity) {
        if(activity == null) return;
        View view = activity.getCurrentFocus();
        if (view != null) {
            view.clearFocus();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) App.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hideKeyboardOnTouchOutside(View view, final Activity activity) {
        if(!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                hideKeyboard(activity);
                return false;
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hideKeyboardOnTouchOutside(innerView, activity);
            }
        }
    }

    public static String colorAsHex(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

}
