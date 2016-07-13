package fr.kayrnt.tindplayer.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import java.util.Arrays;
import java.util.List;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.adapter.MainPageAdapter;
import fr.kayrnt.tindplayer.utils.SessionManager;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;
    ViewPager viewPager;
    MainPageAdapter pageAdapter;
    PagerSlidingTabStrip tabs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LinearLayout layout = (LinearLayout) findViewById(R.id.main_container);
        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        List<String> pageTitles = Arrays.asList("Liker", "History", "Position");
        pageAdapter = new MainPageAdapter(viewPager, getSupportFragmentManager(), pageTitles);

        viewPager.setAdapter(pageAdapter);
        tabs.setViewPager(viewPager);

        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                viewPager.setCurrentItem(position);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        sessionManager = MyApplication.session();
        sessionManager.checkLogin(this);
        if (sessionManager.isLoggedIn()) sessionManager.authTinder(this);
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            case R.id.action_settings:
                sessionManager.logoutUser();
                return true;
            case R.id.action_feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(paramMenuItem);
    }

}