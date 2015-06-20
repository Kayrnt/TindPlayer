package fr.kayrnt.tindplayer.api.position;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.PositionAPIModel;

/**
 * by Kayrnt
 * 06/12/14 20:24
 */

public class PositionAPIListener
        implements Response.Listener<JSONObject> {

    private final Context context;
    private final PositionAPIModel position;

    public PositionAPIListener(Context context, PositionAPIModel position) {
        this.context = context;
        this.position = position;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        Log.i("Like API Listener", "json : " + jsonObject);
            Toast.makeText(context, "position successfully updated",
    //        Toast.makeText(context, "updated position : lon " + position.getLon() + " lat : " + position.getLat(),
                    Toast.LENGTH_SHORT).show();
    }
}