package fr.kayrnt.tindplayer.api.like;

import android.os.Handler;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.List;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;


/**
 * Created by Kayrnt on 06/12/14.
 */

public class LikeJSONListener
        implements JSONObjectRequestListener {

    private TinderAPI tinderAPI;
    private final ProfileListFragment fragment;
    private final List<Profile> profiles;
    private final Profile profile;
    private boolean liked;
    private final boolean likeAll;

    public LikeJSONListener(TinderAPI tinderAPI, ProfileListFragment fragment, List<Profile> profiles, Profile profile, boolean liked, boolean likeAll) {
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
        this.profiles = profiles;
        this.profile = profile;
        this.liked = liked;
        this.likeAll = likeAll;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        Log.i("Like API Listener", "json : " + jsonObject);
        if(likeAll) fragment.updateLikeAllCount();
        if (jsonObject != null) {
            if(jsonObject.optBoolean("match", false)){
                Log.i("PROFILE MATCHED", this.profile.getId());
                tinderAPI.addMatchProfile(profile);
            }
        }
        if(profiles != null && fragment != null)
            tinderAPI.likeProfiles(profiles, fragment, likeAll);
    }

    @Override
    public void onError(ANError error) {
        Log.i("Like Listener", "Error : " + error.getMessage());
        if (
                (error.getErrorCode() == 401) &&
                (!this.tinderAPI.authInProgress)) {
            this.tinderAPI.authInProgress = true;
            this.tinderAPI.auth(null);
        } else if (
                (error.getErrorCode() == 429)){
            //we retry because too many requests in 3 sec
            Handler handler = new Handler();
            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                    TinderAPI.getInstance().likeProfileImpl(fragment, profiles, profile, liked, likeAll);
                }
            }, 3000);
        }
    }

}