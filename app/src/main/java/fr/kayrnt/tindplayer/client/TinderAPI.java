package fr.kayrnt.tindplayer.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Response;

import java.util.LinkedList;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.activity.MainActivity;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.FacebookAccount;
import fr.kayrnt.tindplayer.model.ProfileHistory;
import fr.kayrnt.tindplayer.model.PositionAPIModel;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.RecResponse;
import fr.kayrnt.tindplayer.utils.SessionManager;

/**
 * Created by Kayrnt on 21/12/14.
 */
public abstract class TinderAPI implements IApi {

    protected static TinderAPI instance = null;

    public FacebookAccount account;
    public boolean authInProgress;
    public String fbId;
    public String fbAuthToken;
    public int likeCount;
    public ProfileHistory likedProfiles;
    public ProfileHistory passedProfiles;
    public ProfileHistory matchedProfiles;
    public SharedPreferences.Editor mEditor;
    public SharedPreferences mPrefs;
    public int matchCount;
    public int offerCounter;
    public final LinkedList<Profile> profiles = new LinkedList<>();
    public SessionManager session;
    public String tinderId;
    public String token;

    public void goProfileList(Activity activity) {
        authInProgress = false;
        if (activity != null) {
            Intent localIntent = new Intent(activity, MainActivity.class);
            activity.startActivity(localIntent);
            activity.finish();
        }
    }

    @Override
    public void likeProfile(Profile profile, boolean shouldLike) {
        if (shouldLike) {
            Log.i("PROFILE LIKED", profile.getId());
            addLikedProfile(profile);
        } else {
            Log.i("PROFILE PASSED", profile.getId());
            addPassedProfile(profile);
        }
        synchronized (profiles) {
            profiles.remove(profile);
        }
        likeProfileImpl(profile, shouldLike);
    }

    public void removeLikedProfiles(Profile profile) {
        synchronized (TinderAPI.getInstance().likedProfiles.profiles) {
            TinderAPI.getInstance().likedProfiles.profiles.remove(profile);
        }
    }

    public void removePassedProfiles(Profile profile) {
        synchronized (TinderAPI.getInstance().passedProfiles.profiles) {
            TinderAPI.getInstance().passedProfiles.profiles.remove(profile);
        }
    }

    public abstract void likeProfileImpl(Profile profile, boolean shouldLike);

    public void addProfile(Profile profile) {
        //I suspect GSON to fuck up with that value
        profile.shouldLike = true;
        synchronized (profiles) {
            profiles.add(profile);
        }
    }

    public void saveProfileHistory() {
        synchronized (likedProfiles.profiles) {
            mEditor.putString("liked_profiles", likedProfiles.serialize());
        }
        synchronized (passedProfiles.profiles) {
            mEditor.putString("passed_profiles", passedProfiles.serialize());
        }
        synchronized (matchedProfiles.profiles) {
            mEditor.putString("matched_profiles", matchedProfiles.serialize());
        }
        mEditor.apply();
    }

    public static void dispose() {
        instance = null;
    }

    public void initialize(Context paramContext) {
        initializeProperties();
        setupHistory();
    }

    public static TinderAPI getInstance() {
        if (instance == null) {
//            instance = new MockTinderAPI();
            instance = new RealTinderAPI();
            instance.initialize(MyApplication.getInstance().getApplicationContext());
        }
        return instance;
    }

    public void addLikedProfile(Profile profile) {
        synchronized (likedProfiles.profiles) {
            likeCount++;
            if (likedProfiles.profiles.size() >= 500)
                likedProfiles.profiles.removeLast();
            profile.liked = true;
            likedProfiles.profiles.addFirst(profile);
        }
    }

    public void addPassedProfile(Profile profile) {
        synchronized (passedProfiles.profiles) {
            if (passedProfiles.profiles.size() >= 500)
                passedProfiles.profiles.removeLast();
            profile.liked = false;
            passedProfiles.profiles.addFirst(profile);
        }
    }

    public void addMatchProfile(Profile profile) {
        synchronized (matchedProfiles.profiles) {
            likeCount++;
            if (matchedProfiles.profiles.size() >= 500)
                matchedProfiles.profiles.removeLast();
            profile.liked = true;
            matchedProfiles.profiles.addFirst(profile);
        }
    }

    public void setupHistory() {
        getLikedProfiles();
        getPassedProfiles();
        getMatchedProfiles();
    }

    public void getLikedProfiles() {
        String str = mPrefs.getString("liked_profiles", null);
        if (str != null) {
            likedProfiles = ProfileHistory.create(str);
        }
        else likedProfiles = new ProfileHistory();
    }

    public void getPassedProfiles() {
        String str = mPrefs.getString("passed_profiles", null);
        if (str != null) {
            passedProfiles = ProfileHistory.create(str);
        }
        else passedProfiles = new ProfileHistory();
    }

    public void getMatchedProfiles() {
        String str = mPrefs.getString("matched_profiles", null);
        if (str != null) {
            matchedProfiles = ProfileHistory.create(str);
        }
        else matchedProfiles = new ProfileHistory();
    }

    public void initializeProperties() {
        session = MyApplication.session();
//        email = UserEmailFetcher.getEmail(MyApplication.getInstance());
        mPrefs = MyApplication.getSharedPreferences();
        mEditor = mPrefs.edit();
        tinderId = mPrefs.getString("tinder_id", null);
        synchronized (profiles) {
            profiles.clear();
        }
        matchCount = mPrefs.getInt("match_count", 0);
        likeCount = mPrefs.getInt("like_count", 0);
        offerCounter = mPrefs.getInt("offer_counter", 0);
        authInProgress = false;
        account = FacebookAccount.getCurrentAccount();
    }

    public abstract void getProfiles(ProfileListFragment fragment, Response.Listener<RecResponse>
            listener, Response.ErrorListener errorListener);

    public abstract void updatePosition(Context context, PositionAPIModel position);
}
