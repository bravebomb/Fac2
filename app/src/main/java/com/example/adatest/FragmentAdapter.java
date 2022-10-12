package com.example.adatest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.adatest.AllFragments.KategoriFragment;
import com.example.adatest.AllFragments.FavoriterFragment;
import com.example.adatest.AllFragments.HemFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position){
            case 1:
                return new KategoriFragment();
            case 2:
                return new FavoriterFragment();
            default:
                return new HemFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
