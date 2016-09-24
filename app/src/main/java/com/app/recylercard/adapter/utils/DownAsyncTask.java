package com.app.recylercard.adapter.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;

import com.app.recylercard.adapter.bean.Root;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: liyabin
 * @description:
 * @projectName: RecylerCard
 * @date: 2016-09-24
 * @time: 17:02
 */

public class DownAsyncTask extends AsyncTask<String, Void, Root> {
    public interface WeiXinCallBack {
        void getNews(Root root);
    }

    private WeiXinCallBack news;
    private ProgressDialog pd;

    public DownAsyncTask(Context context, WeiXinCallBack news) {
        this.news = news;
        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected Root doInBackground(String... params) {

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                /*InputStream is = conn.getInputStream();
                byte[] b = new byte[1024];
                int num = -1;
                StringBuffer sb = new StringBuffer();
                while ((num = is.read(b)) != -1){
                    sb.append(new String(b,0,num,"UTF-8"));
                }
                SystemClock.sleep(1000);
                String json = sb.toString();*/

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String str = null;
                StringBuffer buffer = new StringBuffer();
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                SystemClock.sleep(1000);
                String json = buffer.toString();


                Gson gson = new Gson();
                Root root = gson.fromJson(json, Root.class);
                return root;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Root root) {
        super.onPostExecute(root);
        pd.dismiss();
        if (news != null) {
            news.getNews(root);
        }
    }
}
