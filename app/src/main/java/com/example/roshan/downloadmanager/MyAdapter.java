package com.example.roshan.downloadmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MyAdapter extends ArrayAdapter {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
    public MyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Download download = DownloaderMainActivity.downloadList.get(position);
        ViewHolder holder;
        if (convertView == null) {
            //LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitemlayout, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.name);
            holder.url = convertView.findViewById(R.id.url);
            holder.savePath = convertView.findViewById(R.id.path);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(download.getName());
        holder.savePath.setText(download.getSavePath());
        holder.url.setText(download.getUrl());

        return convertView;
    }
}