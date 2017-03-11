package fr.kayrnt.tindplayer.api.position;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.PositionAPIModel;

public class PositionAPIErrorListener extends BaseAPIErrorListener
        implements Response.ErrorListener {

    TinderAPI tinderAPI;
    private final Context context;
    private final PositionAPIModel position;

    public PositionAPIErrorListener(TinderAPI tinderAPI, Context context,
                                    PositionAPIModel position) {
        super(context);
        this.tinderAPI = tinderAPI;
        this.context = context;
        this.position = position;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("Like Listener", "Error : " + error.getMessage());
        NetworkResponse networkResponse = error.networkResponse;
        if ((networkResponse != null) &&
                (networkResponse.statusCode == 401) &&
                (!this.tinderAPI.authInProgress)) {
            this.tinderAPI.authInProgress = true;
            this.tinderAPI.auth(null);
        } else if ((networkResponse != null) &&
                (networkResponse.statusCode == 429)){
            //we retry because too many requests in 3 sec
            Handler handler = new Handler();
            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                   TinderAPI.getInstance().updatePosition(context, position);
                }
            }, 3000);
        } else fallbackErrorResponse(error);
    }
}