package fr.kayrnt.tindplayer.api.auth;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.crashlytics.android.Crashlytics;

import java.util.List;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.AuthAPIModel;
import fr.kayrnt.tindplayer.model.FacebookAccounts;
import fr.kayrnt.tindplayer.model.Photo;

/**
 * Created by Kayrnt on 11/03/2017.
 */

public class AuthJSONListener implements ParsedRequestListener<AuthAPIModel> {

    private final TinderAPI tinderAPI;
    private final Activity activity;
    private final int retryRemaining;

    public AuthJSONListener(TinderAPI tinderAPI, Activity activity, int retryRemaining) {
        this.tinderAPI = tinderAPI;
        this.activity = activity;
        this.retryRemaining = retryRemaining;
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
            tinderAPI.account.saveCurrentAccount();
            FacebookAccounts.getInstance().saveWithCurrentAccount();
        }
        this.tinderAPI.sessionManager.addTinderToken(this.tinderAPI.token);
        this.tinderAPI.sessionManager.addTinderId(this.tinderAPI.tinderId);
        FacebookAccounts.getInstance().saveWithCurrentAccount();
        this.tinderAPI.goProfileList(activity);
    }

    @Override
    public void onError(ANError error) {
        Log.i("Auth API Listener", "Error : " + error.getMessage());
        if (retryRemaining > 0) {
            tinderAPI.auth(activity, retryRemaining - 1);
        } else {
            Crashlytics.logException(error);
            Log.i("APIErrorListener", "error : " + error.getMessage());
            this.tinderAPI.authInProgress = false;
            Context context = activity == null ? MyApplication.getInstance() : activity;
            Toast.makeText(context, "Tinder login error, please retry (" + error.getMessage() + ")", Toast.LENGTH_SHORT).show();
            this.tinderAPI.sessionManager.logoutUser(activity, false);
        }
    }

}
