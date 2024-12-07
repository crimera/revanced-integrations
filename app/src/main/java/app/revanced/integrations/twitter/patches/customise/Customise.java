package app.revanced.integrations.twitter.patches.customise;

import android.util.*;
import java.util.*;
import java.lang.reflect.Field;
import app.revanced.integrations.twitter.Pref;
import app.revanced.integrations.twitter.Utils;
import com.twitter.model.json.search.JsonTypeaheadResponse;

public class Customise {

    private static void logger(Object j){
        Utils.logger(j);
    }

    public static List navBar(List inp){
        try{
            ArrayList choices = Pref.customNavbar();
            List list2 = new ArrayList<>(inp);
            Iterator itr = list2.iterator();

            while (itr.hasNext()) {
                Object obj = itr.next();
                String itemStr = obj.toString();
                if(choices.contains(itemStr)){
                    inp.remove(obj);
                }
            }

        }catch (Exception e){
            logger(e);
        }
        return inp;
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

    public static ArrayList exploretabs(ArrayList inp){
        try{
            ArrayList choices = Pref.customExploreTabs();
            Object inpObj = inp.clone();
            ArrayList<?> arr = (ArrayList<?>) inpObj;
            Iterator itr = inp.iterator();

            while (itr.hasNext()) {

                Object obj = itr.next();
                Class<?> clazz = obj.getClass();
                Field field = clazz.getDeclaredField("a");
                String id = (String) field.get(obj);

                if (id!=null && choices.contains(id)){
                    arr.remove(obj);
                }
            }
            return arr;


        }catch (Exception e){
            logger(e);
        }
        return inp;
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

    public static List sideBar(List inp){
        try{
            ArrayList choices = Pref.customSidebar();
            List list2 = new ArrayList<>(inp);
            Iterator itr = list2.iterator();

            while (itr.hasNext()) {
                Object obj = itr.next();
                String itemStr = obj.toString();
                if(choices.contains(itemStr)){
                    inp.remove(obj);
                }
            }

        }catch (Exception e){
            logger(e);
        }
        return inp;
    }

    public static JsonTypeaheadResponse typeAheadResponse(JsonTypeaheadResponse jsonTypeaheadResponse){
        try{
            ArrayList choices = Pref.customSearchTypeAhead();
            if(choices.contains("users")){
                jsonTypeaheadResponse.a = new ArrayList<>();
            }
            if(choices.contains("topics")){
                jsonTypeaheadResponse.b = new ArrayList<>();
            }
            if(choices.contains("events")){
                jsonTypeaheadResponse.c = new ArrayList<>();
            }
            if(choices.contains("lists")){
                jsonTypeaheadResponse.d = new ArrayList<>();
            }
            if(choices.contains("ordered_section")){
                jsonTypeaheadResponse.e = new ArrayList<>();
            }
        }catch (Exception e){
            logger(e);
        }
        return jsonTypeaheadResponse;
    }

//class end
}