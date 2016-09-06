package fr.kayrnt.tindplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.adapter.FriendProfileAdapter;
import fr.kayrnt.tindplayer.api.friends.FriendListUpdateTask;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.utils.SessionManager;

public class FriendListActivity extends DrawerActivity implements SwipeRefreshLayout.OnRefreshListener {
    public FriendProfileAdapter listAdapter;
    SessionManager sessionManager;
    public TextView header;
    public GridView profileGridView;
    public SwipeRefreshLayout swipeRefreshLayout;
    TinderAPI tinderAPI = TinderAPI.getInstance();

    public void updateListUI() {
        header.setText("Here are your facebook friends using Tinder Social");
        listAdapter.updateWith(tinderAPI.getFriendProfiles());
        swipeRefreshLayout.setRefreshing(false);
    }

    public void updateFriendList() {
            new FriendListUpdateTask(tinderAPI, this).execute();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sessionManager = MyApplication.session();
        setContentView(R.layout.activity_friend_list);
        this.profileGridView = ((GridView) findViewById(R.id.gridView));
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(this);

        listAdapter = new FriendProfileAdapter(this);
        this.profileGridView.setAdapter(listAdapter);
        ProfileItemListClickListener profileItemListClickListener = new
                ProfileItemListClickListener();
        this.profileGridView.setOnItemClickListener(profileItemListClickListener);
        this.header = ((TextView) findViewById(R.id.num_liked));
        initDrawer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_refresh:
                this.updateFriendList();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onResume() {
        super.onResume();
        if ((this.sessionManager.hasTinderToken())) updateFriendList();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", "ProfileListFragment");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateFriendList();
            }
        }, 2000);
    }

    public class ProfileItemListClickListener
            implements AdapterView.OnItemClickListener {
        public ProfileItemListClickListener() {}

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final FriendProfileAdapter.ProfileView profileView = (FriendProfileAdapter.ProfileView) view
                    .getTag();
            Activity activity = FriendListActivity.this;
            Intent intent = new Intent(activity, ProfileDetailActivity.class);
            try {
                intent.putExtra("item_id", profileView.friendProfile.getUserId());
                intent.putExtra("profile_type", "api");
                activity.startActivity(intent);
            } catch (IndexOutOfBoundsException e) {
                Toast.makeText(activity, "Unable to show profile details, please retry or " +
                        "refresh", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
