package app.revanced.integrations.twitter.settings;

import android.text.*;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.preference.*;
import androidx.annotation.Nullable;
import app.revanced.integrations.shared.Utils;
import com.twitter.ui.widget.LegacyTwitterPreferenceCategory;
import app.revanced.integrations.BuildConfig;
import java.util.*;

@SuppressWarnings("deprecation")
public class SettingsAboutFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private Context context;

    @Override
    public void onResume() {
        super.onResume();
        ActivityHook.toolbar.setTitle(strRes("piko_pref_patch_info"));
    }

    @Override
    public void onCreate(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        PreferenceManager preferenceManager = getPreferenceManager();
        PreferenceScreen screen = preferenceManager.createPreferenceScreen(context);
        LegacyTwitterPreferenceCategory verPref = preferenceCategory(strRes("piko_pref_version_info"), screen);
        verPref.addPreference(
                buttonPreference(
                        strRes("piko_pref_app_version"),
                        Utils.getVersionName(),
                        strRes("piko_pref_app_version")
                )
        );
        verPref.addPreference(
                buttonPreference(
                        strRes("piko_title_piko_patches"),
                        Utils.getPatchesReleaseVersion(),
                        strRes("piko_title_piko_patches")
                )
        );
        verPref.addPreference(
                buttonPreference(
                        strRes("piko_title_piko_integrations"),
                        BuildConfig.VERSION_NAME,
                        strRes("piko_title_piko_integrations")
                )
        );

        LegacyTwitterPreferenceCategory patPref = preferenceCategory(strRes("piko_pref_patches"), screen);


        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_video_download"),
                        SettingsStatus.enableVidDownload,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_reader_mode"),
                        SettingsStatus.enableReaderMode,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_undo_posts"),
                        SettingsStatus.enableUndoPosts,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_icon_n_navbar_btn"),
                        SettingsStatus.enableAppIconNNavIcon,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_download"),
                        SettingsStatus.changeDownloadEnabled,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_download_media_link_handle"),
                        SettingsStatus.mediaLinkHandle,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_promoted_posts"),
                        SettingsStatus.hideAds,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_g_ads"),
                        SettingsStatus.hideGAds,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_wtf_section"),
                        SettingsStatus.hideWTF,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_cts_section"),
                        SettingsStatus.hideCTS,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_ctj_section"),
                        SettingsStatus.hideCTJ,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_ryb_section"),
                        SettingsStatus.hideRBMK,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_pinned_posts_section"),
                        SettingsStatus.hideRPinnedPosts,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_detailed_posts"),
                        SettingsStatus.hideDetailedPosts,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_trends"),
                        SettingsStatus.hidePromotedTrend,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_chirp_font"),
                        SettingsStatus.enableFontMod,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_fab"),
                        SettingsStatus.hideFAB,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_fab_menu"),
                        SettingsStatus.hideFABBtns,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_show_sensitive_media"),
                        SettingsStatus.showSensitiveMedia,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_selectable_text"),
                        SettingsStatus.selectableText,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_rec_users"),
                        SettingsStatus.hideRecommendedUsers,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_browser_chooser"),
                        SettingsStatus.browserChooserEnabled,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_custom_share_domain"),
                        SettingsStatus.customSharingDomainEnabled,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_feature_flags"),
                        SettingsStatus.featureFlagsEnabled,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_customisation_profiletabs"),
                        SettingsStatus.profileTabCustomisation,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_customisation_timelinetabs"),
                        SettingsStatus.timelineTabCustomisation,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_live_threads"),
                        SettingsStatus.hideLiveThreads,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_banner"),
                        SettingsStatus.hideBanner,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_bmk_timeline"),
                        SettingsStatus.hideInlineBmk,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_show_poll_result"),
                        SettingsStatus.showPollResultsEnabled,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_comm_notes"),
                        SettingsStatus.hideCommunityNote,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_force_translate"),
                        SettingsStatus.forceTranslate,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_quick_promote"),
                        SettingsStatus.hidePromoteButton,
                        strRes("piko_pref_patches")
                )
        );

        patPref.addPreference(
                buttonPreference2(
                        strRes("piko_pref_hide_immersive_player"),
                        SettingsStatus.hideImmersivePlayer,
                        strRes("piko_pref_patches")
                )
        );

        setPreferenceScreen(screen);

    }

    private LegacyTwitterPreferenceCategory preferenceCategory(String title, PreferenceScreen screen) {
        LegacyTwitterPreferenceCategory preferenceCategory = new LegacyTwitterPreferenceCategory(context);
        preferenceCategory.setTitle(title);
        screen.addPreference(preferenceCategory);
        return preferenceCategory;
    }

    private Preference buttonPreference(String title, String summary, String key) {
        Preference preference = new Preference(context);
        preference.setKey(key);
        preference.setTitle(title);
        preference.setSummary(summary);
        preference.setOnPreferenceClickListener(this);
        return preference;
    }

    private Preference buttonPreference2(String title, Boolean inc, String key) {
        String summary = inc ? strRes("piko_pref_included"):strRes("piko_pref_excluded");
        String colorHex = inc ? "#008FC4":"#DE0025";
        Preference preference = new Preference(context);
        preference.setKey(key);
        preference.setTitle(title);
        Spannable summarySpan = new SpannableString(summary);
        summarySpan.setSpan(new ForegroundColorSpan(Color.parseColor(colorHex)), 0, summary.length(), 0);
        preference.setSummary(summarySpan);
        preference.setOnPreferenceClickListener(this);
        return preference;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if ( (key.equals(strRes("piko_pref_app_version"))) || (key.equals(strRes("piko_title_piko_patches"))) || (key.equals(strRes("piko_title_piko_integrations"))) ) {
            String summary = preference.getSummary().toString();
            Utils.setClipboard(summary);
            Utils.showToastShort(strRes("copied_to_clipboard")+": "+ summary);
        }

        return true;
    }

    private static String strRes(String tag) {
        return app.revanced.integrations.twitter.Utils.strRes(tag);
    }

}