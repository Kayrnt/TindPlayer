package fr.kayrnt.tindplayer.api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.client.TinderAPI;


/**
 * Created by Kayrnt on 03/12/14.
 */
public class APIErrorListener
        implements Response.ErrorListener
{
    private final TinderAPI tinderAPI;
    private final Activity activity;

    public APIErrorListener(TinderAPI tinderAPI, Activity activity)
    {
        this.tinderAPI = tinderAPI;
        this.activity = activity;
    }

    public void onErrorResponse(VolleyError volleyError) {
        Log.i("APIErrorListener", "error : "+volleyError.getMessage());
        this.tinderAPI.authInProgress = false;
        Context context = activity == null ? MyApplication.getInstance() : activity;
        Toast.makeText(context, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
        this.tinderAPI.sessionManager.logoutUser(activity, false);

    }

}
