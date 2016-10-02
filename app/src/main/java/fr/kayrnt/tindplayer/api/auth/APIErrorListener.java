package fr.kayrnt.tindplayer.api.auth;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.api.all.ProfileAllAPIListener;
import fr.kayrnt.tindplayer.client.TinderAPI;


/**
 * Created by Kayrnt on 03/12/14.
 */
public class APIErrorListener
        implements Response.ErrorListener {
    private final TinderAPI tinderAPI;
    private final Activity activity;
    private int retryRemaining;

    public APIErrorListener(TinderAPI tinderAPI, Activity activity, int retryRemaining) {
        this.tinderAPI = tinderAPI;
        this.activity = activity;
        this.retryRemaining = retryRemaining;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("Auth API Listener", "Error : " + error.getMessage());
        if (retryRemaining > 0) {
            tinderAPI.auth(activity, retryRemaining - 1);
        } else {
            Log.i("APIErrorListener", "error : " + error.getMessage());
            this.tinderAPI.authInProgress = false;
            Context context = activity == null ? MyApplication.getInstance() : activity;
            Toast.makeText(context, "Tinder login error, please retry (" + error.getMessage() + ")", Toast.LENGTH_SHORT).show();
            this.tinderAPI.sessionManager.logoutUser(activity, false);
        }
    }

}
