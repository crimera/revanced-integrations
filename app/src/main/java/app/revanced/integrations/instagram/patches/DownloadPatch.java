package app.revanced.integrations.instagram.patches;

import android.app.Activity;
import com.instagram.api.schemas.MediaOptionStyle;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class DownloadPatch {
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

    public static void downloadPost(Object p1, int p2, Object p3, Activity p4) {
//        TODO implement method
        System.out.println("BRUH p1: "+p1+", p2: "+p2+", p3: "+p3+", p4: "+p4);
    }

    public static void print(Object message) {
        System.out.println("BRUH: "+message);
    }
}