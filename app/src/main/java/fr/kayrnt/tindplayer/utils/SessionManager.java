package fr.kayrnt.tindplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.webkit.CookieManager;

import fr.kayrnt.tindplayer.activity.FacebookLoginActivity;
import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.FacebookAccount;
import fr.kayrnt.tindplayer.model.FacebookAccounts;

import java.util.HashMap;

public class SessionManager {
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_FB_ID = "fb_id";
    public static final String KEY_FB_TOKEN = "fb_auth_token";
    public static final String KEY_TINDER_ID = "tinder_id";
    public static final String KEY_TINDER_TOKEN = "tinder_auth_token";
    public static final String KEY_TINDER_PROFILE_PICTURE = "tinder_profile_picture";
    public Context _context;
    SharedPreferences.Editor editor;
    SharedPreferences pref;

    public SessionManager(Context context) {
        this._context = context;
        this.pref = MyApplication.getSharedPreferences();
        this.editor = this.pref.edit();
    }

    public void addTinderId(String id) {
        this.editor.putString(KEY_TINDER_ID, id);
        this.editor.apply();
    }

    public void addTinderToken(String token) {
        this.editor.putString(KEY_TINDER_TOKEN, token);
        this.editor.apply();
    }

    public void addProfilePicture(String url) {
        this.editor.putString(KEY_TINDER_PROFILE_PICTURE, url);
        this.editor.apply();
    }

    public void authTinder(Activity activity) {
        if (!hasTinderToken()) TinderAPI.getInstance().auth(activity);
    }

    public void checkLogin(Activity activity) {
        if (!isLoggedIn()) {
            Intent intent = new Intent(this._context, FacebookLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_RECEIVER_FOREGROUND);
            this._context.startActivity(intent);
            activity.finish();
        }
    }

    public void saveLoginSession(FacebookAccount account) {
        this.editor.putBoolean(IS_LOGIN, true);
        this.editor.putString(KEY_FB_ID, account.getId().toString());
        this.editor.putString(KEY_FB_TOKEN, account.getToken());
        this.editor.commit();
    }

    public String getTinderId() {
        return this.pref.getString(KEY_TINDER_ID, null);
    }

    public String getTinderToken() {
        return this.pref.getString(KEY_TINDER_TOKEN, null);
    }

    public String getTinderProfilePicture() {
        return this.pref.getString(KEY_TINDER_PROFILE_PICTURE, null);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(KEY_FB_ID, this.pref.getString(KEY_FB_ID, null));
        map.put(KEY_FB_TOKEN, this.pref.getString(KEY_FB_TOKEN, null));
        map.put(KEY_TINDER_TOKEN, this.pref.getString(KEY_TINDER_TOKEN, null));
        return map;
    }

    public boolean hasTinderToken() {
        return this.pref.getString(KEY_TINDER_TOKEN, null) != null;
    }

    public boolean isLoggedIn() {
        return this.pref.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser(Activity activity, boolean onlySwitchAccount) {
        CookieManager.getInstance().removeAllCookie();
        this.editor.clear();
        this.editor.putString("liked_profiles", TinderAPI.getInstance().likedProfiles.serialize());
        this.editor.apply();
        if (!onlySwitchAccount) FacebookAccounts.getInstance().logoutCurrentAccount();

        TinderAPI.dispose();

        if (activity != null) {
            Intent intent = new Intent(activity, FacebookLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
        }
    }
}