package fr.kayrnt.tindplayer.api.profile;

import android.os.AsyncTask;

import fr.kayrnt.tindplayer.client.TinderAPI;


/**
 * Created by Kayrnt on 07/12/14.
 */
public class LikedProfileSaveTask extends AsyncTask<Void, Void, Void> {

    private TinderAPI tinderAPI;

    public LikedProfileSaveTask(TinderAPI tinderAPI) {
        super();
        this.tinderAPI = tinderAPI;
    }

    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }

}
