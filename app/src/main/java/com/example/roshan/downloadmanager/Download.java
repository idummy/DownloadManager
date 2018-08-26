package com.example.roshan.downloadmanager;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.webkit.URLUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Download implements Runnable, Serializable {
    private String url;
    private String name;
    private String savePath;
    private int size;
    private int received;
    private int state;

    final int FINISHED = 0;


    Download(String address, String path) throws IOException {
        this.url = address;
        this.savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DownloadManager/";
    }


    //    @Override
    public void run() {
        System.out.println(this.url);
        download();
    }

    public void download() {
        File downloaderDirectory = null;
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            downloaderDirectory = new File(this.savePath);

            System.out.println(downloaderDirectory.getAbsolutePath());
            downloaderDirectory.mkdirs();

            this.name = URLUtil.guessFileName(this.url,connection.getHeaderField("Content-Disposition"),connection.getContentType());
            this.size = connection.getContentLength();

            File downloadedFile = new File(downloaderDirectory,this.name);
            downloadedFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(downloadedFile);
            byte[] buffer = new byte[1000];
            int r;
            int receivedBytes = 0;
            while ((r = is.read(buffer)) != -1) {
                fos.write(buffer, 0, r);
                receivedBytes = receivedBytes+ r;
                System.out.println("Received: "+receivedBytes);
                this.received = receivedBytes;
            }
            is.close();
            fos.close();
            System.out.println("Download Finished!");
            this.state = FINISHED;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getSavePath() {
        return savePath;
    }

    public String getUrl() {
        return url;
    }

    public int getSize(){
        return size;
    }

    public int getReceived() {
        return received;
    }

    public int getState() {
        return state;
    }
}
