package fr.kayrnt.tindplayer.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.client.TinderAPI;


/**
 * Created by Kayrnt on 03/12/14.
 */
public class APIErrorListener
        implements Response.ErrorListener
{
    private final TinderAPI tinderAPI;

    public APIErrorListener(TinderAPI tinderAPI)
    {
        this.tinderAPI = tinderAPI;
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.tinderAPI.authInProgress = false;
        this.tinderAPI.sessionManager.logoutUser();
    }

}
