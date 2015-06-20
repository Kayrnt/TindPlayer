package fr.kayrnt.tindplayer.api.all;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.client.TinderAPI;


/**
 * Created by Kayrnt on 03/12/14.
 */
public class APIAllErrorListener
        implements Response.ErrorListener
{
    private final TinderAPI tinderAPI;

    public APIAllErrorListener(TinderAPI tinderAPI)
    {
        this.tinderAPI = tinderAPI;
    }

    public void onErrorResponse(VolleyError error) {
        Log.i("Profile All Listener", "error : " + error);
        this.tinderAPI.authInProgress = false;
        this.tinderAPI.session.logoutUser();
    }

}
