package fr.kayrnt.tindplayer.api.match;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileDetailFragment;
import fr.kayrnt.tindplayer.fragment.history.MatchedFragment;

public class MatchesFetchAPIErrorListener
        implements Response.ErrorListener {

    TinderAPI tinderAPI;
    private final MatchedFragment fragment;

    public MatchesFetchAPIErrorListener(TinderAPI tinderAPI, MatchedFragment fragment) {
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("Profile API Listener", "Error : " + error.getMessage());
        NetworkResponse networkResponse = error.networkResponse;
        if ((networkResponse != null) &&
                (networkResponse.statusCode == 401) &&
                (!this.tinderAPI.authInProgress)) {
            this.tinderAPI.authInProgress = true;
            this.tinderAPI.auth(null);
            Toast.makeText(fragment.getContext(), "Authenticating...", Toast.LENGTH_SHORT).show();
        }
    }
}