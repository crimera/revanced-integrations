package app.revanced.integrations.instagram.patches;

import app.revanced.integrations.twitter.Utils;
import com.instagram.api.schemas.MediaOptionStyle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class DownloadPatch {
    //  To be added in revanced patches
    public static String feedOptionItemIconClassName() {
        return "";
    }

    public static String feedItemClassName() {
        return "";
    }

    public static Class<?> feedItemIconClass;
    public static Class<?> feedItem;

    static {
        try {
            feedItemIconClass = Class.forName(feedOptionItemIconClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            feedItem = Class.forName(feedItemClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addDownloadButton(List items) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Object item = feedItem
                .getConstructor(MediaOptionStyle.class, feedItemIconClass, CharSequence.class)
//                TODO dynamically get the Download icon field
                .newInstance(MediaOptionStyle.A05, feedItemIconClass.getField("A0Z").get(feedItemIconClass), "Download");
        items.add(item);
    }

    private void findNormalField() {

    }
}