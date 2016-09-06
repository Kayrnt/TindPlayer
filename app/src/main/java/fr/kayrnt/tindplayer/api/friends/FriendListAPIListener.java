package fr.kayrnt.tindplayer.api.friends;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.List;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.activity.FriendListActivity;
import fr.kayrnt.tindplayer.model.FriendProfile;
import fr.kayrnt.tindplayer.model.FriendRequest;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class FriendListAPIListener
        implements Response.Listener<FriendRequest> {
    TinderAPI client;
    private FriendListActivity activity;

    public FriendListAPIListener(TinderAPI tinderAPI, FriendListActivity activity) {
        this.client = tinderAPI;
        this.activity = activity;
    }

    @Override
    public void onResponse(FriendRequest friendRequest) {
        Log.i("Friend API Listener", "Recs API");
        List<FriendProfile> filteredProfiles = friendRequest.getFriendProfiles();
        if (!filteredProfiles.isEmpty()) {
            Log.i("Friend API Listener", "Added " + filteredProfiles.size() + "profiles ");
                client.setFriendProfiles(filteredProfiles);
        } else {
            if(activity != null)
                Toast.makeText(activity, "No friends found",
                    Toast.LENGTH_SHORT).show();
            Log.i("Friend API Listener", "No friends");
        }
        activity.updateListUI();
    }
}
