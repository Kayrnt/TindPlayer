package fr.kayrnt.tindplayer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.R;

import fr.kayrnt.tindplayer.adapter.PhotoAdapter;

import com.gc.materialdesign.views.ButtonRectangle;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

public class ProfileDetailFragment extends Fragment {
    ViewPager viewPager;
    PagerAdapter photoContainer;
    CirclePageIndicator circlePageIndicator;
    DisplayMetrics metrics;
    private Profile profile;
    private String type;
    ButtonRectangle actionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_remove_match:
                Log.i("Detail fragment", "removing match for " + profile);
                if (profile != null) {
                    TinderAPI.getInstance().likeProfile(profile, false);
                    TinderAPI.getInstance().removeLikedProfiles(profile);
                    getActivity().finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setupButton() {
        switch (type) {
            case "liked":
                setupButtonLiked();
                return;
            case "passed":
                setupButtonPassed();
                return;
            default:
                return;
        }
    }

    private void setupButtonLiked() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinderAPI.getInstance().likeProfile(profile, false);
                synchronized (TinderAPI.getInstance().likedProfiles.profiles) {
                    TinderAPI.getInstance().likedProfiles.profiles.remove(profile);
                }
                setupButtonPassed();
            }
        });
        actionButton.setText(getString(R.string.profile_like_undo_button));
        actionButton.setVisibility(View.VISIBLE);
    }

    private void setupButtonPassed() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinderAPI.getInstance().likeProfile(profile, true);
                TinderAPI.getInstance().removePassedProfiles(profile);
                setupButtonLiked();
            }
        });
        actionButton.setText(getString(R.string.profile_like_button));
        actionButton.setVisibility(View.VISIBLE);
    }

    private List<Profile> getProfileList(String listType) {
        switch (listType) {
            case "profile":
                return TinderAPI.getInstance().profiles;
            case "liked":
                return TinderAPI.getInstance().likedProfiles.profiles;
            case "passed":
                return TinderAPI.getInstance().passedProfiles.profiles;
            case "matched":
                return TinderAPI.getInstance().matchedProfiles.profiles;
            default:
                return TinderAPI.getInstance().profiles;
        }
    }


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.fragment_profile_detail, viewGroup,
                false);
        if (getArguments() != null && getArguments().containsKey("item_id")) {
            String id = getArguments().getString("item_id");
            type = getArguments().getString("profile_type");
            List<Profile> profiles = getProfileList(type);
            for (Profile currentProfile : profiles) {
                if (id != null && id.equals(currentProfile.getId())) profile = currentProfile;
            }
        }

        if ((profile != null) && (profile.getName() != null))
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(profile.getName());

        if (profile != null) {
            photoContainer = new PhotoAdapter(getChildFragmentManager(), profile.getPhotos());
            Log.i("PHOTO ADAPTER", photoContainer.toString());
            viewPager = ((ViewPager) view.findViewById(R.id.image_pager));

            metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            viewPager.getLayoutParams().height = metrics.widthPixels;
            viewPager.setAdapter(photoContainer);
            actionButton = (ButtonRectangle) view.findViewById(R.id.detail_action_button);
            setupButton();
            circlePageIndicator = ((CirclePageIndicator) view.findViewById(R.id.circle_indicator));
            circlePageIndicator.setViewPager(viewPager);
            circlePageIndicator.setSnap(true);
            ((TextView) view.findViewById(R.id.profile_detail)).setText(profile.getNameAndAge());
            ((TextView) view.findViewById(R.id.distance)).setText(profile.getDistance());
            ((TextView) view.findViewById(R.id.last_active)).setText(profile.getLastActive());
            ((TextView) view.findViewById(R.id.about_label)).setText("About " + profile.getName());
            if (profile.getBio().isEmpty()) {
                view.findViewById(R.id.about_label).setVisibility(View.GONE);
                view.findViewById(R.id.bio).setVisibility(View.GONE);
            }
            ((TextView) view.findViewById(R.id.bio)).setText(profile.getBio());
        } else {
            ((TextView) view.findViewById(R.id.profile_detail)).setText("");
            ((TextView) view.findViewById(R.id.distance)).setText("");
            ((TextView) view.findViewById(R.id.last_active)).setText("");
            ((TextView) view.findViewById(R.id.about_label)).setText("Profile not found");
            view.findViewById(R.id.bio).setVisibility(View.GONE);
        }

        return view;
    }

}

