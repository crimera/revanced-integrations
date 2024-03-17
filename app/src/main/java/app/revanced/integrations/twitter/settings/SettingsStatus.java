package app.revanced.integrations.twitter.settings;

public class SettingsStatus {
    public static boolean changeDownloadEnabled = false;
    public static boolean enableFontMod = false;
    public static boolean enableDownloadPostMod = false;
    public static boolean enableUndoPostMod = false;
    public static boolean enableReaderModeMod = false;


    public static void enableDownloadFolder() { changeDownloadEnabled = true; }
    public static void enableFont() { enableFontMod = true; }

    //premium settings
    public static void enableDownloadPost() { enableDownloadPostMod = true; }
    public static void enableUndoPost() { enableUndoPostMod = true; }
    public static void enableReaderMode() { enableReaderModeMod = true; }
    public static boolean enablePremiumScreen(){return (enableDownloadPostMod || enableUndoPostMod || enableReaderModeMod);}

    public static void load() {}
}
