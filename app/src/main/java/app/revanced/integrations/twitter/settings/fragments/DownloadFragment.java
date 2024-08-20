package app.revanced.integrations.twitter.settings.fragments;

import app.revanced.integrations.shared.StringRef;
import android.content.Context;
import android.os.Bundle;
import android.preference.*;
import androidx.annotation.Nullable;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.twitter.settings.widgets.Helper;
import app.revanced.integrations.twitter.settings.ScreenBuilder;
import app.revanced.integrations.twitter.settings.Settings;
import app.revanced.integrations.twitter.settings.ActivityHook;
import app.revanced.integrations.twitter.settings.SettingsStatus;

@SuppressWarnings("deprecation")
public class DownloadFragment extends PreferenceFragment {
    private Context context;

    @Override
    public void onResume() {
        super.onResume();
        ActivityHook.toolbar.setTitle(StringRef.str("piko_title_download"));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        PreferenceManager preferenceManager = getPreferenceManager();
        PreferenceScreen screen = preferenceManager.createPreferenceScreen(context);
        preferenceManager.setSharedPreferencesName(Settings.SHARED_PREF_NAME);

        Helper helper = new Helper(context);
        ScreenBuilder screenBuilder = new ScreenBuilder(context, screen, helper);

        screenBuilder.buildDownloadSection(false);

        setPreferenceScreen(screen);

    }

}