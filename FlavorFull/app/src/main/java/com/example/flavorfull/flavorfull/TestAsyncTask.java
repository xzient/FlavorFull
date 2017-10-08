package com.example.flavorfull.flavorfull;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestAsyncTask extends AsyncTask<Void, Void, String> {
    private Activity mContext;
    private String mUrl;
    private TextView dynamicText;

    public TestAsyncTask(Activity context, String url, TextView id) {
        mContext = context;
        mUrl = url;
        dynamicText = id;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(Void... b) {
        String resultString = null;
        resultString = getJSON(mUrl);


        final String finalResultString = resultString;
        mContext.runOnUiThread(new Runnable() {
            public void run() {
                dynamicText.setText(finalResultString);
            }
        });
        return resultString;
    }

    @Override
    protected void onPostExecute(String strings) {
        super.onPostExecute(strings);
    }

    private String getJSON(String url) {

/*
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
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
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (Exception ex) {
            return ex.toString();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    //disconnect error
                }
            }
        }

*/



        return "hello UMBC!";
    }//End get JSon




}