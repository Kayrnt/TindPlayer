package fr.kayrnt.tindplayer.client;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.api.APIErrorListener;
import fr.kayrnt.tindplayer.api.auth.AuthenticationAPIListener;
import fr.kayrnt.tindplayer.api.like.LikeAPIErrorListener;
import fr.kayrnt.tindplayer.api.like.LikeAPIListener;
import fr.kayrnt.tindplayer.api.position.PositionAPIErrorListener;
import fr.kayrnt.tindplayer.api.position.PositionAPIListener;
import fr.kayrnt.tindplayer.api.profile.ProfileAPIErrorListener;
import fr.kayrnt.tindplayer.api.profile.ProfileAPIListener;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.AuthAPIModel;
import fr.kayrnt.tindplayer.model.PositionAPIModel;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.RecResponse;
import fr.kayrnt.tindplayer.utils.GsonRequest;

/**
 * Created by Kayrnt on 21/12/14.
 */
public class RealTinderAPI extends TinderAPI {

    public String ACCEPT = "*/*";
    public String ACCEPT_LANGUAGE = "en;q=1, fr;q=0.9, de;q=0.8, zh-Hans;q=0.7, zh-Hant;q=0.6, ja;q=0.5";
    public String API_URL = "https://api.gotinder.com";
    public String APP_VERSION = "371";
    public String CONNECTION = "keep-alive";
    public String CONTENT_TYPE = "application/json; charset=utf-8";
    public String OS_VERSION = "90000000001";
    public String PLATFORM = "android";
    public String USER_AGENT = "Tinder/4.6.1 (iPhone; iOS 9.0.1; Scale/2.00)";

    @Override
    public void requestUsers() {

    }

    public void auth(Activity activity) {
        String url = API_URL + "/auth";
        HashMap<String, String> map = new HashMap<String, String>();
        sessionManager = MyApplication.session();
        String fbId = sessionManager.getUserDetails().get("fb_id");
        String fbAuthToken = sessionManager.getUserDetails().get("fb_auth_token");
        Log.i("Tinder API", "auth... id : "+fbId+ " token "+fbAuthToken);
        if (fbAuthToken != null) {
            map.put("facebook_id", fbId);
            map.put("facebook_token", fbAuthToken);
            String body = new JSONObject(map).toString();
            GsonRequest<AuthAPIModel> request =
                    new GsonRequest<AuthAPIModel>(Request.Method.POST, url,
                            AuthAPIModel.class, getAuthHeaders(false), body,
                            new AuthenticationAPIListener(this, activity),
                            new APIErrorListener(this));
            MyApplication.getInstance().withSessionManager(request);
        }
    }

    public HashMap<String, String> getAuthHeaders(boolean loggedIn) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Accept-Language", ACCEPT_LANGUAGE);
        map.put("app-version", APP_VERSION);
        map.put("User-Agent", USER_AGENT);
        map.put("os_version", OS_VERSION);
        map.put("Accept", ACCEPT);
        map.put("platform", PLATFORM);
        map.put("Connection", CONNECTION);
        if (loggedIn) {
            map.put("X-Auth-Token", sessionManager.getTinderToken());
            map.put("Authorization", "Token token=\"" + sessionManager.getTinderToken() + "\"");
        }
        if (!loggedIn)
            map.put("Content-type", CONTENT_TYPE);
        return map;
    }

    @Override
    public void likeProfileImpl(Profile profile, boolean shouldLike) {
        String likeOrPassAPI = shouldLike ? "/like/" : "/pass/";
        GsonRequest<JSONObject> request =
                new GsonRequest<JSONObject>(Request.Method.GET,
                        API_URL + likeOrPassAPI +
                                profile.getId(), JSONObject.class, getAuthHeaders(true), null,
                        new LikeAPIListener(this, profile),
                        new LikeAPIErrorListener(this, profile, shouldLike));
        MyApplication.getInstance().withSessionManager(request);
    }

    public void getProfiles(ProfileListFragment fragment, Response.Listener<RecResponse>
            listener, Response.ErrorListener errorListener) {
        HashMap<String, String> authHeaders = getAuthHeaders(true);
        GsonRequest<RecResponse> request =
                new GsonRequest<RecResponse>(
                        Request.Method.GET,
                        API_URL + "/user/recs",
                        RecResponse.class,
                        authHeaders, null, listener,
                        errorListener);
        MyApplication.getInstance().withSessionManager(request);
    }

    @Override
    public void getProfiles(ProfileListFragment fragment) {
        getProfiles(fragment, new ProfileAPIListener(this, fragment),
                new ProfileAPIErrorListener(this, fragment));
    }

    @Override
    public void updatePosition(Context context, PositionAPIModel position) {
        HashMap<String, String> authHeaders = getAuthHeaders(true);
        GsonRequest<JSONObject> request =
                new GsonRequest<JSONObject>(
                        Request.Method.GET,
                        API_URL + "/user/ping",
                        JSONObject.class,
                        authHeaders, null,
                        new PositionAPIListener(context, position),
                        new PositionAPIErrorListener(this, context, position));
        MyApplication.getInstance().withSessionManager(request);
    }
}