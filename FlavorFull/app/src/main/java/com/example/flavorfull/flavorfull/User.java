package com.example.flavorfull.flavorfull;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.graphics.Path;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by xavy_ on 10/8/2017.
 */

public class User {
    private static User user_instance = null;
    String username;
    public static boolean read = false;
    static ArrayList spices = new ArrayList();
    static public ArrayList inventory = new ArrayList();

    public static User getInstance(){
        if (user_instance == null) {
            user_instance = new User();

            spices.add("McCormick® Black Pepper, Ground");



        }
        return user_instance;
    }

    public boolean addSpice(String spice) {
        if (inventory.contains(spice)) {
            this.spices.add(spice);
            return true;
        }
        return false;

    }

    public boolean removeSspice(String spice) {
        if (inventory.contains(spice)) {
            this.spices.remove(spice);
            return true;
        }
        return false;

    }

    public void removeSpice(String spice) {
        this.spices.remove(spice);
    }

    public String display() {
        String stringB = "";

        Iterator<String> itr = spices.iterator();
        while (itr.hasNext()) {
            String element = itr.next();
            stringB += element + "\n\n";
        }

        return stringB;
    }



     public void setInventory(ArrayList al) {
         this.inventory = al;
     }

     public void setReadVal(){
         read = true;

     }



}
