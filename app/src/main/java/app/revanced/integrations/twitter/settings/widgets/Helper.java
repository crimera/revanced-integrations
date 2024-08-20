package app.revanced.integrations.twitter.settings.widget;

import android.preference.Preference;
import app.revanced.integrations.twitter.Utils;
import java.util.Set;
import app.revanced.integrations.shared.settings.BooleanSetting;
import app.revanced.integrations.shared.settings.StringSetting;
import android.content.Context;

public class Helper {
     private Context context;

     public Helper(Context context){
         this.context = context;
     }
    

    public Preference editTextPreference(String title, String summary, StringSetting setting) {
        EditTextPref preference = new EditTextPref(context);
        preference.setTitle(title);
        preference.setDialogTitle(title);
        preference.setSummary(summary);
        preference.setKey(setting.key);
        preference.setDefaultValue(setting.defaultValue);
//        setOnPreferenceChangeListener(preference);
        return preference;
    }

    public Preference switchPreference(String title, String summary, BooleanSetting setting) {
        SwitchPref preference = new SwitchPref(context);
        preference.setTitle(title);
        preference.setSummary(summary);
        preference.setKey(setting.key);
        preference.setDefaultValue(setting.defaultValue);
//        setOnPreferenceChangeListener(preference);
        return preference;
    }

    public Preference buttonPreference(String title, String summary, String key) {
        ButtonPref button = new ButtonPref(context);
        button.setKey(key);
        button.setTitle(title);
        button.setSummary(summary);
//        preference.setOnPreferenceClickListener(this);
        return button;
    }

    public Preference listPreference(String title, String summary, StringSetting setting) {
        ListPref preference = new ListPref(context);
        String key = setting.key;
        preference.setTitle(title);
        preference.setDialogTitle(title);
        preference.setSummary(summary);
        preference.setKey(key);
        preference.setDefaultValue(setting.defaultValue);
//        setOnPreferenceChangeListener(preference);
        return preference;
    }

    public Preference multiSelectListPref(String title, String summary, StringSetting setting) {
        MultiSelectListPref preference = new MultiSelectListPref(context);
        String key = setting.key;
        preference.setTitle(title);
        preference.setDialogTitle(title);
        preference.setSummary(summary);
        preference.setKey(key);
//        setOnPreferenceChangeListener(preference);
        return preference;
    }

    public void setValue(Preference preference,Object newValue){
        String key = preference.getKey();
        try {
            if (newValue != null) {
                String newValClass = newValue.getClass().getSimpleName();

                if (newValClass.equals("Boolean")) {
                    Utils.setBooleanPerf(key, (Boolean) newValue);
                } else if (newValClass.equals("String")) {
                    Utils.setStringPref(key, (String) newValue);
                } else if (newValClass.equals("HashSet")) {
                    Utils.setSetPerf(key, (Set) newValue);
                }
            }

        } catch (Exception ex) {
            Utils.toast(ex.toString());
            Utils.logger(ex);
        }
    }
    
    
    
}