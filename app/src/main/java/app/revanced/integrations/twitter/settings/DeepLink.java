package app.revanced.integrations.twitter.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.List;
import android.net.Uri;
import  app.revanced.integrations.twitter.Utils;

@SuppressWarnings("deprecation")
public class DeepLink {

    public static boolean deeplink(Activity act) {
        try {
            Uri deeplink = act.getIntent().getData();
            List<String> deeplinkSegments = deeplink.getPathSegments();
            int segmentSize = deeplinkSegments.size();

            if(deeplinkSegments.size() < 2) return false;

            if(!(deeplinkSegments.get(0).equals("i"))) return false;

            String mainPath = deeplinkSegments.get(1).toLowerCase();
            boolean isPiko = mainPath.equals("piko") || mainPath.equals("pikosettings");

            if(segmentSize == 2 && isPiko){
                ActivityHook.startSettingsActivity();
                return true;
            }else if(mainPath.equals("flags")){
                ActivityHook.startActivity(Settings.FEATURE_FLAGS.key);
                return true;
            }
        }catch(Exception e){
            Utils.logger(e);
        }

        return false;
    }


}