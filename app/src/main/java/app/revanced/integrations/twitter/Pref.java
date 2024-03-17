package app.revanced.integrations.twitter;

import app.revanced.integrations.twitter.Utils;
import app.revanced.integrations.twitter.settings.Settings;

public class Pref {

    public static boolean isChirpFontEnabled() {return Utils.getBooleanPerf(Settings.MISC_FONT);}

    public static String getPublicFolder() { return Utils.getStringPref(Settings.VID_PUBLIC_FOLDER);}

    public static String getVideoFolder(String filename) {
        return Utils.getStringPref(Settings.VID_SUBFOLDER)+"/"+filename;
    }


}