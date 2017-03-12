package fr.kayrnt.tindplayer.api.friends;

import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import fr.kayrnt.tindplayer.activity.FriendListActivity;
import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.FriendProfile;
import fr.kayrnt.tindplayer.model.FriendRequest;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class FriendListJSONListener extends BaseAPIErrorListener
        implements ParsedRequestListener<FriendRequest> {
    private TinderAPI tinderAPI;
    private FriendListActivity activity;

    public FriendListJSONListener(TinderAPI tinderAPI, FriendListActivity activity) {
        super(activity);
        this.tinderAPI = tinderAPI;
        this.activity = activity;
    }

    @Override
    public void onResponse(FriendRequest friendRequest) {
        Log.i("Friend API Listener", "Recs API");
        List<FriendProfile> filteredProfiles = friendRequest.getFriendProfiles();
        if (!filteredProfiles.isEmpty()) {
            Log.i("Friend API Listener", "Added " + filteredProfiles.size() + "profiles ");
                tinderAPI.setFriendProfiles(filteredProfiles);
        } else {
            if(activity != null)
                Toast.makeText(activity, "No friends found",
                    Toast.LENGTH_SHORT).show();
            Log.i("Friend API Listener", "No friends");
        }
        activity.updateListUI();
    }

    @Override
    public void onError(ANError error) {
        Log.i("Profile API Listener", "Error : " + error.getMessage());
            if((error.getErrorCode() == 401) &&
                    (!this.tinderAPI.authInProgress)){
                this.tinderAPI.authInProgress = true;
                this.tinderAPI.auth(null);
                Toast.makeText(activity, "Authenticating...", Toast.LENGTH_SHORT).show();
                tinderAPI.getFriendProfiles();
            }
            else{
                String body = error.getErrorBody();
                Toast.makeText(activity, "Did you activate Tinder Social? You have to if you want to " +
                        "see people here (error http" + error.getErrorCode() + " "+ body + ")", Toast.LENGTH_LONG)
                        .show();

            }
        activity.updateListUI();
    }
}
