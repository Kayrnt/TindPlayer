package fr.kayrnt.tindplayer.api.detail;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.List;

import fr.kayrnt.tindplayer.activity.FriendListActivity;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.model.FriendProfile;
import fr.kayrnt.tindplayer.model.FriendRequest;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.ProfileRequest;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class ProfileDetailFetchAPIListener
        implements Response.Listener<ProfileRequest> {
    TinderAPI client;
    private ProfileDetailFragment fragment;

    public ProfileDetailFetchAPIListener(TinderAPI tinderAPI, ProfileDetailFragment fragment) {
        this.client = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    public void onResponse(ProfileRequest profileRequest) {
        Log.i("Profile API Listener", "user API");
        Profile profile = profileRequest.getProfile();
        fragment.updateListUI(profile);
    }
}
