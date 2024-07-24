package app.revanced.integrations.twitter.patches;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.LinearLayout;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import app.revanced.integrations.twitter.Utils;

public class NativeDownloader {

    private static Map<String, HashMap> CACHE = new HashMap<>();
    private static void setCache(String key,HashMap value){
        CACHE.put(key,value);
    }

    private static HashMap getCache(String key){
        return (HashMap)CACHE.get(key);
    }

    private static boolean isCached(String key){
        return CACHE.containsKey(key);
    }

    private static Map<String, String> MAPPING = new HashMap<>();

    private static void setMapping(String key,String value){
        MAPPING.put(key,value);
    }

    private static String getMapping(String key){
        return (String)MAPPING.get(key);
    }

    private static boolean isMapNotPresent(String key){
        return !(MAPPING.containsKey(key));
    }


    public static String downloadString(){
        return strRes("piko_pref_native_downloader_alert_title");
    }


    private static String getExtension(String typ){
        if(typ.equals("video/mp4")){
            return "mp4";
        }
        if(typ.equals("video/webm")){
            return "webm";
        }
        if(typ.equals("application/x-mpegURL")){
            return "m3u8";
        }
        return "jpg";
    }

    private static String getFilename(String postId,String username){
        return username + "_" + String.valueOf(postId);
    }


    private static ArrayList<HashMap> getMedia(Object yghObj){
        ArrayList<HashMap> mediaData = new ArrayList<HashMap>();

        Class<?> yghClazz = yghObj.getClass();
        Class<?> superClass = yghClazz.getSuperclass();

        if (superClass.isInstance(yghObj)) {
            try {
                Object superClassInstance = superClass.cast(yghObj);
                List<?> list = null;

                if(isMapNotPresent("F_MEDIALIST")){
                    for (Field field : superClass.getDeclaredFields()) {
                        if (List.class.isAssignableFrom(field.getType())) {
                            setMapping("F_MEDIALIST",field.getName());
                            break;
                        }
                    }
                }

                Field fieldList = superClass.getDeclaredField(getMapping("F_MEDIALIST"));
                fieldList.setAccessible(true);
                list = (List<?>) fieldList.get(superClassInstance);


                if(list == null){
                    return mediaData;
                }

                if(isMapNotPresent("M_MEDIADATA")){
                    Class<?> itemClass = list.get(0).getClass();
                    Method itemMethods[] = itemClass.getDeclaredMethods();
                    Method method = itemMethods[itemMethods.length -1];
                    setMapping("M_MEDIADATA",method.getName());

                    if(isMapNotPresent("F_IMG")){
                        for (Field field : itemClass.getDeclaredFields()) {
                            if (String.class.isAssignableFrom(field.getType())) {
                                setMapping("F_IMG",field.getName());
                                break;
                            }
                        }
                    }
                    if(isMapNotPresent("F_VID") || isMapNotPresent("F_VID_CDC")){
                        Class<?> clazz = method.getReturnType();
                        int c=1;
                        for (Field field : clazz.getDeclaredFields()) {
                            if (String.class.isAssignableFrom(field.getType())) {
                                if(c==1){
                                    setMapping("F_VID",field.getName());
                                }else if(c==2){
                                    setMapping("F_VID_CDC",field.getName());
                                    break;
                                }

                                c++;
                            }
                        }
                    }
                }

                for (Object item : list) {

                        Class<?> itemClass = item.getClass();

                        try {
                            Method method = itemClass.getDeclaredMethod(getMapping("M_MEDIADATA"));
                            Object returnObject = method.invoke(item);
                            HashMap<String,String> data = new HashMap<String,String>();
                            if (returnObject != null) {


                                Class<?> returnClass = returnObject.getClass();

                                Field fieldB = returnClass.getDeclaredField(getMapping("F_VID"));
                                fieldB.setAccessible(true);
                                String mediaUrl = (String) fieldB.get(returnObject);

                                Field fieldC = returnClass.getDeclaredField(getMapping("F_VID_CDC"));
                                fieldC.setAccessible(true);
                                String c = (String) fieldC.get(returnObject);
                                String ext = getExtension(c);

                                data.put("type",strRes("drafts_empty_video"));
                                data.put("ext",ext);
                                data.put("url",mediaUrl);
                                mediaData.add(data);

                            } else {
                                Field fieldY = itemClass.getDeclaredField(getMapping("F_IMG"));
                                fieldY.setAccessible(true);
                                String mediaUrl = (String) fieldY.get(item);
                                String ext = "jpg";
                                data.put("type",strRes("drafts_empty_photo"));
                                data.put("ext",ext);
                                data.put("url",mediaUrl+"?name=4096x4096&format=jpg");
                                mediaData.add(data);

                            }
                        } catch (Exception e) {
                            logger(e.toString());
                        }
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mediaData;
    }

    public static void downloader(Context ctx,Object t57){
        try {

            HashMap<String,Object> cachedData = new HashMap<>();
            String fileName = "";
            String username = "";
            ArrayList<HashMap> mediaData = new ArrayList<>();

            Class<?> clazz = t57.getClass();
            clazz.cast(t57);
            if(isMapNotPresent("M_GETID")){
                setMapping("M_GETID","getId");
            }
            //get id
            Method getIdMethod = clazz.getDeclaredMethod(getMapping("M_GETID"));
            Long idLong = (Long) getIdMethod.invoke(t57);
            String postId = String.valueOf(idLong);

            if(isCached(postId)){
                cachedData = getCache(postId);
                username = (String)cachedData.get("username");
                fileName = getFilename(postId,username);
                mediaData = (ArrayList)cachedData.get("mediaData");
            }else {

                if (isMapNotPresent("M_USERNAME") || isMapNotPresent("M_MEDIAOBJ")) {
                    Method[] methods = clazz.getDeclaredMethods();
                    int c = 0, ind = 0;
                    for (Method method : methods) {
                        if (method.getReturnType().equals(String.class) && c < 5) {
                            c++;
                            if (c == 3) {
                                setMapping("M_USERNAME", method.getName());
                            } else if (c == 4) {
                                setMapping("M_MEDIAOBJ", methods[ind + 1].getName());
                                break;
                            }
                            continue;
                        }

                        ind++;
                    }
                }


                //get username
                Method userNameMethod = clazz.getDeclaredMethod(getMapping("M_USERNAME"));
                username = (String) userNameMethod.invoke(t57);

                fileName = getFilename(postId,username);

                Method yghMethod = clazz.getDeclaredMethod(getMapping("M_MEDIAOBJ"));
                Object yghObj = (Object) yghMethod.invoke(t57);


                mediaData = getMedia(yghObj);
                cachedData.put("username",username);
                cachedData.put("mediaData",mediaData);
                setCache(postId,cachedData);
            }


            assert mediaData != null;
            if(mediaData.isEmpty()){
                Utils.toast(strRes("piko_pref_native_downloader_no_media"));
                return;
            }

            if (mediaData.size()==1) {
                downloadMedia(fileName, mediaData);
                return;
            }

            alertbox(ctx,fileName,mediaData);
        }catch (Exception ex){
            logger(ex.toString());
        }
    }



    private static void alertbox(Context ctx,String filename,ArrayList<HashMap> mediaData) throws NoSuchFieldException, IllegalAccessException {
        LinearLayout ln = new LinearLayout(ctx);
        ln.setOrientation(LinearLayout.VERTICAL);
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(strRes("piko_pref_native_downloader_alert_title"));

        int n = mediaData.size();
        String[] choices = new String[n];
        for(int i=0;i<n;i++){
            HashMap<String, String> hashMap = mediaData.get(i);
            String typ = (String)hashMap.get("type");
            choices[i] = "• "+typ+" "+String.valueOf(i+1);
        }

        builder.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                ArrayList<HashMap> mData = new ArrayList<HashMap>();
                HashMap<String,String> media = mediaData.get(which);
                mData.add(media);
                downloadMedia(filename+"_"+(which+1),mData);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(strRes("piko_pref_native_downloader_download_all"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int index) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            int i = 0;
                            for (HashMap ignored : mediaData) {
                                ArrayList<HashMap> mData = new ArrayList<>();
                                HashMap media = mediaData.get(i);
                                mData.add(media);
                                downloadMedia(filename+"_"+(++i),mData);
                            }

                        }
                        dialogInterface.dismiss();
                    }
                });

        builder.show();

        //endfunc
    }

    private static void downloadMedia(String filename,ArrayList<HashMap> mediaData){
        try{
            Utils.toast(strRes("download_started"));
            int n = mediaData.size();
            for(int i=0;i<n;i++){
                HashMap<String, String> media = mediaData.get(i);
                String mediaUrl = (String)media.get("url");
                String ext = (String)media.get("ext");

                String updFileName = filename+"."+ext;

                Utils.downloadFile(mediaUrl,updFileName);
            }

        } catch (Exception e){
            Utils.logger(e.toString());
        }
    }

    private static String strRes(String tag) {
        return Utils.strRes(tag);
    }

    private static void logger(String tag) {
        Utils.logger(tag);
    }


    //end class
}