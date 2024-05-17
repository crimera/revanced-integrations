package app.revanced.integrations.instagram.patches;

import android.app.Activity;
import android.content.Context;
import app.revanced.integrations.shared.Utils;
import com.instagram.api.schemas.MediaOptionStyle;
import com.instagram.model.mediasize.ExtendedImageUrl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
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

    public static void downloadPost(Object media, int p2, Object p3, Activity p4) throws InvocationTargetException, IllegalAccessException {
//        TODO implement method
        System.out.println("BRUH p1: "+media+", p2: "+p2+", p3: "+p3+", p4: "+p4);
//        TODO implement download a post with only 1 media
        print("BRUH: "+getPhotoLink(media));
    }

    public static String getPhotoLink(Object media) throws InvocationTargetException, IllegalAccessException {
        Method getExtendedImageUrl = null;
        for (Method method : media.getClass().getMethods()) {
            if (method.getParameterTypes().length == 0) {continue;}

            if (method.getReturnType() == ExtendedImageUrl.class && method.getParameterTypes()[0] == Context.class) {
                getExtendedImageUrl = method;
            }
        }

        assert getExtendedImageUrl != null;
        return ((ExtendedImageUrl) Objects.requireNonNull(getExtendedImageUrl.invoke(media, Utils.getContext()))).getUrl();
    }

    public static void print(Object message) {
        System.out.println("BRUH: "+message);
    }
}