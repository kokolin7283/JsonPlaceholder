package com.example.jsonplaceholder.Network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jsonplaceholder.Bean.DataBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ContentGetAsync extends AsyncTask<String,Void ,String> {
    private ProgressBar progressBar;
    private ArrayList<DataBean> list = new ArrayList<DataBean>();

    //宣告一個TaskListener介面, 接收回傳值的物件必須實作它
    public interface TaskListener{
        void onFinished(ArrayList<DataBean> result);
    }

    //接收回傳值的物件參考
    private final TaskListener taskListener;

    public ContentGetAsync(ProgressBar progressBar ,ArrayList<DataBean> list ,TaskListener taskListener){
        this.progressBar = progressBar;
        this.list = list;
        this.taskListener = taskListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... parms) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(parms[0]);
            Log.d("TEST", url.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = in.readLine();
            while (line != null) {
                Log.d("HTTP", line);
                sb.append(line);
                line = in.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("Json", "TEST");
        parseJSON(s);
        progressBar.setVisibility(View.INVISIBLE);
        taskListener.onFinished(list);
    }

    private void parseJSON(String s) {
        try {
            Gson gson = new Gson();
            list = gson.fromJson(s, new TypeToken<ArrayList<DataBean>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
