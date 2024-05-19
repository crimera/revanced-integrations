package app.revanced.integrations.twitter.patches.customise;

import android.util.*;
import java.util.*;
import java.lang.reflect.Field;
import app.revanced.integrations.twitter.Pref;

public class InlineBar {

    private static void logger(Object j){
        Log.d("piko", j.toString());
    }


    public static List inlineBar(List inp){
        try{
                ArrayList choices = Pref.inlineBar();
                List list2 = new ArrayList<>(inp);
                Iterator itr = inp.iterator();

                while (itr.hasNext()) {
                    Object obj = itr.next();
                    String itemStr = obj.toString();
                    if(choices.contains(itemStr)){
                        list2.remove(obj);
                    }
                }
            return list2;
        }catch (Exception e){
            logger(e);
        }
        return inp;
    }
}