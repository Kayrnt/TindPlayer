package fr.kayrnt.tindplayer.api.profile;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.ArrayList;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.RecResponse;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class ProfileAPIListener
        implements Response.Listener<RecResponse> {
    TinderAPI client;
    private ProfileListFragment fragment;

    public ProfileAPIListener(TinderAPI tinderAPI, ProfileListFragment fragment) {
        this.client = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    public void onResponse(RecResponse recResponse) {
        Log.i("Profile API Listener", "Recs API");
        ArrayList<Profile> filteredProfiles = new ArrayList<>();
        if((recResponse != null) && (recResponse.profiles != null)) {
            for (Profile profile : recResponse.profiles) {
                String id = profile.getId();
                if (id != null && !id.contains("tinder_rate_limited_id"))
                    filteredProfiles.add(profile);
            }
        }
        if (!filteredProfiles.isEmpty()) {
            Log.i("Profile API Listener", "Added " + filteredProfiles.size() + "profiles ");
            for (Profile profile : filteredProfiles) {
                client.addProfile(profile);
            }
        } else {
            Activity activity = fragment.getActivity();
            if(activity != null)
                Toast.makeText(activity, "No targets found, try again later or exhausted likes",
                    Toast.LENGTH_SHORT).show();
            Log.i("Profile API Listener", "No profiles");
        }
        fragment.updateListUI();
    }
}
