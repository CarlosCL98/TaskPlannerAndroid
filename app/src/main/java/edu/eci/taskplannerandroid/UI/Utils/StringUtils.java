package edu.eci.taskplannerandroid.UI.Utils;

import android.text.TextUtils;
import android.util.Patterns;

public class StringUtils {

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
