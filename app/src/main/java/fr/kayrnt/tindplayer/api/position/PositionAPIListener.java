package fr.kayrnt.tindplayer.api.position;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.PositionAPIModel;
import fr.kayrnt.tindplayer.model.PositionResponseAPIModel;

/**
 * by Kayrnt
 * 06/12/14 20:24
 */

public class PositionAPIListener
        implements Response.Listener<PositionResponseAPIModel> {

    private final Context context;
    private final PositionAPIModel position;

    public PositionAPIListener(Context context, PositionAPIModel position) {
        this.context = context;
        this.position = position;
    }

    @Override
    public void onResponse(PositionResponseAPIModel jsonObject) {
        Log.i("Position API Listener", "json : " + jsonObject);
        String error = jsonObject.getError();
        String successOrError = error == null? "OK" : error;
            Toast.makeText(context, "position updated (" + successOrError + ")",
    //        Toast.makeText(context, "updated position : lon " + position.getLon() + " lat : " + position.getLat(),
                    Toast.LENGTH_SHORT).show();
    }
}