package app.revanced.integrations.twitter.patches;


import com.twitter.api.model.json.core.JsonApiTweet;
import com.twitter.model.json.core.JsonTweetQuickPromoteEligibility;
import app.revanced.integrations.twitter.Pref;
import app.revanced.integrations.twitter.settings.SettingsStatus;
import java.lang.reflect.Field;

public class TweetInfo {
    private static final boolean hideCommNotes,hidePromoteBtn,forceTranslate;
    private static Field commNotesField,promoteBtnField,translateField;

    static {
        hideCommNotes = (Pref.hideCommNotes() && SettingsStatus.hideCommunityNote);
        hidePromoteBtn = (Pref.hidePromoteBtn() && SettingsStatus.hidePromoteButton);
        forceTranslate = (Pref.enableForceTranslate() && SettingsStatus.forceTranslate);
        commNotesField = null;
        promoteBtnField = null;
        translateField = null;

    }

    private static void loader(Class jsonApiTweetCls){
        commNotesField = null;
        promoteBtnField = null;
        translateField = null;

        Field[] fields = jsonApiTweetCls.getDeclaredFields();
        for(Field field : fields){
            if (field.getType() == boolean.class) {
                if(commNotesField == null){
                    commNotesField = field;
                    continue;
                }
                translateField = field;
                continue;
            }
            if (field.getType() == JsonTweetQuickPromoteEligibility.class) {
                promoteBtnField = field;
            }
        }
    }
    public static JsonApiTweet checkEntry(JsonApiTweet jsonApiTweet) {
        try {
            Class jsonApiTweetCls = jsonApiTweet.getClass();
            if( commNotesField == null || promoteBtnField == null || commNotesField == null ){
                loader(jsonApiTweetCls);
            }
            if(hideCommNotes){
                commNotesField.set(jsonApiTweet,null);
            }
            if(hidePromoteBtn){
                promoteBtnField.set(jsonApiTweet,null);
            }
            if(forceTranslate){
                translateField.set(jsonApiTweet,true);
            }

        } catch (Exception unused) {

        }
        return jsonApiTweet;
    }
}