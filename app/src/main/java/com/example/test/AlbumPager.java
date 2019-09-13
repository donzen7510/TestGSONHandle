package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class AlbumPager extends Fragment {

    private int index = 0;

    private ArrayList<AlbumVO> albumList;
    private AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_pager, container, false);

        albumAdapter = new AlbumAdapter(getChildFragmentManager());

        final ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(albumAdapter);

        Button prevBtn = view.findViewById(R.id.prev_btn);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0)
                    viewPager.setCurrentItem(--index);
            }
        });
        Button nextBtn = view.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < albumAdapter.getCount() - 1)
                    viewPager.setCurrentItem(++index);
            }
        });

        return view;
    }

    void processResponse(ArrayList<AlbumVO> response) {
        albumList = response;

        SimpleAlbum();
    }

    static class AlbumAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<>();

        AlbumAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        void addItem(Fragment fragment) {
            items.add(fragment);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    private void SimpleAlbum() {
        for (int i = 0; i < albumList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("list", albumList);
            bundle.putInt("index", i);

            AlbumPagerDetail apd = new AlbumPagerDetail();

            apd.setArguments(bundle);
            albumAdapter.addItem(apd);
        }
        albumAdapter.notifyDataSetChanged();
    }
}
