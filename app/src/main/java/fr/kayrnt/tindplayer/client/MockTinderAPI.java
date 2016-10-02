package fr.kayrnt.tindplayer.client;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.List;

import fr.kayrnt.tindplayer.activity.FriendListActivity;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.PositionAPIModel;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.RecResponse;

/**
 * Created by Kayrnt on 21/12/14.
 */
public class MockTinderAPI extends TinderAPI {

    public List<Profile> screenshotProfiles() {
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("1", "Abby", "1990-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/cheriana/128.jpg"));
        profileList.add(new Profile("2", "Mindy", "1995-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/tawshmcd/128.jpg"));
        profileList.add(new Profile("3", "Sarah", "1990-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/kari_lane/128.jpg"));
        profileList.add(new Profile("4", "Kelly", "1993-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/chicatwitt/128.jpg"));
        profileList.add(new Profile("5", "Kim", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/bgiardelli/128.jpg"));
        profileList.add(new Profile("6", "Erin", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/meecasso/128.jpg"));
        profileList.add(new Profile("7", "Jennifer", "1990-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/krystalfister/128.jpg"));
        profileList.add(new Profile("8", "Tara", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/uxpiper/128.jpg"));
        profileList.add(new Profile("9", "Jessica", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/cansudelitay/128.jpg"));
        profileList.add(new Profile("10", "Jessica", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/cansudelitay/128.jpg"));
        profileList.add(new Profile("11", "Jessica", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/cansudelitay/128.jpg"));
        profileList.add(new Profile("12", "Jessica", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/cansudelitay/128.jpg"));
        profileList.add(new Profile("13", "Jessica", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/cansudelitay/128.jpg"));
        profileList.add(new Profile("14", "Jessica", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/cansudelitay/128.jpg"));
        profileList.add(new Profile("15", "Jessica", "1992-01-01",
                "https://s3.amazonaws.com/uifaces/faces/twitter/cansudelitay/128.jpg"));
        return profileList;
    }

    @Override
    public void requestUsers() {

    }

    @Override
    public void auth(Activity activity) {
        auth(activity, 3);
    }

    @Override
    public void auth(Activity activity, int retryRemaining) {
        token = "";
        tinderId = "";
        sessionManager.addTinderToken(token);
        mEditor.putString("tinder_id", tinderId);
        mEditor.apply();
        goProfileList(activity);
    }

    @Override
    public void likeProfileImpl(Profile profile, boolean shouldLike) {
        if (shouldLike){
            Log.i("LIKE", "profile " + profile.getId() + " has been liked");
        }
        else {
            Log.i("LIKE", "profile " + profile.getId() + " has been passed");
        }
    }

    @Override
    public void getProfiles(final ProfileListFragment fragment) {
        List<Profile> profileList = screenshotProfiles();
        for(Profile profile : profileList) addProfile(profile);
        fragment.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.updateListUI();
            }
        });
    }

    @Override
    public void getFriends(FriendListActivity activity) {

    }

    @Override
    public void getProfile(ProfileDetailFragment fragment, String userId) {

    }

    @Override
    public void getProfiles(final ProfileListFragment fragment, Response.Listener<RecResponse>
            listener, Response.ErrorListener errorListener) {
        RecResponse response = new RecResponse();
        response.profiles = screenshotProfiles();
        response.status = 200;
        listener.onResponse(response);
    }

    @Override
    public void updatePosition(Context context, PositionAPIModel position) {
        Toast.makeText(context, "MOCK updated position : lon " + position.getLon() + " lat : " +
                        position
                        .getLat(),
                Toast.LENGTH_SHORT).show();
    }
}
