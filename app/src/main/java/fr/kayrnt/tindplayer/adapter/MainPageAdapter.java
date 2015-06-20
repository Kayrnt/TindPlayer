package fr.kayrnt.tindplayer.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import fr.kayrnt.tindplayer.fragment.history.HistoryFragment;
import fr.kayrnt.tindplayer.fragment.PositionFragment;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;

public class MainPageAdapter extends FragmentPagerAdapter implements OnPageChangeListener {
    public List<String> titles;
    public Fragment currentFragment;

    public MainPageAdapter(ViewPager pager,
                           FragmentManager fm,
                           List<String> titles) {
        super(fm);
        this.titles = titles;
        pager.setAdapter(this);
        pager.setOnPageChangeListener(this);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                currentFragment = new ProfileListFragment();
                break;
            case 1:
                currentFragment = new HistoryFragment();
                break;
            case 2:
                currentFragment = new PositionFragment();
                break;
            default:
                currentFragment = new ProfileListFragment();
                break;
        }
        return currentFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String name = null;
        try {
            name = titles.get(position % titles.size()).toUpperCase();
        }
        catch(NullPointerException e) {
            Log.i("Name", "not set :/");
        }
        if(name == null) {
            return "";
        } else {
            return name;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i("View Pager", "onPageSelected : "+position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {
    }

}