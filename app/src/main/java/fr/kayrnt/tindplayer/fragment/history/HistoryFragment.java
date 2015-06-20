package fr.kayrnt.tindplayer.fragment.history;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.Arrays;
import java.util.List;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.adapter.HistoryPageAdapter;

public class HistoryFragment extends Fragment {

    ViewPager viewPager;
    HistoryPageAdapter pageAdapter;
    PagerSlidingTabStrip tabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View localView = paramLayoutInflater.inflate(R.layout.fragment_history_group, null);
        viewPager = (ViewPager) localView.findViewById(R.id.main_view_pager);
        tabs = (PagerSlidingTabStrip) localView.findViewById(R.id.tabs);
        List<String> pageTitles = Arrays.asList("Liked", "Passed", "Match");
        pageAdapter = new HistoryPageAdapter(viewPager, this, pageTitles);

        viewPager.setAdapter(pageAdapter);
        tabs.setViewPager(viewPager);

        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                viewPager.setCurrentItem(position);
            }
        });
        return localView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

}