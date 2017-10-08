package com.example.flavorfull.flavorfull;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TestAsyncTask extends AsyncTask<Void, Void, String> {
    private static ArrayList<String> viewed = new ArrayList<String>();
    private Activity mContext;
    private String mUrl;
    private String recipeID;
    private ImageView picture;
    static StringBuilder sb = null;

    public TestAsyncTask(Activity context, String url, String recipeID) {
        mContext = context;
        mUrl = url;
        this.recipeID = recipeID;
        picture = (ImageView) mContext.findViewById(R.id.spiceImage);
        viewed.add(recipeID);

    }

    public String findRecipe(){
        HttpURLConnection c = null;
        Log.d("DEBUGGING!!! ", "debug test ");
        if(sb == null) {
            Log.d("DEBUGGING!!! ", "sb is null ");
            try {
                URL u = new URL(mUrl);
                c = (HttpURLConnection) u.openConnection();
                c.connect();
                int status = c.getResponseCode();
                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        br.close();
                }

            } catch (Exception ex) {
                return ex.toString();
            }
        }


        ArrayList<String> allSpices = User.inventory;

        //TODO: change
        ArrayList<String> hasSpices = new ArrayList<String>();
        hasSpices.add("McCormick® Black Pepper, Ground");
        hasSpices.add("McCormick® Thyme Leaves");

        Log.d("DEBUGGING!!! ", "added " + hasSpices.get(0) + " " + hasSpices.get(1));
        try{
            JSONObject json = new JSONObject(sb.toString());
            JSONArray arr = json.getJSONArray("content");

            //loop through all recipes
            for (int i = 0; i < arr.length(); i++) {


                JSONObject recipe = new JSONObject(arr.get(i).toString());
                JSONArray ingredients = recipe.getJSONArray("ingredients");


                Log.d("DEBUGGING!!! ", "In recipe: " + recipe.get("title").toString());

                int total = 0;
                int has = 0;

                //loop ingredients in recipe
                for (int j = 0; i < ingredients.length(); i++) {
                    JSONObject actualIngredient = new JSONObject(ingredients.get(i).toString());
                    String spiceName = actualIngredient.get("ingredientName").toString().replace("&reg;","®");
                    Log.d("DEBUGGING!!! ", "In ingredient: " + spiceName);
                    if (allSpices.contains(spiceName)) {
                        Log.d("DEBUGGING!!! ", spiceName + " is a spice");
                        total++;
                        if (hasSpices.contains(spiceName)) {
                            Log.d("DEBUGGING!!! ", "USER OWNS SPICE");
                            has++;
                        }else{
                            Log.d("DEBUGGING!!! ", "USER DOES NOT OWN SPICE");
                        }
                    }else{

                    }
                }
                if (has == total && !viewed.contains(recipe.get("id").toString())) {

                    Log.d("DEBUGGING!!! ", "matched perfectly: " + recipe.get("title").toString());
                    viewed.add(recipe.get("id").toString());
                    recipeID = recipe.get("id").toString();
                    return recipe.get("id").toString();
                }else{
                    //add close matches to list then loop through later
                }
            }
        }catch(JSONException je){
            je.printStackTrace();
        }

        Log.d("DEBUGGING!!! ", "returned null ");
        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(Void... b) {
        //Log.d("DEBUGGING!!!  ", "doInBackground");
        if(sb != null){
            recipeID = findRecipe();
        }
        doJSON(mUrl);


        return null;
    }


    public Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();

            final Drawable d = Drawable.createFromStream(is, "spiceImage");


            mContext.runOnUiThread(new Runnable() { public void run() {
                picture.setImageDrawable(d);
            }});



            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String strings) {
        super.onPostExecute(strings);
    }

    private void doJSON(String url) {



        HttpURLConnection c = null;

        if(sb == null) {
            try {
                URL u = new URL(url);
                c = (HttpURLConnection) u.openConnection();
                c.connect();
                int status = c.getResponseCode();
                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        br.close();

                }

            } catch (Exception ex) {
                return /*ex.toString()*/;
            }
        };
        try{

            JSONObject json = new JSONObject(sb.toString());

            JSONArray arr = json.getJSONArray("content");

            JSONObject recipe = null;

            for(int i = 0; i < arr.length(); i++){
                recipe = new JSONObject(arr.get(i).toString());
                String currentId = recipe.get("id").toString();
                if(currentId.equals(recipeID)){
                    break;
                }
            }




            String title = recipe.get("title").toString();

            final String finalTitle = title;
            mContext.runOnUiThread(new Runnable() { public void run() {
                TextView txtv = (TextView) mContext.findViewById(R.id.txt_title);
                txtv.setText(finalTitle);
            }});


            String desc = recipe.get("description").toString();

            final String finaldesc = desc;
            mContext.runOnUiThread(new Runnable() { public void run() {
                TextView txtv = (TextView) mContext.findViewById(R.id.txt_description);
                txtv.setText(finaldesc);
            }});

            StringBuilder ingSB = new StringBuilder();

            JSONArray ingredients = recipe.getJSONArray("ingredients");
            for(int i = 0; i < ingredients.length(); i++){
                JSONObject actualIngredient = new JSONObject(ingredients.get(i).toString());

                JSONObject uim1 = new JSONObject(actualIngredient.toString());
                JSONObject uim2 = new JSONObject(uim1.get("primUom").toString());

                ingSB.append("[+] " + actualIngredient.get("primQty").toString() + " " + uim2.get("name").toString() + " " + actualIngredient.get("ingredientName").toString().replace("&reg;", "®") + "\n");

            }


            final String finalIngredients = ingSB.toString();
            mContext.runOnUiThread(new Runnable() { public void run() {
                TextView txtv = (TextView) mContext.findViewById(R.id.txt_ingredients);
                txtv.setText(finalIngredients);
            }});


            JSONArray instructions = recipe.getJSONArray("recipe_instructions");
            String terrible = "[+] " + instructions.toString().replace("\"", "").replace("[","").replace("]","").replace(",","\n[+] ");
            terrible = terrible.substring(0, terrible.length()-3);


            final String finalInstructions = terrible;
            mContext.runOnUiThread(new Runnable() { public void run() {
                TextView txtv = (TextView) mContext.findViewById(R.id.txt_instructions);
                txtv.setText(finalInstructions);
            }});

            LoadImageFromWebOperations(recipe.get("desktop_image").toString());
        }catch (JSONException je){
            je.printStackTrace();
        }

        return;
    }//End get JSon




}