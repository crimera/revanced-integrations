package app.revanced.integrations.twitter.settings;

import app.revanced.integrations.shared.settings.BaseSettings;
import app.revanced.integrations.shared.settings.BooleanSetting;
import app.revanced.integrations.shared.settings.StringSetting;

public class Settings extends BaseSettings {
    public static final StringSetting VID_PUBLIC_FOLDER = new StringSetting("vid_public_folder", "Movies");
    public static final StringSetting VID_SUBFOLDER = new StringSetting("vid_subfolder", "Twitter");
    public static final BooleanSetting MISC_FONT = new BooleanSetting("misc_font", false);
}
