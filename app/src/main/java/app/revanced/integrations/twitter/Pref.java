package app.revanced.integrations.twitter;

import app.revanced.integrations.twitter.settings.Settings;

import java.util.ArrayList;

public class Pref {

    public static boolean isChirpFontEnabled() {return Utils.getBooleanPerf(Settings.MISC_FONT);}

    public static String getPublicFolder() { return Utils.getStringPref(Settings.VID_PUBLIC_FOLDER);}

    public static String getVideoFolder(String filename) {
        return Utils.getStringPref(Settings.VID_SUBFOLDER)+"/"+filename;
    }

    public static String getSharingLink(String link) {
        String domain = Utils.getStringPref(Settings.CUSTOM_SHARING_DOMAIN);
        return link.replace("x", domain);
    }

    public  static ArrayList hideRecommendedUsers(ArrayList users) {
        if (Utils.getBooleanPerf(Settings.MISC_HIDE_RECOMMENDED_USERS)) { return null; }
        return users;
    }

    public static ArrayList liveThread(ArrayList fleets) {
        if (Utils.getBooleanPerf(Settings.TIMELINE_HIDE_LIVETHREADS)) { return null; }
        return fleets;
    }

    public static boolean hideBanner() {
        return !Utils.getBooleanPerf(Settings.TIMELINE_HIDE_BANNER);
    }

    public static int hideForYou() {
        return Utils.getBooleanPerf(Settings.TIMELINE_HIDE_FORYOU)?34:17;
    }

    public static boolean hideFAB() {
        return Utils.getBooleanPerf(Settings.MISC_HIDE_FAB);
    }

    public static boolean hideCommNotes() {
        return Utils.getBooleanPerf(Settings.MISC_HIDE_COMM_NOTES);
    }

    public static boolean hideViewCount() {
        return !Utils.getBooleanPerf(Settings.MISC_HIDE_VIEW_COUNT);
    }

    public static boolean hidePromotedTrend(Object data) {
        if(data!=null && Utils.getBooleanPerf(Settings.ADS_HIDE_PROMOTED_TRENDS)){
            return true;
        }
        return false;
    }
    public static boolean hideAds() {
        return Utils.getBooleanPerf(Settings.ADS_HIDE_PROMOTED_POSTS);
    }
    public static boolean hideGoogleAds() {
        return Utils.getBooleanPerf(Settings.ADS_HIDE_GOOGLE_ADS);
    }
    public static boolean hideWTF() {
        return Utils.getBooleanPerf(Settings.ADS_HIDE_WHO_TO_FOLLOW);
    }
    public static boolean hideCTS() {
        return Utils.getBooleanPerf(Settings.ADS_HIDE_CREATORS_TO_SUB);
    }
    public static boolean hideCTJ() {
        return Utils.getBooleanPerf(Settings.ADS_HIDE_COMM_TO_JOIN);
    }
    public static boolean hideRBMK() {
        return Utils.getBooleanPerf(Settings.ADS_HIDE_REVISIT_BMK);
    }
    public static boolean hideRPinnedPosts() {
        return Utils.getBooleanPerf(Settings.ADS_HIDE_REVISIT_PINNED_POSTS);
    }
    public static boolean hideDetailedPosts() {
        return Utils.getBooleanPerf(Settings.ADS_HIDE_DETAILED_POSTS);
    }
    public static boolean enableReaderMode() {
        return Utils.getBooleanPerf(Settings.PREMIUM_READER_MODE);
    }
    public static boolean enableUndoPosts() {
        return Utils.getBooleanPerf(Settings.PREMIUM_UNDO_POSTS);
    }

    //end
}