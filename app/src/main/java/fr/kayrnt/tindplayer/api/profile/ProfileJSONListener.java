package fr.kayrnt.tindplayer.api.profile;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;

import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.RecResponse;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class ProfileJSONListener extends BaseAPIErrorListener
        implements ParsedRequestListener<RecResponse> {

    private TinderAPI tinderAPI;
    private ProfileListFragment fragment;

    public ProfileJSONListener(TinderAPI tinderAPI, ProfileListFragment fragment) {
        super(fragment.getContext());
        this.tinderAPI = tinderAPI;
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
                tinderAPI.addProfile(profile);
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

    @Override
    public void onError(ANError error) {
        Log.i("Profile API Listener", "Error : " + error.getMessage());
        if ((error.getErrorCode() == 401) &&
                (!tinderAPI.authInProgress)) {
            tinderAPI.authInProgress = true;
            tinderAPI.auth(null);
            Activity activity = fragment.getActivity();
            if(activity != null) Toast.makeText(activity, "Authenticating...", Toast.LENGTH_SHORT).show();
            fragment.getMoreProfileAndUpdateUI();
        } else fallbackErrorResponse(error);
        fragment.updateListUI();
    }
}
