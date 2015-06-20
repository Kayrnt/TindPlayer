package fr.kayrnt.tindplayer.api.auth;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Response;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.AuthAPIModel;


/**
 * Created by Kayrnt on 03/12/14.
 */
public class AuthenticationAPIListener implements Response.Listener<AuthAPIModel> {

    TinderAPI tinderAPI;
    Activity activity;

    public AuthenticationAPIListener(TinderAPI tinderAPI, Activity activity) {
        this.tinderAPI = tinderAPI;
        this.activity = activity;
    }

    @Override
    public void onResponse(AuthAPIModel authAPIModel) {
        Log.i("Auth API Listener", "api : "+ authAPIModel);
        this.tinderAPI.token = authAPIModel.getToken();
        this.tinderAPI.tinderId = authAPIModel.getUser().getId();
        this.tinderAPI.session.addTinderToken(this.tinderAPI.token);
        tinderAPI.mEditor.putString("tinder_id", this.tinderAPI.tinderId);
        tinderAPI.mEditor.apply();
        this.tinderAPI.goProfileList(activity);
    }

}