package fr.kayrnt.tindplayer.api.profile;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;

public class ProfileAPIErrorListener extends BaseAPIErrorListener
        implements Response.ErrorListener {

    TinderAPI tinderAPI;
    private final ProfileListFragment fragment;

    public ProfileAPIErrorListener(TinderAPI tinderAPI, ProfileListFragment fragment) {
        super(fragment.getContext());
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
            Activity activity = fragment.getActivity();
            if(activity != null) Toast.makeText(activity, "Authenticating...", Toast.LENGTH_SHORT).show();
            fragment.getMoreProfileAndUpdateUI();
        } else fallbackErrorResponse(error);
        fragment.updateListUI();
    }
}