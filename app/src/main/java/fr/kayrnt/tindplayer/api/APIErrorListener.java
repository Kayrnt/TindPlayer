package fr.kayrnt.tindplayer.api;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

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
        Log.i("APIErrorListener", "error : "+volleyError.getMessage());
        this.tinderAPI.authInProgress = false;
        Toast.makeText(this.tinderAPI.sessionManager._context, volleyError.getMessage(), Toast.LENGTH_SHORT)
                .show();
        this.tinderAPI.sessionManager.logoutUser();

    }

}
