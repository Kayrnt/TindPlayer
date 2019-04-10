package fr.kayrnt.tindplayer.adapter;

import android.util.Log;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import fr.kayrnt.tindplayer.fragment.history.LikedFragment;
import fr.kayrnt.tindplayer.fragment.history.MatchedFragment;
import fr.kayrnt.tindplayer.fragment.history.PassedFragment;

public class HistoryPageAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    public List<String> titles;
    public Fragment currentFragment;

    public HistoryPageAdapter(ViewPager pager,
                              Fragment fragment,
                              List<String> titles) {
        super(fragment.getChildFragmentManager());
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
                currentFragment = new LikedFragment();
                break;
            case 1:
                currentFragment = new PassedFragment();
                break;
            case 2:
                currentFragment = new MatchedFragment();
                break;
            default:
                currentFragment = new LikedFragment();
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