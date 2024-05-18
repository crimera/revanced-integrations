package app.revanced.integrations.instagram.patches.download;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;
import app.revanced.integrations.shared.Utils;
import com.instagram.api.schemas.MediaOptionStyle;
import com.instagram.model.mediasize.ExtendedImageUrl;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class DownloadPatch {
    // To be added in revanced patches
    public static String feedOptionItemIconClassName() {
        return "";
    }

    public static String feedItemClassName() {
        return "";
    }

    public static String mediaListSourceClass() {
        return "";
    }

    public static String listFieldName() {
        return "";
    }

    public static Class<?> feedItemIconClass;
    public static Class<?> feedItem;
    public static Class<?> mediaListSourceClass;

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


        try {
            mediaListSourceClass = Class.forName(mediaListSourceClass());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addDownloadButton(List items) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Object item = feedItem
                .getConstructor(MediaOptionStyle.class, feedItemIconClass, CharSequence.class)
                // TODO dynamically get the Download icon field
                .newInstance(MediaOptionStyle.A05, feedItemIconClass.getField("A0Z").get(feedItemIconClass),
                        "Download");

        items.add(item);
    }

    public static void downloadPost(Object media, int index, Object p3, Activity act) throws URISyntaxException, NoSuchFieldException, IllegalAccessException, MalformedURLException, InvocationTargetException {
        // TODO fingerprint them fields
        // TODO support video downloading
        Field mediaField = null;

        // Get media field
        for (Field field : media.getClass().getFields()) {
            if (field.getType() == mediaListSourceClass.getInterfaces()[0]) {
                mediaField = field;
            }
        }

        assert mediaField != null;
        Object _mediaList = mediaField.get(media);
        _mediaList = mediaListSourceClass.cast(_mediaList);

        List mediaList = (List) _mediaList.getClass().getField(listFieldName()).get(_mediaList);

        // TODO Support video downloads
        if (mediaList != null) {
            downloadDialog(mediaList, index, act);
        } else {
            downloadMedia(media);
        }
    }

    public static void downloadDialog(List mediaList, int index, Activity activity) {
        // TODO use instagram dialog
        AlertDialog.Builder d = new AlertDialog.Builder(activity)
                .setTitle("Download")
                .setItems(new String[]{"Current", "Download all"}, (dialogInterface, i) -> {
                    try {
                        if (i == 0) {
                            downloadMedia(mediaList.get(index));
                        } else {
                            for (Object media : mediaList) {
                                downloadMedia(media);
                            }
                        }
                    } catch (URISyntaxException | MalformedURLException | InvocationTargetException |
                             IllegalAccessException e) {
                        e.printStackTrace();
                        Toast.makeText(Utils.getContext(), "Download failed", Toast.LENGTH_SHORT).show();
                    }
                });
        d.show();
    }

    public static void downloadMedia(Object media) throws URISyntaxException, MalformedURLException, InvocationTargetException, IllegalAccessException {
        if (isVideo(media)) {
            startDownload(getVideoLink(media));
        } else {
            startDownload(getPhotoLink(media));
        }
    }

    // TODO add a new destination parameter so we can use the same download function for video and photo
    public static void startDownload(String url) throws URISyntaxException, MalformedURLException {
        URL link = new URL(url);

        String filename = link.getPath().split("/")[link.getPath().split("/").length - 1];
        String publicFolderDirectory = Environment.DIRECTORY_PICTURES;
        String subPath = "Instagram/" + filename;

        File imageFile = new File(Environment.getExternalStoragePublicDirectory(publicFolderDirectory), subPath);

        if (imageFile.exists()) {
            return;
        }

        DownloadManager mgr = (DownloadManager) Utils.getContext().getSystemService(Context.DOWNLOAD_SERVICE);

        mgr.enqueue(new DownloadManager.Request(Uri.parse(link.toURI().toString()))
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                        DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setDescription("Downloading...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationInExternalPublicDir(publicFolderDirectory,
                        subPath));

        Toast.makeText(Utils.getContext(), "Download completed", Toast.LENGTH_SHORT).show();
    }

    public static void downloadPhoto(URL link) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) link.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "test.png");
            if (!imageFile.createNewFile()) {
                Toast.makeText(Utils.getContext(), "Failed to create file", Toast.LENGTH_SHORT).show();
            }

            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(readAllBytes(inputStream));

            fos.close();

            Toast.makeText(Utils.getContext(), "File downloaded", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(Utils.getContext(), "Error downloading", Toast.LENGTH_SHORT).show();
        }
    }

    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        final int bufLen = 4 * 0x400; // 4KB
        byte[] buf = new byte[bufLen];
        int readLen;
        IOException exception = null;

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((readLen = inputStream.read(buf, 0, bufLen)) != -1) {
                outputStream.write(buf, 0, readLen);
            }

            return outputStream.toByteArray();
        } catch (IOException e) {
            exception = e;
            throw e;
        } finally {
            if (exception == null) inputStream.close();
            else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }
    }

    private static String getVideoLink(Object media) {
        return "";
    }

    public static String getPhotoLink(Object media) throws InvocationTargetException, IllegalAccessException {
        Method getExtendedImageUrl = null;
        URL url = null;

        for (Method method : media.getClass().getMethods()) {
            if (method.getParameterTypes().length == 0) {
                continue;
            }

            if (method.getReturnType() == ExtendedImageUrl.class
                    && method.getParameterTypes()[0] == Context.class) {
                getExtendedImageUrl = method;
            }
        }

        assert getExtendedImageUrl != null;
        return ((ExtendedImageUrl) Objects
                .requireNonNull(getExtendedImageUrl.invoke(media, Utils.getContext())))
                .getUrl();
    }

    public static boolean isVideo(Object media) {
        return true;
    }

    public static void print(Object message) {
        System.out.println("BRUH: " + message);
    }
}