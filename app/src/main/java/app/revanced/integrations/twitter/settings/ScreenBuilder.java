package app.revanced.integrations.twitter.settings;

import app.revanced.integrations.shared.StringRef;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import androidx.annotation.Nullable;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.shared.settings.BooleanSetting;
import app.revanced.integrations.shared.settings.StringSetting;
import com.twitter.ui.widget.LegacyTwitterPreferenceCategory;
import android.view.View;
import app.revanced.integrations.twitter.settings.widget.Helper;

public class ScreenBuilder {
    private Context context;
    private PreferenceScreen screen;
    private Helper helper;

    public ScreenBuilder(Context context,PreferenceScreen screen,Helper helper){
        this.context = context;
        this.screen = screen;
        this.helper = helper;
    }

    public void buildPremiumSection(){
        if (SettingsStatus.enablePremiumSection()) {
            LegacyTwitterPreferenceCategory premiumPrefs = preferenceCategory(strRes("piko_title_premium"));
            if (SettingsStatus.enableReaderMode) {
                premiumPrefs.addPreference(
                        helper.switchPreference(
                                strEnableRes("piko_pref_reader_mode"),
                                strRes("piko_pref_reader_mode_desc"),
                                Settings.PREMIUM_READER_MODE
                        )
                );
            }
            if (SettingsStatus.enableUndoPosts) {
                premiumPrefs.addPreference(
                        helper.switchPreference(
                                strEnableRes("piko_pref_undo_posts"),
                                strRes("piko_pref_undo_posts_desc"),
                                Settings.PREMIUM_UNDO_POSTS
                        )
                );

                if (SettingsStatus.enableForcePip) {
                    premiumPrefs.addPreference(
                            helper.switchPreference(
                                    strEnableRes("piko_pref_enable_force_pip"),
                                    strRes("piko_pref_enable_force_pip_desc"),
                                    Settings.PREMIUM_ENABLE_FORCE_PIP
                            )
                    );
                }

                premiumPrefs.addPreference(
                        helper.buttonPreference(
                                strRes("piko_pref_undo_posts_btn"),
                                "",
                                Settings.PREMIUM_UNDO_POSTS.key
                        )
                );
            }

            if (SettingsStatus.customAppIcon || SettingsStatus.navBarCustomisation) {
                premiumPrefs.addPreference(
                        helper.buttonPreference(
                                strRes("piko_pref_icon_n_navbar_btn"),
                                "",
                                Settings.PREMIUM_ICONS.key
                        )
                );
            }
        }
    }

    public void buildDownloadSection(){
        if (SettingsStatus.enableDownloadSection()) {
            LegacyTwitterPreferenceCategory downloadPrefs = preferenceCategory(strRes("piko_title_download"));
            if (SettingsStatus.changeDownloadEnabled || SettingsStatus.nativeDownloader) {
                downloadPrefs.addPreference(helper.listPreference(
                        strRes("piko_pref_download_path"),
                        strRes("piko_pref_download_path_desc"),
                        Settings.VID_PUBLIC_FOLDER
                ));
                downloadPrefs.addPreference(helper.editTextPreference(
                        strRes("piko_pref_download_folder"),
                        strRes("piko_pref_download_folder_desc"),
                        Settings.VID_SUBFOLDER
                ));
            }
            if (SettingsStatus.mediaLinkHandle) {
                downloadPrefs.addPreference(
                        helper.listPreference(
                                strRes("piko_pref_download_media_link_handle"),
                                "",
                                Settings.VID_MEDIA_HANDLE
                        )
                );
            }
        }
    }

    public void buildAdsSection(){
        LegacyTwitterPreferenceCategory adsPrefs = preferenceCategory(strRes("piko_title_ads"));

        if (SettingsStatus.hideAds) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_promoted_posts"),
                            "",
                            Settings.ADS_HIDE_PROMOTED_POSTS
                    )
            );
        }

        if (SettingsStatus.hideGAds) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_g_ads"),
                            "",
                            Settings.ADS_HIDE_GOOGLE_ADS
                    )
            );
        }
        if (SettingsStatus.hideMainEvent) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_main_event"),
                            "",
                            Settings.ADS_HIDE_MAIN_EVENT
                    )
            );
        }
        if (SettingsStatus.hideSuperheroEvent) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_superhero_event"),
                            "",
                            Settings.ADS_HIDE_SUPERHERO_EVENT
                    )
            );
        }
        if (SettingsStatus.hideVideosForYou) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_videos_for_you"),
                            "",
                            Settings.ADS_HIDE_VIDEOS_FOR_YOU
                    )
            );
        }
        if (SettingsStatus.hideWTF) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_wtf_section"),
                            "",
                            Settings.ADS_HIDE_WHO_TO_FOLLOW
                    )
            );
        }
        if (SettingsStatus.hideCTS) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_cts_section"),
                            "",
                            Settings.ADS_HIDE_CREATORS_TO_SUB
                    )
            );
        }

        if (SettingsStatus.hideCTJ) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_ctj_section"),
                            "",
                            Settings.ADS_HIDE_COMM_TO_JOIN
                    )
            );
        }

        if (SettingsStatus.hideRBMK) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_ryb_section"),
                            "",
                            Settings.ADS_HIDE_REVISIT_BMK
                    )
            );
        }

        if (SettingsStatus.hideRPinnedPosts) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_pinned_posts_section"),
                            "",
                            Settings.ADS_HIDE_REVISIT_PINNED_POSTS
                    )
            );
        }

        if (SettingsStatus.hideDetailedPosts) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_detailed_posts"),
                            "",
                            Settings.ADS_HIDE_DETAILED_POSTS
                    )
            );
        }

        if (SettingsStatus.hidePromotedTrend) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_trends"),
                            "",
                            Settings.ADS_HIDE_PROMOTED_TRENDS
                    )
            );
        }

        if (SettingsStatus.hidePremiumPrompt) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_premium_prompt"),
                            "",
                            Settings.ADS_HIDE_PREMIUM_PROMPT
                    )
            );
        }

        if (SettingsStatus.removePremiumUpsell) {
            adsPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_premium_upsell"),
                            strRes("piko_pref_hide_premium_upsell_desc"),
                            Settings.ADS_REMOVE_PREMIUM_UPSELL
                    )
            );
        }

        if (SettingsStatus.deleteFromDb) {
            adsPrefs.addPreference(
                    helper.buttonPreference(
                            strRes("piko_pref_del_from_db"),
                            "",
                            Settings.ADS_DEL_FROM_DB.key
                    )
            );
        }
    }

    public void buildMiscSection(){
        LegacyTwitterPreferenceCategory miscPrefs = preferenceCategory(strRes("piko_title_misc"));
        if (SettingsStatus.enableFontMod) {
            miscPrefs.addPreference(
                    helper.switchPreference(
                            strEnableRes("piko_pref_chirp_font"),
                            "",
                            Settings.MISC_FONT
                    )
            );
        }
        if (SettingsStatus.hideFAB) {
            miscPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_fab"),
                            "",
                            Settings.MISC_HIDE_FAB
                    )
            );
        }
        if (SettingsStatus.hideFABBtns) {
            miscPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_fab_menu"),
                            "",
                            Settings.MISC_HIDE_FAB_BTN
                    )
            );
        }

        if (SettingsStatus.hideRecommendedUsers) {
            miscPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_rec_users"),
                            "",
                            Settings.MISC_HIDE_RECOMMENDED_USERS
                    )
            );
        }

        if (SettingsStatus.hideViewCount) {
            miscPrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_view_count"),
                            "",
                            Settings.MISC_HIDE_VIEW_COUNT
                    )
            );
        }

        if (SettingsStatus.browserChooserEnabled) {
            miscPrefs.addPreference(
                    helper.switchPreference(
                            strRes("piko_pref_browser_chooser"),
                            strRes("piko_pref_browser_chooser_desc"),
                            Settings.MISC_BROWSER_CHOOSER
                    )
            );
        }

        if (SettingsStatus.roundOffNumbers) {
            miscPrefs.addPreference(
                    helper.switchPreference(
                            strRes("piko_pref_round_off_numbers"),
                            strRes("piko_pref_round_off_numbers_desc"),
                            Settings.MISC_ROUND_OFF_NUMBERS
                    )
            );
        }

        if (SettingsStatus.enableDebugMenu) {
            miscPrefs.addPreference(
                    helper.switchPreference(
                            strEnableRes("piko_pref_debug_menu"),
                            "",
                            Settings.MISC_DEBUG_MENU
                    )
            );
        }

        miscPrefs.addPreference(
                helper.switchPreference(
                        strRes("piko_pref_quick_settings"),
                        strRes("piko_pref_quick_settings_summary"),
                        Settings.MISC_QUICK_SETTINGS_BUTTON
                )
        );

        if (SettingsStatus.customSharingDomainEnabled) {
            miscPrefs.addPreference(
                    helper.editTextPreference(
                            strRes("piko_pref_custom_share_domain"),
                            strRes("piko_pref_custom_share_domain_desc"),
                            Settings.CUSTOM_SHARING_DOMAIN
                    )
            );
        }

        if (SettingsStatus.featureFlagsEnabled) {
            miscPrefs.addPreference(
                    helper.buttonPreference(
                            strRes("piko_pref_feature_flags"),
                            "",
                            Settings.MISC_FEATURE_FLAGS.key
                    )
            );
        }

    }

    public void buildCustomiseSection(){
        LegacyTwitterPreferenceCategory customisationPrefs = preferenceCategory(strRes("piko_title_customisation"));
        if (SettingsStatus.profileTabCustomisation) {
            customisationPrefs.addPreference(
                    helper.multiSelectListPref(
                            strRes("piko_pref_customisation_profiletabs"),
                            strRes("piko_pref_app_restart_rec"),
                            Settings.CUSTOM_PROFILE_TABS
                    )
            );
        }
        if (SettingsStatus.timelineTabCustomisation) {
            customisationPrefs.addPreference(
                    helper.listPreference(
                            strRes("piko_pref_customisation_timelinetabs"),
                            strRes("piko_pref_app_restart_rec"),
                            Settings.CUSTOM_TIMELINE_TABS
                    )
            );
        }
        if (SettingsStatus.sideBarCustomisation) {
            customisationPrefs.addPreference(
                    helper.multiSelectListPref(
                            strRes("piko_pref_customisation_sidebartabs"),
                            strRes("piko_pref_app_restart_rec"),
                            Settings.CUSTOM_SIDEBAR_TABS
                    )
            );
        }

        if (SettingsStatus.navBarCustomisation) {
            customisationPrefs.addPreference(
                    helper.multiSelectListPref(
                            strRes("piko_pref_customisation_navbartabs"),
                            strRes("piko_pref_app_restart_rec"),
                            Settings.CUSTOM_NAVBAR_TABS
                    )
            );
        }

        if (SettingsStatus.inlineBarCustomisation) {
            customisationPrefs.addPreference(
                    helper.multiSelectListPref(
                            strRes("piko_pref_customisation_inlinetabs"),
                            strRes("piko_pref_app_restart_rec"),
                            Settings.CUSTOM_INLINE_TABS
                    )
            );
        }

        if (SettingsStatus.defaultReplySortFilter) {
            customisationPrefs.addPreference(
                    helper.listPreference(
                            strRes("piko_pref_customisation_reply_sorting"),
                            "",
                            Settings.CUSTOM_DEF_REPLY_SORTING
                    )
            );
        }
    }

    public void buildTimelineSection(){
        LegacyTwitterPreferenceCategory timelinePrefs = preferenceCategory(strRes("piko_title_timeline"));
        if (SettingsStatus.disableAutoTimelineScroll) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRes("piko_pref_disable_auto_timeline_scroll"),
                            "",
                            Settings.TIMELINE_DISABLE_AUTO_SCROLL
                    )
            );
        }
        if (SettingsStatus.hideLiveThreads) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_live_threads"),
                            strRes("piko_pref_hide_live_threads_desc"),
                            Settings.TIMELINE_HIDE_LIVETHREADS
                    )
            );
        }
        if (SettingsStatus.hideBanner) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_banner"),
                            strRemoveRes("piko_pref_hide_banner_desc"),
                            Settings.TIMELINE_HIDE_BANNER
                    )
            );
        }
        if (SettingsStatus.hideInlineBmk) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_bmk_timeline"),
                            "",
                            Settings.TIMELINE_HIDE_BMK_ICON
                    )
            );
        }

        if (SettingsStatus.showPollResultsEnabled) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRes("piko_pref_show_poll_result"),
                            strRes("piko_pref_show_poll_result_desc"),
                            Settings.TIMELINE_SHOW_POLL_RESULTS
                    )
            );
        }

        if (SettingsStatus.unshortenlink) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRes("piko_pref_unshorten_link"),
                            strRes("piko_pref_unshorten_link_desc"),
                            Settings.TIMELINE_UNSHORT_URL
                    )
            );
        }

        if (SettingsStatus.hideCommunityNote) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("community_notes_title"),
                            "",
                            Settings.MISC_HIDE_COMM_NOTES
                    )
            );
        }

        if (SettingsStatus.forceTranslate) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRes("piko_pref_force_translate"),
                            strRes("piko_pref_force_translate_desc"),
                            Settings.TIMELINE_HIDE_FORCE_TRANSLATE
                    )
            );
        }

        if (SettingsStatus.hidePromoteButton) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_quick_promote"),
                            strRes("piko_pref_hide_quick_promote_desc"),
                            Settings.TIMELINE_HIDE_PROMOTE_BUTTON
                    )
            );
        }

        if (SettingsStatus.hideImmersivePlayer) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_immersive_player"),
                            strRes("piko_pref_hide_immersive_player_desc"),
                            Settings.TIMELINE_HIDE_IMMERSIVE_PLAYER
                    )
            );
        }
        if (SettingsStatus.enableVidAutoAdvance) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strEnableRes("piko_pref_enable_vid_auto_advance"),
                            strRes("piko_pref_enable_vid_auto_advance_desc"),
                            Settings.TIMELINE_ENABLE_VID_AUTO_ADVANCE
                    )
            );
        }
        if (SettingsStatus.hideHiddenReplies) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strRemoveRes("piko_pref_hide_hidden_replies"),
                            "",
                            Settings.TIMELINE_HIDE_HIDDEN_REPLIES
                    )
            );
        }
        if (SettingsStatus.enableForceHD) {
            timelinePrefs.addPreference(
                    helper.switchPreference(
                            strEnableRes("piko_pref_force_hd"),
                            strRes("piko_pref_force_hd_desc"),
                            Settings.TIMELINE_ENABLE_VID_FORCE_HD
                    )
            );
        }
    }

    public void buildExportSection(){
        LegacyTwitterPreferenceCategory backupPref = preferenceCategory(strRes("piko_title_backup"));
        backupPref.addPreference(
                helper.buttonPreference(
                        StringRef.str("piko_pref_export",strRes("piko_name"))+" "+strRes("settings_notification_pref_item_title"),
                        "",
                        Settings.EXPORT_PREF.key
                )
        );
        backupPref.addPreference(
                helper.buttonPreference(
                        StringRef.str("piko_pref_export",strRes("piko_title_feature_flags")),
                        "",
                        Settings.EXPORT_FLAGS.key
                )
        );

        backupPref.addPreference(
                helper.buttonPreference(
                        StringRef.str("piko_pref_import",strRes("piko_name"))+" "+strRes("settings_notification_pref_item_title"),
                        strRes("piko_pref_app_restart_rec"),
                        Settings.IMPORT_PREF.key
                )
        );
        backupPref.addPreference(
                helper.buttonPreference(
                        StringRef.str("piko_pref_import",strRes("piko_title_feature_flags")),
                        strRes("piko_pref_app_restart_rec"),
                        Settings.IMPORT_FLAGS.key
                )
        );

        backupPref.addPreference(
                helper.buttonPreference(
                        strRes("delete")+": "+strRes("piko_name")+" "+strRes("settings_notification_pref_item_title"),
                        "",
                        Settings.RESET_PREF.key
                )
        );

        backupPref.addPreference(
                helper.buttonPreference(
                        strRes("delete")+": "+strRes("piko_title_feature_flags"),
                        "",
                        Settings.RESET_FLAGS.key
                )
        );
    }

    public void buildPikoSection(){
        LegacyTwitterPreferenceCategory aboutPref = preferenceCategory(strRes("piko_title_about"));
        aboutPref.addPreference(
                helper.buttonPreference(
                        strRes("piko_pref_patch_info"),
                        "",
                        Settings.PATCH_INFO.key
                )
        );
    }

    public LegacyTwitterPreferenceCategory preferenceCategory(String title) {
        LegacyTwitterPreferenceCategory preferenceCategory = new LegacyTwitterPreferenceCategory(context);
        preferenceCategory.setTitle(title);
        screen.addPreference(preferenceCategory);
        return preferenceCategory;
    }

    public String strRes(String tag) {
        return StringRef.str(tag);
    }

    public String strRemoveRes(String tag) {
        return StringRef.str("piko_pref_remove",strRes(tag));
    }

    public String strEnableRes(String tag) {
        return StringRef.str("piko_pref_enable",strRes(tag));
    }


    //end
}