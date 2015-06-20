package fr.kayrnt.tindplayer.api.like;

import android.util.Log;

import com.android.volley.Response;

import org.json.JSONObject;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.Profile;


/**
 * Created by Kayrnt on 06/12/14.
 */

public class LikeAPIListener
        implements Response.Listener<JSONObject> {

    private TinderAPI tinderAPI;
    private final boolean liked;
    private final Profile profile;

    public LikeAPIListener(TinderAPI tinderAPI, boolean liked, Profile profile) {
        this.tinderAPI = tinderAPI;
        this.liked = liked;
        this.profile = profile;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        Log.i("Like API Listener", "json : " + jsonObject);
        if(jsonObject.optBoolean("match", false)){
            Log.i("PROFILE MATCHED", this.profile.getId());
            tinderAPI.addMatchProfile(profile);
        }
    }
}