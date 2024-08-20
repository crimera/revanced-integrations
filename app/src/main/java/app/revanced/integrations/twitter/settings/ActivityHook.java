package app.revanced.integrations.twitter.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import app.revanced.integrations.shared.Utils;
import android.app.Fragment;
import app.revanced.integrations.twitter.settings.featureflags.FeatureFlagsFragment;
import app.revanced.integrations.twitter.settings.fragments.*;

import static app.revanced.integrations.shared.Utils.context;

@SuppressWarnings("deprecation")
public class ActivityHook {
    public static Toolbar toolbar;
    private static final String EXTRA_PIKO_SETTINGS = "piko_settings";

    public static boolean create(Activity act) {
        Fragment fragment = null;
        Intent intent = act.getIntent();
        boolean addToBackStack = false;
        if (intent.getBooleanExtra(EXTRA_PIKO_SETTINGS, false)) {
            fragment = new SettingsFragment();
        }else if (intent.getBooleanExtra(Settings.MISC_FEATURE_FLAGS.key, false)) {
            fragment = new FeatureFlagsFragment();
        }else if (intent.getBooleanExtra(Settings.PREMIUM_SECTION.key, false)) {
            fragment = new PremiumFragment();
        }else if (intent.getBooleanExtra(Settings.DOWNLOAD_SECTION.key, false)) {
            fragment = new DownloadFragment();
        }else if (intent.getBooleanExtra(Settings.FLAGS_SECTION.key, false)) {
            fragment = new FlagsFragment();
        }else if (intent.getBooleanExtra(Settings.ADS_SECTION.key, false)) {
            fragment = new AdsFragment();
        }else if (intent.getBooleanExtra(Settings.MISC_SECTION.key, false)) {
            fragment = new MiscFragment();
        }else if (intent.getBooleanExtra(Settings.CUSTOMISE_SECTION.key, false)) {
            fragment = new CustomiseFragment();
        }else if (intent.getBooleanExtra(Settings.TIMELINE_SECTION.key, false)) {
            fragment = new TimelineFragment();
        }else if (intent.getBooleanExtra(Settings.BACKUP_SECTION.key, false)) {
            fragment = new BackupFragment();
        }else if (intent.getBooleanExtra(Settings.PATCH_INFO.key, false)) {
            fragment = new SettingsAboutFragment();
        }

        if(fragment!=null) {
            startFragment(act,fragment,addToBackStack);
            return true;
        }
        return false;
    }

    public static void startFragment(Activity act, Fragment fragment,boolean addToBackStack){
        act.setContentView(Utils.getResourceIdentifier("preference_fragment_activity", "layout"));
        toolbar = act.findViewById(Utils.getResourceIdentifier("toolbar", "id"));
        toolbar.setNavigationIcon(Utils.getResourceIdentifier("ic_vector_arrow_left", "drawable"));
        toolbar.setTitle(Utils.getResourceString("piko_title_settings"));
        toolbar.setNavigationOnClickListener(view -> act.onBackPressed());

        FragmentTransaction transaction = act.getFragmentManager().beginTransaction().replace(Utils.getResourceIdentifier("fragment_container", "id"), fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void startActivity(String activity_name)throws Exception{
        Intent intent = new Intent(context, Class.forName("com.twitter.android.AuthorizeAppActivity"));
        Bundle bundle = new Bundle();
        bundle.putBoolean(activity_name, true);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    public static void startSettingsActivity() throws Exception {
        startActivity(EXTRA_PIKO_SETTINGS);
    }
}
