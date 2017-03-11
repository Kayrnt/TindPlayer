package fr.kayrnt.tindplayer.api.friends;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.activity.FriendListActivity;

public class FriendListAPIErrorListener extends BaseAPIErrorListener
        implements Response.ErrorListener {

    TinderAPI tinderAPI;
    private final FriendListActivity activity;

    public FriendListAPIErrorListener(TinderAPI tinderAPI, FriendListActivity activity) {
        super(activity);
        this.tinderAPI = tinderAPI;
        this.activity = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("Profile API Listener", "Error : " + error.getMessage());
        NetworkResponse networkResponse = error.networkResponse;
        if (networkResponse != null) {
            if((networkResponse.statusCode == 401) &&
                    (!this.tinderAPI.authInProgress)){
                this.tinderAPI.authInProgress = true;
                this.tinderAPI.auth(null);
                Toast.makeText(activity, "Authenticating...", Toast.LENGTH_SHORT).show();
                tinderAPI.getFriendProfiles();
            }
            else{
                String body = new String(networkResponse.data);
                Toast.makeText(activity, "Did you activate Tinder Social? You have to if you want to " +
                        "see people here (error http" + networkResponse.statusCode + " "+ body + ")", Toast.LENGTH_LONG)
                        .show();

            }
        }  else{
            fallbackErrorResponse(error);
        }
        activity.updateListUI();
    }
}