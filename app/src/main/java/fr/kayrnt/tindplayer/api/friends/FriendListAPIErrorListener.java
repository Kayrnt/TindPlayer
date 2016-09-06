package fr.kayrnt.tindplayer.api.friends;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.activity.FriendListActivity;

public class FriendListAPIErrorListener
        implements Response.ErrorListener {

    TinderAPI tinderAPI;
    private final FriendListActivity activity;

    public FriendListAPIErrorListener(TinderAPI tinderAPI, FriendListActivity activity) {
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
                Toast.makeText(activity, "Did you activate Tinder Social? You have to if you want to " +
                        "see people here (error " + networkResponse.statusCode + ")", Toast.LENGTH_LONG)
                        .show();

            }
        }  else{
            Toast.makeText(activity, "No response from Tinder", Toast.LENGTH_LONG).show();

        }
        activity.updateListUI();
    }
}