package fr.kayrnt.tindplayer.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.text.NumberFormat;
import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.activity.ProfileDetailActivity;
import fr.kayrnt.tindplayer.adapter.ProfileAdapter;
import fr.kayrnt.tindplayer.api.all.ProfileLikeAllTask;
import fr.kayrnt.tindplayer.api.like.LikeTask;
import fr.kayrnt.tindplayer.api.profile.ProfileListUpdateTask;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.utils.PrefUtils;
import fr.kayrnt.tindplayer.utils.SessionManager;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class ProfileListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public ProfileAdapter listAdapter;
    public FloatingActionButton likeButton;
    public FloatingActionButton likeAllButton;
    SessionManager sessionManager;
    public TextView likedCount;
    public GridView profileGridView;
    public SwipeRefreshLayout swipeRefreshLayout;
    Handler handler;
    Random random;
    LinearLayout profilesLayout;
    TinderAPI tinderAPI = TinderAPI.getInstance();
    public boolean stopLikeAll = false;
    View layout;

    final String KEY_SHOW_TUTORIAL_LIKE_ALL_BUTTON = "tutorial_like_all_button";
    final String KEY_SHOW_TUTORIAL_LIKE_BUTTON = "tutorial_like_button";
    private String prefMaxProfile = "";

    public void stopLikeAll() {
        stopLikeAll = true;
        likeAllButton.setColorNormalResId(R.color.primary);
        likeAllButton.invalidate();
        likeAllButton.setOnClickListener(likeAllListener);
        dismissProgressDialog();
    }

    public void dismissProgressDialog() {
        if ((alertDialog != null) && alertDialog.isShowing())
            try {
                alertDialog.dismiss();
            } catch (IllegalArgumentException ignored) {
            }
    }

    @Override
    public void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }

    public View.OnClickListener stopLikeAllListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stopLikeAll();
        }
    };

    View.OnClickListener likeAllListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            stopLikeAll = false;
            likeAllButton.setColorNormalResId(R.color.ripple);
            likeAllButton.invalidate();
            alertDialog = new AlertDialog.Builder(getActivity())
                    .setMessage("Starting liking all...")
                    .setPositiveButton("Stop", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopLikeAll();
                        }
                    }).show();
            likeAllCount = 0;
            new ProfileLikeAllTask(tinderAPI, ProfileListFragment.this).execute();
            likeAllButton.setOnClickListener(stopLikeAllListener);

        }
    };

    public int getCount() {
        synchronized (tinderAPI.profiles) {
            int count = 0;
            for (Profile profile : tinderAPI.profiles) {
                if (profile.shouldLike) count++;
            }
            return count;
        }
    }

    public void updateLikeCount() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    likedCount.setText(NumberFormat.getIntegerInstance().format(tinderAPI.likeCount) +
                            " liked (" + Integer.toString(getCount()) + " selected to like)");
                }
            });
        }
    }

    public void updateListUI() {
        listAdapter.updateWith(tinderAPI.profiles);
        updateLikeCount();
        likeButton.setClickable(true);
//        tinderAPI.saveProfileHistory();
        saveLikeCount();
        swipeRefreshLayout.setRefreshing(false);
    }

    public void getMoreProfileAndUpdateUI() {
        int maxProfiles = PrefUtils.safeGetInt(tinderAPI.mPrefs, prefMaxProfile, 10);
        int profileCount = tinderAPI.profiles.size();
        if (profileCount < maxProfiles) {
            Log.i("Profile list fragment", "getting new profiles");
            new ProfileListUpdateTask(tinderAPI, this).execute();
        } else {
            Log.i("Profile list fragment", "enough profiles (" + profileCount + ")");
            updateListUI();
        }
    }

    public void onLike(View view) {
        new LikeTask(tinderAPI, this).execute();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sessionManager = MyApplication.session();
        this.random = new Random();
        this.handler = new Handler();
        this.setHasOptionsMenu(true);
        prefMaxProfile = getString(R.string.pref_liker_max_profile);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_refresh:
                this.getMoreProfileAndUpdateUI();
                return true;
            case R.id.action_help:
                TinderAPI.getInstance().mEditor.putBoolean(KEY_SHOW_TUTORIAL_LIKE_ALL_BUTTON, true);
                TinderAPI.getInstance().mEditor.putBoolean(KEY_SHOW_TUTORIAL_LIKE_BUTTON, true);
                TinderAPI.getInstance().mEditor.apply();
                if (layout != null) setupTutorial(layout);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        layout = layoutInflater.inflate(R.layout.fragment_profile_list, null);
        this.profileGridView = ((GridView) layout.findViewById(R.id.gridView));
        this.swipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(this);

        listAdapter = new ProfileAdapter(getActivity(), tinderAPI.profiles, false);
        this.profileGridView.setAdapter(listAdapter);
        ProfileItemListClickListener profileItemListClickListener = new
                ProfileItemListClickListener(this);
        this.profileGridView.setOnItemLongClickListener(profileItemListClickListener);
        this.profileGridView.setOnItemClickListener(profileItemListClickListener);
        this.likedCount = ((TextView) layout.findViewById(R.id.num_liked));

        likeButton = (FloatingActionButton) layout.findViewById(R.id.like_button);
        likeAllButton = (FloatingActionButton) layout.findViewById(R.id.like_all_button);

        updateLikeCount();

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLike(view);
            }
        });
        likeAllButton.setOnClickListener(likeAllListener);

        this.profilesLayout = ((LinearLayout) layout.findViewById(R.id.profile_fragment));

        setupTutorial(layout);

        return layout;
    }

    public void saveLikeCount() {
        tinderAPI.mEditor.putInt("like_count", tinderAPI.likeCount);
        tinderAPI.mEditor.apply();
    }

    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }
        if ((this.sessionManager.hasTinderToken()) &&
                (tinderAPI.profiles.isEmpty()))
            getMoreProfileAndUpdateUI();
    }

    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint(visible);
        if (visible && isResumed()) {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
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
                getMoreProfileAndUpdateUI();
            }
        }, 1000);
    }

    public class ProfileItemListClickListener
            implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        public ProfileItemListClickListener(ProfileListFragment listFragment) {
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final ProfileAdapter.ProfileView profileView = (ProfileAdapter.ProfileView) view.getTag();
//            profileView.profile.liked = !profileView.profile.liked;
            if (profileView.profile != null) {
                profileView.profile.shouldLike = !profileView.profile.shouldLike;
                profileView.passIcon.setVisibility(profileView.profile.shouldLike ? View.GONE : View.VISIBLE);
                profileView.passScreen.setVisibility(profileView.profile.shouldLike ? View.GONE : View.VISIBLE);
            } else Toast.makeText(view.getContext(), "Profile corrupted, please retry if relevant",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Activity activity = ProfileListFragment.this.getActivity();
            Intent intent = new Intent(activity, ProfileDetailActivity.class);
            try {
                intent.putExtra("item_id", tinderAPI.profiles.get(position).getId());
                intent.putExtra("profile_type", "profile");
                activity.startActivity(intent);
            } catch (IndexOutOfBoundsException e) {
                Toast.makeText(activity, "Unable to show profile details, please retry or " +
                        "refresh", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

    }

    /**
     * Alert dialog for like all
     */

    private AlertDialog alertDialog;
    private int likeAllCount = 0;

    public void updateLikeAllCount() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    likeAllCount++;
                    alertDialog.setMessage(likeAllCount + " liked !");
                }
            });
        } else stopLikeAll();
    }

    public void disableLikeAllAlertDialog() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) alertDialog.hide();
            }
        });
    }

    // Show tutorial for buttons

    public void setupTutorial(View layout) {

        if (TinderAPI.getInstance().mPrefs.getBoolean(KEY_SHOW_TUTORIAL_LIKE_ALL_BUTTON, true)) {
            new MaterialTapTargetPrompt.Builder(getActivity())
                    .setTarget(layout.findViewById(R.id.like_all_button))
                    .setPrimaryText("Press to like until you run out of likes!")
                    .setSecondaryText("It takes some times and runs until you're out of recommendations!")
                    .setBackgroundColour(getResources().getColor(R.color.colorPrimary))
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                        @Override
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                            if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                                // User has pressed the prompt target
                                TinderAPI.getInstance().mEditor.putBoolean(KEY_SHOW_TUTORIAL_LIKE_ALL_BUTTON, false);
                                TinderAPI.getInstance().mEditor.apply();
                            }
                        }
                    })
                    .show();
        }

        if (TinderAPI.getInstance().mPrefs.getBoolean(KEY_SHOW_TUTORIAL_LIKE_BUTTON, true)) {
            new MaterialTapTargetPrompt.Builder(getActivity())
                    .setTarget(layout.findViewById(R.id.like_button))
                    .setPrimaryText("Press to like all visible profiles!")
                    .setSecondaryText("Then it refreshes the grid until you run out of recommendations!")
                    .setBackgroundColour(getResources().getColor(R.color.colorPrimary))
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                        @Override
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                            if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                                // User has pressed the prompt target
                                TinderAPI.getInstance().mEditor.putBoolean(KEY_SHOW_TUTORIAL_LIKE_BUTTON, false);
                                TinderAPI.getInstance().mEditor.apply();
                            }
                        }
                    })
                    .show();
        }
    }

}
