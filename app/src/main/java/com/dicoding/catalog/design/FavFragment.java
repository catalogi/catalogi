package com.dicoding.catalog.design;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dicoding.catalog.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {

    public FavFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_favorite, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        ViewPager vPager= v.findViewById(R.id.viewpager);
        setupViewPager(vPager);

        TabLayout tabL = v.findViewById(R.id.tabs);
        tabL.setupWithViewPager(vPager);
    }

    private void setupViewPager(ViewPager vPager) {
        ViewPgAdapter Adapter = new ViewPgAdapter(getChildFragmentManager());
        Adapter.addFragment(new MovFavorite(),getString(R.string.movies));
        vPager.setAdapter(Adapter);
    }


    class ViewPgAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> listFrag = new ArrayList<>();
        private final List<String> listFraTitle = new ArrayList<>();



        ViewPgAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return listFrag.get(position);
        }

        @Override
        public int getCount() {
            return listFrag.size();
        }

        void addFragment(Fragment fm, String title){
            listFrag.add(fm);
            listFraTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return listFraTitle.get(position);
        }
    }

}
