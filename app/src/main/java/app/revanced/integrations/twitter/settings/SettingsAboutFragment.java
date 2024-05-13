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

        Map<String,Boolean> flags = new HashMap();
        flags.put("piko_pref_video_download",SettingsStatus.enableVidDownload);
        flags.put("piko_pref_reader_mode",SettingsStatus.enableReaderMode);
        flags.put("piko_pref_icon_n_navbar_btn",SettingsStatus.enableAppIconNNavIcon);
        flags.put("piko_pref_download",SettingsStatus.changeDownloadEnabled);
        flags.put("piko_pref_download_media_link_handle",SettingsStatus.mediaLinkHandle);
        flags.put("piko_pref_hide_promoted_posts",SettingsStatus.hideAds);
        flags.put("piko_pref_hide_g_ads",SettingsStatus.hideGAds);
        flags.put("piko_pref_wtf_section",SettingsStatus.hideWTF);
        flags.put("piko_pref_cts_section",SettingsStatus.hideCTS);
        flags.put("piko_pref_ctj_section",SettingsStatus.hideCTJ);
        flags.put("piko_pref_ryb_section",SettingsStatus.hideRBMK);
        flags.put("piko_pref_pinned_posts_section",SettingsStatus.hideRPinnedPosts);
        flags.put("piko_pref_hide_detailed_posts",SettingsStatus.hideDetailedPosts);
        flags.put("piko_pref_hide_trends",SettingsStatus.hidePromotedTrend);
        flags.put("piko_pref_chirp_font",SettingsStatus.enableFontMod);
        flags.put("piko_pref_hide_fab",SettingsStatus.hideFAB);
        flags.put("piko_pref_hide_fab_menu",SettingsStatus.hideFABBtns);
        flags.put("piko_pref_show_sensitive_media",SettingsStatus.showSensitiveMedia);
        flags.put("piko_pref_selectable_text",SettingsStatus.selectableText);
        flags.put("piko_pref_rec_users",SettingsStatus.hideRecommendedUsers);
        flags.put("piko_pref_browser_chooser",SettingsStatus.browserChooserEnabled);
        flags.put("piko_pref_custom_share_domain",SettingsStatus.customSharingDomainEnabled);
        flags.put("piko_pref_feature_flags",SettingsStatus.featureFlagsEnabled);
        flags.put("piko_pref_customisation_profiletabs",SettingsStatus.profileTabCustomisation);
        flags.put("piko_pref_customisation_timelinetabs",SettingsStatus.timelineTabCustomisation);
        flags.put("piko_pref_customisation_navbartabs",SettingsStatus.navBarCustomisation);
        flags.put("piko_pref_customisation_sidebartabs",SettingsStatus.sideBarCustomisation);
        flags.put("piko_pref_disable_auto_timeline_scroll",SettingsStatus.disableAutoTimelineScroll);
        flags.put("piko_pref_hide_live_threads",SettingsStatus.hideLiveThreads);
        flags.put("piko_pref_hide_banner",SettingsStatus.hideBanner);
        flags.put("piko_pref_hide_bmk_timeline",SettingsStatus.hideInlineBmk);
        flags.put("piko_pref_show_poll_result",SettingsStatus.showPollResultsEnabled);
        flags.put("piko_pref_comm_notes",SettingsStatus.hideCommunityNote);
        flags.put("piko_pref_force_translate",SettingsStatus.forceTranslate);
        flags.put("piko_pref_hide_quick_promote",SettingsStatus.hidePromoteButton);
        flags.put("piko_pref_hide_immersive_player",SettingsStatus.hideImmersivePlayer);
        flags.put("piko_pref_clear_tracking_params",SettingsStatus.cleartrackingparams);
        flags.put("piko_pref_unshorten_link",SettingsStatus.unshortenlink);

        for (Map.Entry<String, Boolean> entry : flags.entrySet()) {
            String resName = entry.getKey();
            boolean sts = (boolean)entry.getValue();

                patPref.addPreference(
                    buttonPreference2(
                            strRes(resName),
                            sts,
                            strRes("piko_pref_patches")
                    )
            );
        }

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