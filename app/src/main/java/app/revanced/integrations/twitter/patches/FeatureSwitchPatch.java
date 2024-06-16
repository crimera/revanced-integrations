package app.revanced.integrations.twitter.patches;

import java.util.*;

import android.util.Log;
import app.revanced.integrations.twitter.Pref;
import app.revanced.integrations.twitter.Utils;
import app.revanced.integrations.twitter.settings.Settings;

public class FeatureSwitchPatch {
    private static HashMap<String, Object> FLAGS = new HashMap<>();

    private static void addFlag(String flag, Object val) {
        FLAGS.put(flag, val);
    }

    private static void fabMenu() {
        addFlag("android_compose_fab_menu_enabled", Pref.hideFABBtn());
    }

    private static void chirpFont() {
        addFlag("af_ui_chirp_enabled", Pref.isChirpFontEnabled());
    }

    private static void viewCount() {
        addFlag("view_counts_public_visibility_enabled", Pref.hideViewCount());
    }

    private static void bookmarkInTimeline() {
        addFlag("bookmarks_in_timelines_enabled", Pref.hideInlineBookmark());
    }

    private static void navbarFix() {
        addFlag("subscriptions_feature_1008", true);
    }

    private static void immersivePlayer() {
        addFlag("explore_relaunch_enable_immersive_player_across_twitter", Pref.hideImmersivePlayer());
    }

    public static Object flagInfo(String flag, Object def) {
//        Log.d("BRUH", "name: "+flag+" value: "+def.toString()+" type: "+def.getClass().getName());
        try {
            if (FLAGS.containsKey(flag)) {
                Object val = FLAGS.get(flag);
//                Log.d("BRUH", "name: "+flag+" value: "+val+" type: "+val.getClass().getName());
                return val;
            }
        } catch (Exception ex) {

        }
        return def;
    }


    public static void load() {
        String flags = Utils.getStringPref(Settings.MISC_FEATURE_FLAGS);
        if (!flags.isEmpty()) {
            for (String flag : flags.split(",")) {
                String[] item = flag.split(":");
                addFlag(item[0], Boolean.valueOf(item[1]));
            }
        }

        addFlag("timeline_auto_refresh_on_foreground_timeout_millis", 99999.0);
        addFlag("recent_search_limit_count", 10);
        addFlag("home_timeline_start_at_top_min_background_minutes", 99999.0);
        addFlag("home_timeline_start_at_top_warm_start_min_background_minutes", 99999.0);
        addFlag("home_timeline_start_at_top_timeout_length", 99999.0);
    }
}