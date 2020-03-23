package com.example.jsonplaceholder.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetImageByUrl {
    private PicHandler pic_hdl;
    private ImageView imgView;
    private String url;

    /**
     * 通過圖片url獲取圖片並顯示到元件上
     * @param imgView
     * @param url
     */
    public void setImage(ImageView imgView, String url) {
        this.url = url;
        this.imgView = imgView;
        pic_hdl = new PicHandler();
        Thread t = new LoadPicThread();
        t.start();
    }

    class LoadPicThread extends Thread {
        @Override
        public void run() {
            Bitmap img = getUrlImage(url);
            System.out.println(img + "---");
            Message msg = pic_hdl.obtainMessage();
            msg.what = 0;
            msg.obj = img;
            pic_hdl.sendMessage(msg);
        }
    }

    class PicHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bitmap myimg = (Bitmap) msg.obj;
            imgView.setImageBitmap(myimg);
        }
    }

    public Bitmap getUrlImage(String url) {
        Bitmap img = null;
        try {
            Log.d("URLTEST","--" + url + "--");
            URL picurl = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) picurl
                    .openConnection();
            conn.setConnectTimeout(10000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("User-agent","Mozilla/5.0 (Linux; Android 4.2.1; Nexus 7 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166  Safari/535.19");
            conn.connect();
            InputStream is = conn.getInputStream();
            img = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
}
