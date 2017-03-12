package fr.kayrnt.tindplayer.api.detail;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.ProfileRequest;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class ProfileDetailFetchJSONListener extends BaseAPIErrorListener
        implements ParsedRequestListener<ProfileRequest> {
    TinderAPI tinderAPI;
    private ProfileDetailFragment fragment;

    public ProfileDetailFetchJSONListener(TinderAPI tinderAPI, ProfileDetailFragment fragment) {
        super(fragment.getContext());
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    public void onResponse(ProfileRequest profileRequest) {
        Log.i("Profile API Listener", "user API");
        Profile profile = profileRequest.getProfile();
        fragment.updateListUI(profile);
    }

    @Override
    public void onError(ANError error) {
        Log.i("Profile API Listener", "Error : " + error.getMessage());
        Activity activity = fragment.getActivity();
        if ((error.getErrorCode() == 401) &&
                (!this.tinderAPI.authInProgress)) {
            this.tinderAPI.authInProgress = true;
            this.tinderAPI.auth(null);
            Toast.makeText(activity, "Authenticating...", Toast.LENGTH_SHORT).show();
        } else {
            fallbackErrorResponse(error);
        }
        //behavior to check
        if(activity != null && !activity.isFinishing()) activity.finish();
    }
}
