package fr.kayrnt.tindplayer.api.like;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.List;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;


public class SuperLikeJSONListener
        implements JSONObjectRequestListener {

    private TinderAPI tinderAPI;
    private final ProfileDetailFragment fragment;
    private final Profile profile;

    public SuperLikeJSONListener(TinderAPI tinderAPI, ProfileDetailFragment fragment, Profile profile) {
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
        this.profile = profile;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        Log.i("Super Like API Listener", "json : " + jsonObject);
        if (jsonObject != null) {
            Toast toast = Toast.makeText(fragment.getActivity(), "Super liked!", Toast.LENGTH_SHORT);
            if(jsonObject.optBoolean("match", false)){
                Log.i("PROFILE MATCHED", profile.getId());
                tinderAPI.addMatchProfile(profile);
            }
        }
    }

    @Override
    public void onError(ANError error) {
        Log.i("Super Like Listener", "Error : " + error.getMessage());
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
                    TinderAPI.getInstance().superLikeProfile(fragment, profile);
                }
            }, 3000);
        }
    }

}