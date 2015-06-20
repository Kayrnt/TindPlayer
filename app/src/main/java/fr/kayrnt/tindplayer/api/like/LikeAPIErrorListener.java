package fr.kayrnt.tindplayer.api.like;

import android.os.Handler;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.Profile;

public class LikeAPIErrorListener
        implements Response.ErrorListener {

    TinderAPI tinderAPI;
    private final Profile profile;
    private final boolean liked;

    public LikeAPIErrorListener(TinderAPI tinderAPI, Profile profile, boolean liked) {
        this.tinderAPI = tinderAPI;
        this.profile = profile;
        this.liked = liked;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("Like Listener", "Error : " + error.getMessage());
        NetworkResponse networkResponse = error.networkResponse;
        if ((networkResponse != null) &&
                (networkResponse.statusCode == 401) &&
                (!this.tinderAPI.authInProgress)) {
            this.tinderAPI.authInProgress = true;
            this.tinderAPI.auth(null);
        } else if ((networkResponse != null) &&
                (networkResponse.statusCode == 429)){
            //we retry because too many requests in 3 sec
            Handler handler = new Handler();
            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                   TinderAPI.getInstance().likeProfileImpl(profile, liked);
                }
            }, 3000);
        }
    }
}