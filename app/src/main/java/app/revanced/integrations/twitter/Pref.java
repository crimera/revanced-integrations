package app.revanced.integrations.twitter;

import android.util.Log;
import app.revanced.integrations.twitter.settings.Settings;

import java.util.*;

@SuppressWarnings("unused")
public class Pref {

    public static boolean isChirpFontEnabled() {
        return Utils.getBooleanPerf(Settings.MISC_FONT);
    }

    public static String getPublicFolder() {
        return Utils.getStringPref(Settings.VID_PUBLIC_FOLDER);
    }

    public static String getVideoFolder(String filename) {
        return Utils.getStringPref(Settings.VID_SUBFOLDER) + "/" + filename;
    }

    public static int vidMediaHandle() {
        String val = Utils.getStringPref(Settings.VID_MEDIA_HANDLE);
        if(val.equals("download_media")){
            return 1;
        }
        if (val.equals("copy_media_link")){
            return 2;
        }
        return 3;
    }

    public static String getSharingLink(String link) {
        String domain = Utils.getStringPref(Settings.CUSTOM_SHARING_DOMAIN);
        return link.replaceFirst("x|twitter", domain);
    }

    public static ArrayList hideRecommendedUsers(ArrayList users) {
        if (Utils.getBooleanPerf(Settings.MISC_HIDE_RECOMMENDED_USERS)) {
            return null;
        }
        return users;
    }

    public static ArrayList liveThread(ArrayList fleets) {
        if (Utils.getBooleanPerf(Settings.TIMELINE_HIDE_LIVETHREADS)) {
            return null;
        }
        return fleets;
    }

    public static Map polls(Map map) {
        if (Utils.getBooleanPerf(Settings.TIMELINE_SHOW_POLL_RESULTS)) {
            try {
                if (map.containsKey("counts_are_final")) {
                    if (map.get("counts_are_final").toString().equals("true")) {
                        return map;
                    }
                }

                HashMap newMap = new HashMap();

                ArrayList<String> labels = new ArrayList(Arrays.asList("choice1_label", "choice2_label", "choice3_label", "choice4_label"));
                String[] counts = {"choice1_count", "choice2_count", "choice3_count", "choice4_count"};

                // get sum
                int totalVotes = 0;
                for (String count : counts) {
                    if (!map.containsKey(count)) {
                        break;
                    }

                    totalVotes += Integer.parseInt(map.get(count).toString());
                }

                for (Object key : map.keySet()) {
                    Object idk = map.get(key);

                    if (labels.contains(key.toString())) {
                        String countLabel = counts[labels.indexOf(key.toString())];

                        int count = 0;
                        if (map.get(countLabel) != null) {
                            count = Integer.parseInt(map.get(countLabel).toString());
                        }

                        int percentage = Math.round(count * 100.0f / totalVotes);

                        newMap.put(
                                key,
                                idk.getClass().getConstructor(Object.class, String.class).newInstance(
                                        idk + " - " + percentage + "%",
                                        null
                                )
                        );

                        continue;
                    }

                    newMap.put(key, idk);
                }

                return newMap;
            } catch (Exception e) {
                Log.d("POLL_ERROR", map.toString());
            }
        }
        return map;
    }

    public static boolean hideBanner() {
        return !Utils.getBooleanPerf(Settings.TIMELINE_HIDE_BANNER);
    }

    public static int timelineTab() {
        String val = Utils.getStringPref(Settings.CUSTOM_TIMELINE_TABS);
        if(val.equals("hide_forYou")){
            return 1;
        }
        if (val.equals("hide_following")){
            return 2;
        }
        return 0;
    }

    public static boolean enableForceTranslate() {
        return Utils.getBooleanPerf(Settings.TIMELINE_HIDE_FORCE_TRANSLATE);
    }
    public static boolean hidePromoteBtn() {
        return Utils.getBooleanPerf(Settings.TIMELINE_HIDE_PROMOTE_BUTTON);
    }
    public static boolean hideFAB() {
        return Utils.getBooleanPerf(Settings.MISC_HIDE_FAB);
    }

    public static boolean hideFABBtn() {
        return !Utils.getBooleanPerf(Settings.MISC_HIDE_FAB_BTN);
    }

    public static boolean hideCommNotes() {
        return Utils.getBooleanPerf(Settings.MISC_HIDE_COMM_NOTES);
    }

    public static boolean hideViewCount() {
        return !Utils.getBooleanPerf(Settings.MISC_HIDE_VIEW_COUNT);
    }

    public static boolean hideInlineBookmark() {
        return !Utils.getBooleanPerf(Settings.TIMELINE_HIDE_BMK_ICON);
    }

    public static boolean hideImmersivePlayer() {
        return !Utils.getBooleanPerf(Settings.TIMELINE_HIDE_IMMERSIVE_PLAYER);
    }

    public static boolean hidePromotedTrend(Object data) {
        if (data != null && Utils.getBooleanPerf(Settings.ADS_HIDE_PROMOTED_TRENDS)) {
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

    public static ArrayList customProfileTabs() {
        ArrayList<String> arrayList = new ArrayList<String>();
        try{
            String key =Settings.CUSTOM_PROFILE_TABS.key;
            Set<String> ch = Utils.getSetPerf(key,null);
            if(!ch.isEmpty()) {
                arrayList = new ArrayList<String>(ch);
            }
        }catch (Exception e){}
        return arrayList;
    }

    //end
}