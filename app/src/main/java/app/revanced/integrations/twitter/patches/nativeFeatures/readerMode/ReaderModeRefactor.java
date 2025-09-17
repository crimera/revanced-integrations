package app.revanced.integrations.twitter.patches.nativeFeatures.readerMode;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import app.revanced.integrations.shared.StringRef;
import app.revanced.integrations.shared.Utils;
import app.revanced.integrations.twitter.patches.nativeFeatures.readerMode.ReaderModeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import app.revanced.integrations.twitter.settings.Settings;
import org.json.JSONObject;

public class ReaderModeRefactor extends Fragment {
    private WebView webView;

    private String tweetId;

    // JavaScript interface class
    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void copyText(String text) {
            Utils.setClipboard(text);
            Utils.showToastShort(StringRef.str("link_copied_to_clipboard"));
        }

        @JavascriptInterface
        public int getTheme() {
            System.out.println("Getting Theme" + app.revanced.integrations.twitter.Utils.getTheme());
            return app.revanced.integrations.twitter.Utils.getTheme();
        }

        @JavascriptInterface
        public void log(String message) {
            System.out.println("WebView Log: " + message);
        }

        @JavascriptInterface
        public String getColor(String id, String defPackage) {
            int color = mContext.getColor(mContext.getResources().getIdentifier(id, "color", defPackage));
            return String.format("#%06X", (0xFFFFFF & color));
        }

        @JavascriptInterface
        public String getThreadInfo() {
            System.out.println("Getting Thread Info for tweetId: " + tweetId);
            if (tweetId == null || tweetId.isEmpty()) return "{\"error\":\"Invalid tweetId\"}";
            try {
                String cachedJson = ReaderModeUtils.readJsonCacheFile(tweetId);
                if (cachedJson != null) return cachedJson;

                JSONObject jsonResponse = ReaderModeUtils.getThreadInfo(tweetId);
                System.out.println("Getting Thread Info for tweetId: " + tweetId);
                if (jsonResponse != null) {
                    String jsonString = jsonResponse.toString();
                    ReaderModeUtils.writeJsonCacheFile(tweetId, jsonString);
                    return jsonString;
                }
                return "{\"error\":\"Failed to fetch thread data\"}";
            } catch (Exception e) {
                return "{\"error\":\"" + e.getMessage() + "\"}";
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tweetId = getArguments().getString(ReaderModeUtils.ARG_TWEET_ID);
        }
    }


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(Utils.getResourceIdentifier("webview", "layout"), container, false);

        // View  progressBarView = inflater.inflate(Utils.getResourceIdentifier("progress_bar", "layout"), container, false);
        // ProgressBar progressBar = progressBarView.findViewById(Utils.getResourceIdentifier("progressbar", "id"));
        // progressBar.setVisibility(View.VISIBLE);

        webView = rootView.findViewById(Utils.getResourceIdentifier("webview", "id"));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(getContext()), "Android");
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        webView.setWebViewClient(new WebViewClient() {
            // @Override
            // public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //     progressBar.setVisibility(View.VISIBLE);
            // }
            @Override
            public void onPageFinished(WebView view, String url) {
                // progressBar.setVisibility(View.GONE);
                // webView.evaluateJavascript(ReaderModeUtils.injectJS(), null);
            }
        });

        if (tweetId != null && !tweetId.isEmpty()) {
            if (app.revanced.integrations.twitter.Utils.getStringPref(Settings.READER_MODE_URL).isEmpty()) {
                webView.loadData(readHtmlFromRaw(getContext()), "text/html", "UTF-8");
            } else {
                webView.loadUrl(app.revanced.integrations.twitter.Utils.getStringPref(Settings.READER_MODE_URL));
            }
        } else {
            webView.loadData(ReaderModeUtils.NO_CONTENT,"text/html", "UTF-8");
        }

        return rootView;
    }

    public String readHtmlFromRaw(Context context) {
        try {
            InputStream inputStream = context.getResources().openRawResource(Utils.getResourceIdentifier("reader_mode", "raw"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
