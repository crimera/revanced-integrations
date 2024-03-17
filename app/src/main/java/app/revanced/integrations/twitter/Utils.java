package app.revanced.integrations.twitter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

import java.util.Arrays;

import app.revanced.integrations.shared.settings.Setting;
import app.revanced.integrations.twitter.settings.Settings;
import app.revanced.integrations.twitter.settings.SettingsActivity;

@SuppressWarnings("unused")
public class Utils {
    @SuppressLint("StaticFieldLeak")
    private static final Context ctx = app.revanced.integrations.shared.Utils.getContext();

    public static void startActivity() {
        Intent intent = new Intent(ctx, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    @SuppressWarnings("deprecation")
    private static String getStringPref(Setting<String> setting) {
        String value = PreferenceManager.getDefaultSharedPreferences(ctx).getString(setting.key, setting.defaultValue);
        if (value.isBlank()) {
            return setting.defaultValue;
        }
        return value;
    }

    @SuppressWarnings("deprecation")
    private static Boolean getBooleanPerf(Setting<Boolean> setting) {
        return PreferenceManager.getDefaultSharedPreferences(ctx).getBoolean(setting.key, setting.defaultValue);
    }

    public static String getPublicFolder() {
        return getStringPref(Settings.VID_PUBLIC_FOLDER);
    }

    public static String getVideoFolder(String filename) {
        return getStringPref(Settings.VID_SUBFOLDER)+"/"+filename;
    }

    public static String[] addPref(String[] prefs, String pref) {
        String[] bigger = Arrays.copyOf(prefs, prefs.length+1);
        bigger[prefs.length] = pref;
        return bigger;
    }

    public static boolean isChirpFontEnabled() {
        return getBooleanPerf(Settings.MISC_FONT);
    }
}
