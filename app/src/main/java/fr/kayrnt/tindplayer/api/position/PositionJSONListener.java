package fr.kayrnt.tindplayer.api.position;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.PositionAPIModel;
import fr.kayrnt.tindplayer.model.PositionResponseAPIModel;

/**
 * by Kayrnt
 * 06/12/14 20:24
 */

public class PositionJSONListener extends BaseAPIErrorListener
        implements ParsedRequestListener<PositionResponseAPIModel> {

    private final Context context;
    private TinderAPI tinderAPI;
    private final PositionAPIModel position;

    public PositionJSONListener(Context context, TinderAPI tinderAPI, PositionAPIModel position) {
        super(context);
        this.context = context;
        this.tinderAPI = tinderAPI;
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

    @Override
    public void onError(ANError error) {
        Log.i("Like Listener", "Error : " + error.getMessage());
        if (
                (error.getErrorCode() == 401) &&
                (!this.tinderAPI.authInProgress)) {
            this.tinderAPI.authInProgress = true;
            this.tinderAPI.auth(null);
        } else if (
                (error.getErrorCode() == 429)){
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