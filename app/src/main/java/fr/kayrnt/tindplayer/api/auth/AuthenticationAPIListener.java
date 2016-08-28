package fr.kayrnt.tindplayer.api.auth;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Response;

import java.util.List;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.AuthAPIModel;
import fr.kayrnt.tindplayer.model.FacebookAccounts;
import fr.kayrnt.tindplayer.model.Photo;


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
        List<Photo> photoList = authAPIModel.getUser().getPhotos();
        if(!photoList.isEmpty()) {
            this.tinderAPI.profilePicture = photoList.get(0).getUrl();
            this.tinderAPI.sessionManager.addProfilePicture(this.tinderAPI.profilePicture);
            tinderAPI.account.setProfilePicture(this.tinderAPI.profilePicture);
            tinderAPI.account.setCurrentAccount();
        }
        this.tinderAPI.sessionManager.addTinderToken(this.tinderAPI.token);
        this.tinderAPI.sessionManager.addTinderId(this.tinderAPI.tinderId);
        FacebookAccounts.getInstance().saveWithCurrentAccount();
        this.tinderAPI.goProfileList(activity);
    }

}