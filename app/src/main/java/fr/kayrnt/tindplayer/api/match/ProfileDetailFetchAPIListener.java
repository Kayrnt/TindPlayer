package fr.kayrnt.tindplayer.api.match;

import android.util.Log;

import com.android.volley.Response;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
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
