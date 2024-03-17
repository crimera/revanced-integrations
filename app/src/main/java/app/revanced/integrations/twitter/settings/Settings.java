package app.revanced.integrations.twitter.settings;

import app.revanced.integrations.shared.settings.BaseSettings;
import app.revanced.integrations.shared.settings.BooleanSetting;
import app.revanced.integrations.shared.settings.StringSetting;

public class Settings extends BaseSettings {
    public static final String CATEGORY_DOWNLOAD = "Download";
    public static final String CATEGORY_MISC = "Misc";
    public static final String CATEGORY_PREMIUM = "Premium Features";

    public static final StringSetting VID_PUBLIC_FOLDER = new StringSetting("vid_public_folder", "Movies");
    public static final StringSetting VID_SUBFOLDER = new StringSetting("vid_subfolder", "Twitter");
    public static final BooleanSetting MISC_FONT = new BooleanSetting("misc_font", false);
    public static final BooleanSetting DOWNLOAD_POSTS = new BooleanSetting("mod_download_video", true);
    public static final BooleanSetting UNDO_POSTS = new BooleanSetting("mod_undo_posts", false);
    public static final BooleanSetting READER_MODE = new BooleanSetting("mod_reader_mode", false);
}
