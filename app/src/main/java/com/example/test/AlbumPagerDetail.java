package com.example.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AlbumPagerDetail extends Fragment {

    private int index;
    private ArrayList<AlbumVO> albumList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            index = getArguments().getInt("index");
            albumList = getArguments().<AlbumVO>getParcelableArrayList("list");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_list, container, false);

        if (albumList != null) {
            TextView textView = view.findViewById(R.id.title);
            textView.setText(albumList.get(index).getTitle());
            textView = view.findViewById(R.id.artist);
            textView.setText(albumList.get(index).getArtist());
            textView = view.findViewById(R.id.thumb);
            textView.setText(albumList.get(index).getThumb());
            textView = view.findViewById(R.id.date);
            textView.setText(albumList.get(index).getDate());
            textView = view.findViewById(R.id.type);
            textView.setText(albumList.get(index).getType());
            textView = view.findViewById(R.id.song);
            StringBuilder str_song = new StringBuilder();
            for (String str : albumList.get(index).getSong()) {
                str_song.append(str).append("\r\n");
            }
            textView.setText(str_song);
            textView = view.findViewById(R.id.introduce);
            textView.setText(albumList.get(index).getIntroduce());
        }

        return view;
    }
}
