package app.revanced.integrations.twitter.patches.customise;

import android.util.Log;

import java.util.List;

public class NavBar {
    public static void printList(List list) {
        for (Object o: list) {
            print(o.getClass().getName());
        }
    }

    public static void print(String a) {
        System.out.println("BRUH"+ a);
    }
}
