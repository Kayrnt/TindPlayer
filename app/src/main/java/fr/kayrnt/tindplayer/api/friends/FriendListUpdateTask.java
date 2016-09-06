package fr.kayrnt.tindplayer.api.friends;

import android.os.AsyncTask;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.activity.FriendListActivity;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class FriendListUpdateTask extends AsyncTask<Void, Void, Void> {

    private TinderAPI tinderAPI;
    private FriendListActivity activity;

    public FriendListUpdateTask(TinderAPI tinderAPI, FriendListActivity activity) {
        super();
        this.tinderAPI = tinderAPI;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.header.setText("Updating friends list...");
    }

    @Override
    protected Void doInBackground(Void... params) {

        if (tinderAPI.sessionManager.getTinderToken() != null)
            tinderAPI.getFriends(activity);

        return null;
    }

}
