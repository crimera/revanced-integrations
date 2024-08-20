package app.revanced.integrations.twitter.settings.widgets;

import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;
import app.revanced.integrations.twitter.settings.Settings;
import app.revanced.integrations.shared.Utils;
import android.preference.Preference;
//import java.util.*;

public class ListPref extends ListPreference {
    private static Helper helper;

    public ListPref(Context context) {
        super(context);
        helper = new Helper(context);
        init();
    }

    public ListPref(Context context, AttributeSet attrs) {
        super(context, attrs);
        helper = new Helper(context);
        init();
    }

    public ListPref(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        helper = new Helper(context);
        init();
    }

    private void init() {
        setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String selectedValue = (String) newValue;
                helper.setValue(preference,newValue);
                return true;
            }
        });
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        super.onSetInitialValue(restoreValue, defaultValue);
        String key = getKey();

        CharSequence[] entries = new CharSequence[]{};
        CharSequence[] entriesValues = new CharSequence[]{};
        if (key == Settings.VID_PUBLIC_FOLDER.key) {
            entries = new CharSequence[]{"Movies", "DCIM", "Pictures", "Download"};
            entriesValues = entries;
        }else if (key == Settings.CUSTOM_TIMELINE_TABS.key) {
            entries = Utils.getResourceStringArray("piko_array_timelinetabs");
            entriesValues = new CharSequence[]{"show_both","hide_forYou", "hide_following"};
        }else if (key == Settings.VID_MEDIA_HANDLE.key) {
            entries = Utils.getResourceStringArray("piko_array_download_media_handle");
            entriesValues = new CharSequence[]{"download_media","copy_media_link", "always_ask"};
        }else if (key == Settings.CUSTOM_INLINE_TABS.key) {
            entries = Utils.getResourceStringArray("piko_array_inlinetabs");
            entriesValues = new CharSequence[]{"Reply","Retweet", "Favorite","ViewCount","AddRemoveBookmarks", "TwitterShare"};
        }else if (key == Settings.CUSTOM_DEF_REPLY_SORTING.key) {
            entries = Utils.getResourceStringArray("piko_array_reply_sorting");
            entriesValues = new CharSequence[]{"Relevance","Recency", "Likes","LastPostion"};
        }

        setEntries(entries);
        setEntryValues(entriesValues);
    }
}
