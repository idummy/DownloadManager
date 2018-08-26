package com.example.roshan.downloadmanager;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class AddDownload extends AppCompatActivity {
    Button addButton;
    EditText urlAddress;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_download);

//  Acton Bar
        android.support.v7.widget.Toolbar addDownloadToolbar = findViewById(R.id.add_download_activity_toolbar);
        setSupportActionBar(addDownloadToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        addButton = findViewById(R.id.add_download_btn);
        urlAddress = findViewById(R.id.url);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = getApplicationContext().getFilesDir().getAbsolutePath();
                String url = urlAddress.getText().toString();
                Download download = null;
                try {
                    download = new Download(url,path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startDownloadThread(download);
                DownloaderMainActivity.downloadList.add(download);
            }
        });
        finish();
    }

    public void startDownloadThread(Download download) {
        Thread myThread = new Thread(download);
        myThread.start();
    }


}

