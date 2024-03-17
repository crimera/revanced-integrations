package app.revanced.integrations.twitter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

import java.util.Arrays;

import app.revanced.integrations.shared.settings.Setting;
import app.revanced.integrations.twitter.settings.Settings;
import app.revanced.integrations.twitter.settings.SettingsActivity;
import app.revanced.integrations.shared.settings.preference.SharedPrefCategory;

@SuppressWarnings("unused")
public class Utils {
    @SuppressLint("StaticFieldLeak")
    private static final Context ctx = app.revanced.integrations.shared.Utils.getContext();
    private static SharedPrefCategory sp = new SharedPrefCategory("com.twitter.android_preferences");


    private static void startActivity(Class cls) {
        Intent intent = new Intent(ctx, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    public static void startSettingsActivity(){
        startActivity(SettingsActivity.class);
    }

    @SuppressWarnings("deprecation")
    public static String getStringPref(Setting<String> setting) {
        String value = sp.getString(setting.key, setting.defaultValue);
        if (value.isBlank()) {
            return setting.defaultValue;
        }
        return value;
    }

    @SuppressWarnings("deprecation")
    public static Boolean getBooleanPerf(Setting<Boolean> setting) {
        return sp.getBoolean(setting.key, setting.defaultValue);
    }

    public static String[] addPref(String[] prefs, String pref) {
        String[] bigger = Arrays.copyOf(prefs, prefs.length+1);
        bigger[prefs.length] = pref;
        return bigger;
    }

}
