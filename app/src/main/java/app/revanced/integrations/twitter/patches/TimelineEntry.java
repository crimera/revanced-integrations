package app.revanced.integrations.twitter.patches;

import com.twitter.model.json.timeline.urt.JsonTimelineEntry;

import app.revanced.integrations.twitter.Pref;
import app.revanced.integrations.twitter.settings.SettingsStatus;

public class TimelineEntry {
    private static final boolean hideAds,hideGAds,hideWTF,hideCTS,hideCTJ,hideDetailedPosts,hideRBMK,hidePinnedPosts,hidePremiumPrompt,hideMainEvent;
    static {
        hideAds = (Pref.hideAds() && SettingsStatus.hideAds);
        hideGAds = (Pref.hideGoogleAds() && SettingsStatus.hideGAds);
        hideWTF = (Pref.hideWTF() && SettingsStatus.hideWTF);
        hideCTS = (Pref.hideCTS() && SettingsStatus.hideCTS);
        hideCTJ = (Pref.hideCTJ() && SettingsStatus.hideCTJ);
        hideDetailedPosts = (Pref.hideDetailedPosts() && SettingsStatus.hideDetailedPosts);
        hideRBMK = (Pref.hideRBMK() && SettingsStatus.hideRBMK);
        hidePinnedPosts = (Pref.hideRPinnedPosts() && SettingsStatus.hideRPinnedPosts);
        hidePremiumPrompt = (Pref.hidePremiumPrompt() && SettingsStatus.hidePremiumPrompt);
        hideMainEvent = (Pref.hideMainEvent() && SettingsStatus.hideMainEvent);
    }


    private static boolean isEntryIdRemove(String entryId) {
        String[] split = entryId.split("-");
        String entryId2 = split[0];
        if (!entryId2.equals("cursor") && !entryId2.equals("Guide") && !entryId2.startsWith("semantic_core")) {
            if ((entryId2.equals("promoted") || ((entryId2.equals("conversationthread") && split.length == 3) || entryId2.equals("superhero"))) && hideAds) {
                return true;
            }
            if (entryId2.equals("rtb") && hideGAds) {
                return true;
            }

            if (entryId2.equals("tweetdetailrelatedtweets") && hideDetailedPosts) {
                return true;
            }
            if (entryId2.equals("bookmarked") && hideRBMK) {
                return true;
            }
            if (entryId.startsWith("community-to-join") && hideCTJ) {
                return true;
            }
            if (entryId.startsWith("who-to-follow") && hideWTF) {
                return true;
            }
            if (entryId.startsWith("who-to-subscribe") && hideCTS) {
                return true;
            }
            if (entryId.startsWith("pinned-tweets") && hidePinnedPosts) {
                return true;
            }
            if (entryId.startsWith("messageprompt-") && hidePremiumPrompt) {
                return true;
            }
            if (entryId.startsWith("main-event-") && hideMainEvent) {
                return true;
            }
        }
        return false;
    }

    public static JsonTimelineEntry checkEntry(JsonTimelineEntry jsonTimelineEntry) {
        try {
            String entryId = jsonTimelineEntry.a;
            if(isEntryIdRemove(entryId)){
                return null;
            }
        } catch (Exception unused) {

        }
        return jsonTimelineEntry;
    }

//end
}