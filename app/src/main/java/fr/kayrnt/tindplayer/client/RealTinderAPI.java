package fr.kayrnt.tindplayer.client;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.api.auth.*;
import fr.kayrnt.tindplayer.api.detail.*;
import fr.kayrnt.tindplayer.api.friends.*;
import fr.kayrnt.tindplayer.api.like.*;
import fr.kayrnt.tindplayer.api.match.*;
import fr.kayrnt.tindplayer.api.position.*;
import fr.kayrnt.tindplayer.api.profile.*;
import fr.kayrnt.tindplayer.activity.FriendListActivity;
import fr.kayrnt.tindplayer.fragment.*;
import fr.kayrnt.tindplayer.fragment.history.MatchedFragment;
import fr.kayrnt.tindplayer.model.*;

/**
 * Created by Kayrnt on 21/12/14.
 */
public class RealTinderAPI extends TinderAPI {

    private static final String ACCEPT = "application/json";
    private static final String ACCEPT_LANGUAGE = "en;q=1, fr;q=0.9, de;q=0.8, zh-Hans;q=0.7, zh-Hant;q=0.6, ja;q=0.5";
    private static final String API_URL = "https://api.gotinder.com";
    private static final String APP_VERSION = "6.9.4";
    private static final String CONNECTION = "keep-alive";
    private static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private static final String OS_VERSION = "90000000001";
    private static final String PLATFORM = "ios";
    private static final String USER_AGENT = "Tinder/7.5.3 (iPhone; iOS 10.3.2; Scale/2.00)";

    @Override
    public void requestUsers() {

    }

    public void auth(Activity activity) {
        auth(activity, 3);
    }

    public void auth(Activity activity, int retryRemaining) {
        String url = API_URL + "/v2/auth/login/facebook";
        HashMap<String, String> map = new HashMap<String, String>();
        sessionManager = MyApplication.session();
        String fbId = sessionManager.getUserDetails().get("fb_id");
        String fbAuthToken = sessionManager.getUserDetails().get("fb_auth_token");
        Log.i("Tinder API", "auth... fb id : " + fbId + "fb token " + fbAuthToken);
        if (fbAuthToken != null) {
            map.put("facebook_id", fbId);
            map.put("token", fbAuthToken);
            String body = new JSONObject(map).toString();

            AndroidNetworking.post(url)
                    .addHeaders(getAuthHeaders(false))
                    .setContentType(CONTENT_TYPE)
                    .setUserAgent(USER_AGENT)
                    .addStringBody(body)
                    .build()
                    .getAsObject(AuthAPIModel.class,
                            new AuthJSONListener(this, activity, retryRemaining));
        }
    }

    private HashMap<String, String> getAuthHeaders(boolean loggedIn) {
        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("Accept-Language", ACCEPT_LANGUAGE);
        map.put("app-version", APP_VERSION);
        map.put("User-Agent", USER_AGENT);
//        map.put("os_version", OS_VERSION);
        map.put("Accept", ACCEPT);
//        map.put("content-type", ACCEPT);
        map.put("platform", PLATFORM);
//        map.put("Connection", CONNECTION);
        if (loggedIn) {
            map.put("X-Auth-Token", sessionManager.getTinderToken());
            map.put("Authorization", "Token token=\"" + sessionManager.getTinderToken() + "\"");
        }
        if (!loggedIn)
            map.put("Content-type", CONTENT_TYPE);
        return map;
    }

    @Override
    public void likeProfileImpl(ProfileListFragment fragment, List<Profile> profiles, Profile profile, boolean shouldLike, boolean likeAll) {
        String likeOrPassAPI = shouldLike ? "/like/" : "/pass/";
        String url = API_URL + likeOrPassAPI + profile.getId();

        AndroidNetworking.get(url)
                .addHeaders(getAuthHeaders(true))
                .setUserAgent(USER_AGENT)
                .build()
                .getAsJSONObject(new LikeJSONListener(this, fragment, profiles, profile, shouldLike, likeAll));
    }

    @Override
    public void getProfiles(ProfileListFragment fragment) {
        getProfiles(new ProfileJSONListener(this, fragment));
    }

    @Override
    public void getProfiles(ParsedRequestListener<RecResponse> listener) {
        HashMap<String, String> authHeaders = getAuthHeaders(true);
        String url = API_URL + "/user/recs";
        AndroidNetworking.get(url)
                .addHeaders(authHeaders)
                .build()
                .getAsObject(RecResponse.class, listener);
    }

    @Override
    public void updatePosition(Context context, PositionAPIModel position) {
        try {
            HashMap<String, String> authHeaders = getAuthHeaders(true);
            String url = API_URL + "/user/ping";
            String body =
                    new JSONObject()
                            .put("lon", position.getLon())
                            .put("lat", position.getLat())
                            .toString();

            AndroidNetworking.post(url)
                    .addHeaders(authHeaders)
                    .setContentType(CONTENT_TYPE)
                    .setUserAgent(USER_AGENT)
                    .addStringBody(body)
                    .build()
                    .getAsObject(PositionResponseAPIModel.class,
                            new PositionJSONListener(context, this, position));

        } catch (JSONException e) {
            Toast.makeText(context, "Unable to determine position to send", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getFriends(FriendListActivity activity) {
        HashMap<String, String> authHeaders = getAuthHeaders(true);

        String url = API_URL + "/group/friends";
        AndroidNetworking.get(url)
                .addHeaders(authHeaders)
                .setUserAgent(USER_AGENT)
                .build()
                .getAsObject(FriendRequest.class, new FriendListJSONListener(this, activity));
    }

    @Override
    public void getProfile(ProfileDetailFragment fragment, String userId) {
        HashMap<String, String> authHeaders = getAuthHeaders(true);
        String url = API_URL + "/user/" + userId;
        AndroidNetworking.get(url)
                .addHeaders(authHeaders)
                .setUserAgent(USER_AGENT)
                .build()
                .getAsObject(ProfileRequest.class,
                        new ProfileDetailFetchJSONListener(this, fragment));
    }

    @Override
    public void getMatches(MatchedFragment fragment) {
        HashMap<String, String> authHeaders = getAuthHeaders(true);
        String url = API_URL + "/updates";
        AndroidNetworking.post(url)
                .addHeaders(authHeaders)
                .setContentType(CONTENT_TYPE)
                .setUserAgent(USER_AGENT)
                .build()
                .getAsObject(MatchesRequest.class,
                        new MatchesFetchJSONListener(this, fragment));
    }

}