package app.revanced.integrations.twitter.settings;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import app.revanced.integrations.shared.Utils;

public class BackupPrefFragment extends Fragment {
    private String prefData;
    private boolean featureFlag = false;

    private void startIntent(String fileName,int mode) {
        fileName = fileName+"_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ".json";
        Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("application/json");
        intent.putExtra("android.intent.extra.TITLE", fileName);
        startActivityForResult(intent, mode);
    }

    private void backupFile() {
        String str = "backup";
        startIntent(str, 1);
    }

    private void backupFlags() {
        String str = "feature_flags";
        startIntent(str, 1);
    }

    private void copyFile(String jsonString, Uri uri) {
        try {
            jsonString = this.prefData;
            byte[] bytes = jsonString.getBytes();

            OutputStream openOutputStream = getActivity().getContentResolver().openOutputStream(uri);
            openOutputStream.write(bytes);
            openOutputStream.close();
            toast("piko_pref_export_saved");
        } catch (IOException e) {
            toast("piko_pref_export_failed");
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Uri uri = null;
        if (intent != null) {
            uri = intent.getData();
        }
        if (uri == null) {
            toast("piko_pref_export_no_uri");

        }
        else if (i2 == -1) {
            copyFile(this.prefData, uri);
        }

        getFragmentManager().popBackStack();
    }

    private void toast(String tag){
        app.revanced.integrations.twitter.Utils.toast(Utils.getResourceString(tag));
    }

    @Override
    public void onCreate(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.featureFlag = getArguments().getBoolean("featureFlag", false);

        if (this.featureFlag) {
            this.prefData = app.revanced.integrations.twitter.Utils.getStringPref(Settings.MISC_FEATURE_FLAGS);
            backupFlags();
        } else {
            this.prefData = app.revanced.integrations.twitter.Utils.getAll(true);
            backupFile();
        }
    }
}