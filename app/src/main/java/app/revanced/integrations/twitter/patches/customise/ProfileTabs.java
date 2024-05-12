package app.revanced.integrations.twitter.patches.customise;

import android.util.*;
import java.util.*;
import java.lang.reflect.Field;
import app.revanced.integrations.twitter.Pref;

public class ProfileTabs {

    private static String str(Object j){
        return String.valueOf(j)+"\n";
    }
    public static void logger(Object j){
        Log.d("piko", j.toString());
    }


    public static ArrayList profiletabs(ArrayList inp){
        try{
                ArrayList choices = Pref.customProfileTabs();
                Object inpObj = inp.clone();
                ArrayList<?> arr = (ArrayList<?>) inpObj;
                Iterator itr = inp.iterator();

                while (itr.hasNext()) {

                    Object obj = itr.next();
                    Class<?> clazz = obj.getClass();
                    Field field = clazz.getDeclaredField("g");
                    String g = (String) field.get(obj);

                    if ((g!=null && choices.contains(g)) || (g==null && choices.contains("subs"))){
                        arr.remove(obj);
                    }
                }
                return arr;


        }catch (Exception e){
            logger(e);
        }
        return inp;
    }
}