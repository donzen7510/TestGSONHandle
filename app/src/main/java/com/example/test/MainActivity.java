package com.example.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<AlbumVO> al;
    private AlbumPager fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        al = getAlbumList();

        fragment = new AlbumPager();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment, fragment);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        fragment.processResponse(al);
    }

    private ArrayList<AlbumVO> getAlbumList() {
        ArrayList<AlbumVO> list_album = new ArrayList<>();
        Gson gson = new Gson();

        try {
            InputStream is = getAssets().open("album.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("album");

            int index = 0;

            while (index < jsonArray.length()) {
                AlbumVO albumVO = gson.fromJson(jsonArray.get(index).toString(), AlbumVO.class);
                list_album.add(albumVO);
                index++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list_album;
    }
}
