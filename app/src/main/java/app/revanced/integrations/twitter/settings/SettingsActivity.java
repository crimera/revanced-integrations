package app.revanced.integrations.twitter.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.*;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.shared.settings.BooleanSetting;
import app.revanced.integrations.shared.settings.StringSetting;
import com.twitter.ui.widget.LegacyTwitterPreferenceCategory;

@SuppressWarnings("deprecation")
public class SettingsActivity extends Activity {
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(Utils.getResourceIdentifier("preference_fragment_activity", "layout"));
        Toolbar toolbar = findViewById(Utils.getResourceIdentifier("toolbar", "id"));
        toolbar.setNavigationIcon(Utils.getResourceIdentifier("ic_vector_arrow_left", "drawable"));
        toolbar.setTitle("Mod Settings");
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        getFragmentManager().beginTransaction().add(Utils.getResourceIdentifier("fragment_container", "id"), new Screen()).commit();
    }

    public static class Screen extends PreferenceFragment implements Preference.OnPreferenceClickListener{
        private Context context;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            context = getContext();

            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);
          //  SettingsStatus.load();
            if (SettingsStatus.enablePremiumSection()) {
                LegacyTwitterPreferenceCategory premiumPrefs = preferenceCategory("Premium", screen);
                if (SettingsStatus.enableReaderMode) {
                    premiumPrefs.addPreference(
                            switchPreference(
                                    "Enable reader mode",
                                    "Enables \"reader mode\" on long threads",
                                    Settings.PREMIUM_READER_MODE
                            )
                    );
                }
                if (SettingsStatus.enableUndoPosts) {
                    premiumPrefs.addPreference(
                            switchPreference(
                                    "Enable undo posts",
                                    "Enables ability to undo posts before posting",
                                    Settings.PREMIUM_UNDO_POSTS
                            )
                    );

                    premiumPrefs.addPreference(
                            buttonPreference(
                                    "Undo posts settings",
                                    "",
                                    Settings.PREMIUM_UNDO_POSTS.key
                            )
                    );
                }
                if (SettingsStatus.enableAppIconNNavIcon) {
                    premiumPrefs.addPreference(
                            buttonPreference(
                                    "App icon and navbar customisation settings",
                                    "",
                                    Settings.PREMIUM_ICONS
                            )
                    );
                }
            }

            if (SettingsStatus.changeDownloadEnabled) {
                LegacyTwitterPreferenceCategory downloadPrefs = preferenceCategory("Download", screen);
                downloadPrefs.addPreference(listPreference(
                        "Public folder",
                        "The public folder to use for video downloads",
                        Settings.VID_PUBLIC_FOLDER
                ));
                downloadPrefs.addPreference(editTextPreference(
                        "Download subfolder",
                        "The subfolder to download videos to ([PublicFolder]/[Subfolder])",
                        Settings.VID_SUBFOLDER
                ));
            }

            if(SettingsStatus.enableAdsSection()){
                LegacyTwitterPreferenceCategory adsPrefs = preferenceCategory("Ads", screen);

                if (SettingsStatus.hideAds) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide promoted posts",
                                    "",
                                    Settings.ADS_HIDE_PROMOTED_POSTS
                            )
                    );
                }

                if (SettingsStatus.hideGAds) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide google ads",
                                    "",
                                    Settings.ADS_HIDE_GOOGLE_ADS
                            )
                    );
                }
                if (SettingsStatus.hideWTF) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide \"Who to follow\" section",
                                    "",
                                    Settings.ADS_HIDE_WHO_TO_FOLLOW
                            )
                    );
                }

                if (SettingsStatus.hideCTS) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide \"Creators to subscribe\" section",
                                    "",
                                    Settings.ADS_HIDE_CREATORS_TO_SUB
                            )
                    );
                }

                if (SettingsStatus.hideCTJ) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide \"Community to join\" section",
                                    "",
                                    Settings.ADS_HIDE_COMM_TO_JOIN
                            )
                    );
                }

                if (SettingsStatus.hideRBMK) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide \"Revist your bookmark\" section",
                                    "",
                                    Settings.ADS_HIDE_REVISIT_BMK
                            )
                    );
                }

                if (SettingsStatus.hideRPinnedPosts) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide \"Pinned posts by followers\" section",
                                    "",
                                    Settings.ADS_HIDE_REVISIT_PINNED_POSTS
                            )
                    );
                }

                if (SettingsStatus.hideDetailedPosts) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide detailed posts (in replies)",
                                    "",
                                    Settings.ADS_HIDE_DETAILED_POSTS
                            )
                    );
                }

                if (SettingsStatus.hidePromotedTrend) {
                    adsPrefs.addPreference(
                            switchPreference(
                                    "Hide promoted trends",
                                    "",
                                    Settings.ADS_HIDE_PROMOTED_TRENDS
                            )
                    );
                }



            }

            if (SettingsStatus.enableMiscSection()) {
                LegacyTwitterPreferenceCategory miscPrefs = preferenceCategory("Misc", screen);
                if (SettingsStatus.enableFontMod) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    "Enable chirp font",
                                    "",
                                    Settings.MISC_FONT
                            )
                    );
                }
                if (SettingsStatus.hideFAB) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    "Hide floating action button",
                                    "",
                                    Settings.MISC_HIDE_FAB
                            )
                    );
                }

                if (SettingsStatus.hideRecommendedUsers) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    "Hide recommended users",
                                    "",
                                    Settings.MISC_HIDE_RECOMMENDED_USERS
                            )
                    );
                }

                if (SettingsStatus.hideCommunityNote) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    "Hide community notes",
                                    "",
                                    Settings.MISC_HIDE_COMM_NOTES
                            )
                    );
                }
                if (SettingsStatus.hideViewCount) {
                    miscPrefs.addPreference(
                            switchPreference(
                                    "Hide view count",
                                    "",
                                    Settings.MISC_HIDE_VIEW_COUNT
                            )
                    );
                }

                if (SettingsStatus.customSharingDomainEnabled) {
                    miscPrefs.addPreference(
                            editTextPreference(
                                    "Custom sharing domain",
                                    "The domain to use when sharing tweets",
                                    Settings.CUSTOM_SHARING_DOMAIN
                            )
                    );
                }
            }

            if (SettingsStatus.enableTimelineSection()) {
                LegacyTwitterPreferenceCategory timelinePrefs = preferenceCategory("Timeline", screen);
                if (SettingsStatus.hideForyou) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    "Hide for you tab",
                                    "",
                                    Settings.TIMELINE_HIDE_FORYOU
                            )
                    );
                }
                if (SettingsStatus.hideLiveThreads) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    "Hide live threads",
                                    "",
                                    Settings.TIMELINE_HIDE_LIVETHREADS
                            )
                    );
                }
                if (SettingsStatus.hideBanner) {
                    timelinePrefs.addPreference(
                            switchPreference(
                                    "Hide banner",
                                    "",
                                    Settings.TIMELINE_HIDE_BANNER
                            )
                    );
                }
            }

            setPreferenceScreen(screen);
        }

        private Preference editTextPreference(String title, String summary, StringSetting setting) {
            EditTextPreference preference = new EditTextPreference(context);
            preference.setTitle(title);
            preference.setDialogTitle(title);
            preference.setSummary(summary);
            preference.setKey(setting.key);
            preference.setDefaultValue(setting.defaultValue);

            return preference;
        }

        private Preference switchPreference(String title, String summary, BooleanSetting setting) {
            SwitchPreference preference = new SwitchPreference(context);
            preference.setTitle(title);
            preference.setSummary(summary);
            preference.setKey(setting.key);
            preference.setDefaultValue(setting.defaultValue);

            return preference;
        }

        private Preference buttonPreference(String title, String summary, String key) {
            Preference preference = new Preference(context);
            preference.setKey(key);
            preference.setTitle(title);
            preference.setSummary(summary);
            preference.setOnPreferenceClickListener(this);

            return preference;
        }

        private Preference listPreference(String title, String summary, StringSetting setting) {
            ListPreference preference = new ListPreference(context);
            preference.setTitle(title);
            preference.setDialogTitle(title);
            preference.setSummary(summary);
            preference.setKey(setting.key);
            preference.setDefaultValue(setting.defaultValue);
            preference.setEntries(new CharSequence[]{"Movies", "DCIM", "Pictures", "Download"});
            preference.setEntryValues(new CharSequence[]{"Movies", "DCIM", "Pictures", "Download"});

            return preference;
        }

        private LegacyTwitterPreferenceCategory preferenceCategory(String title, PreferenceScreen screen) {
            LegacyTwitterPreferenceCategory preferenceCategory = new LegacyTwitterPreferenceCategory(context);
            preferenceCategory.setTitle(title);
            screen.addPreference(preferenceCategory);
            return preferenceCategory;
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            String key = preference.getKey();
            if(key.equals(Settings.PREMIUM_UNDO_POSTS.key.toString())){
                app.revanced.integrations.twitter.Utils.startUndoPostActivity();
            }
            else if(key.equals(Settings.PREMIUM_ICONS)){
                app.revanced.integrations.twitter.Utils.startAppIconNNavIconActivity();
            }
            return true;
        }
    }


}
