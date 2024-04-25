package app.revanced.integrations.twitter.settings;

import app.revanced.integrations.shared.settings.BaseSettings;
import app.revanced.integrations.shared.settings.BooleanSetting;
import app.revanced.integrations.shared.settings.StringSetting;

public class Settings extends BaseSettings {
    public static final String SHARED_PREF_NAME = "piko_settings";

    public static final StringSetting VID_PUBLIC_FOLDER = new StringSetting("vid_public_folder", "Movies");
    public static final StringSetting VID_SUBFOLDER = new StringSetting("vid_subfolder", "Twitter");
    public static final StringSetting CUSTOM_SHARING_DOMAIN = new StringSetting("misc_custom_sharing_domain", "twitter");
    public static final BooleanSetting MISC_FONT = new BooleanSetting("misc_font", false);
    public static final BooleanSetting MISC_HIDE_FAB = new BooleanSetting("misc_hide_fab", false);
    public static final BooleanSetting MISC_HIDE_FAB_BTN = new BooleanSetting("misc_hide_fab_btns", false);
    public static final BooleanSetting MISC_HIDE_RECOMMENDED_USERS = new BooleanSetting("misc_hide_recommended_users", true);
    public static final BooleanSetting MISC_HIDE_COMM_NOTES = new BooleanSetting("misc_hide_comm_notes", false);
    public static final BooleanSetting MISC_HIDE_VIEW_COUNT = new BooleanSetting("misc_hide_view_count", true);
    public static final StringSetting MISC_FEATURE_FLAGS = new StringSetting("misc_feature_flags", "");

    public static final BooleanSetting ADS_HIDE_PROMOTED_TRENDS = new BooleanSetting("ads_hide_promoted_trends", true);
    public static final BooleanSetting ADS_HIDE_PROMOTED_POSTS = new BooleanSetting("ads_hide_promoted_posts", true);
    public static final BooleanSetting ADS_HIDE_GOOGLE_ADS = new BooleanSetting("ads_hide_google_ads", true);
    public static final BooleanSetting ADS_HIDE_WHO_TO_FOLLOW = new BooleanSetting("ads_hide_who_to_follow", true);
    public static final BooleanSetting ADS_HIDE_CREATORS_TO_SUB = new BooleanSetting("ads_hide_creators_to_sub", true);
    public static final BooleanSetting ADS_HIDE_COMM_TO_JOIN = new BooleanSetting("ads_hide_comm_to_join", true);
    public static final BooleanSetting ADS_HIDE_REVISIT_BMK = new BooleanSetting("ads_hide_revisit_bookmark", true);
    public static final BooleanSetting ADS_HIDE_REVISIT_PINNED_POSTS = new BooleanSetting("ads_hide_revisit_pinned_posts", true);
    public static final BooleanSetting ADS_HIDE_DETAILED_POSTS = new BooleanSetting("ads_hide_detailed_posts", true);

    public static final BooleanSetting TIMELINE_HIDE_LIVETHREADS = new BooleanSetting("timeline_hide_livethreads", true);
    public static final BooleanSetting TIMELINE_HIDE_BANNER = new BooleanSetting("timeline_hide_banner", true);
    public static final BooleanSetting TIMELINE_HIDE_FORYOU = new BooleanSetting("timeline_hide_foryou", false);
    public static final BooleanSetting TIMELINE_HIDE_BMK_ICON = new BooleanSetting("timeline_hide_bookmark_icon", false);
    public static final BooleanSetting TIMELINE_SHOW_POLL_RESULTS = new BooleanSetting("timeline_show_poll_results", false);
    public static final BooleanSetting TIMELINE_HIDE_IMMERSIVE_PLAYER = new BooleanSetting("timeline_hide_immersive_player", false);

    public static final BooleanSetting PREMIUM_READER_MODE = new BooleanSetting("premium_reader_mode", false);
    public static final BooleanSetting PREMIUM_UNDO_POSTS = new BooleanSetting("premium_undo_posts", false);
    public static final StringSetting PREMIUM_ICONS = new StringSetting("premium_app_icon_n_nav_icon", "");

    public static final StringSetting CUSTOM_PROFILE_TABS = new StringSetting("customisation_profile_tabs", "");

    public static final StringSetting EXPORT_PREF = new StringSetting("export_pref", "");
    public static final StringSetting EXPORT_FLAGS = new StringSetting("export_flags", "");
    public static final StringSetting IMPORT_PREF = new StringSetting("import_pref", "");
    public static final StringSetting IMPORT_FLAGS = new StringSetting("import_flags", "");
}
