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

/**
 * Created by xavy_ on 10/8/2017.
 */

public class User {
    private static User user_instance = null;
    String username;
    static ArrayList spices = new ArrayList();
    ArrayList inventory = new ArrayList();

    public static User getInstance(){
        if (user_instance == null) {
            user_instance = new User();
        }
        return user_instance;
    }

    public void addSpice(String spice) {
        this.spices.add(spice);
    }

    public void removeSpice(String spice) {
        this.spices.remove(spice);
    }

    public String display() {
        return this.inventory.toString();
    }


     public void setInventory(ArrayList al) {
         this.inventory = al;
     }



}
