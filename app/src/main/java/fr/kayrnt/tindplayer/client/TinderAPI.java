package fr.kayrnt.tindplayer.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.activity.FriendListActivity;
import fr.kayrnt.tindplayer.activity.MainActivity;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.FacebookAccount;
import fr.kayrnt.tindplayer.model.FriendProfile;
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

    public String tinderId;
    public String token;
    public String profilePicture;

    public boolean authInProgress;
    public int likeCount;
    public ProfileHistory likedProfiles;
    public ProfileHistory passedProfiles;
    public ProfileHistory matchedProfiles;
    public SharedPreferences.Editor mEditor;
    public SharedPreferences mPrefs;
    public int matchCount;
    public int offerCounter;
    public final LinkedList<Profile> profiles = new LinkedList<>();
    public SessionManager sessionManager;
    private List<FriendProfile> friendProfiles = new LinkedList<>();
    public Gson gson = new Gson();

    public void goProfileList(Activity activity) {
        Log.i("Tinder API", "go profile activity");
        authInProgress = false;
        if (activity != null) {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    public void goFriendList(Activity activity) {
        Log.i("Tinder API", "go friend activity");
        authInProgress = false;
        if (activity != null) {
            Intent intent = new Intent(activity, FriendListActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }

    public SessionManager getSessionManager() {
        if(sessionManager == null){
            sessionManager = MyApplication.session();
        }
        return sessionManager;
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
        likeProfileImpl(null, null, profile, shouldLike);
    }

    public void likeProfiles(List<Profile> profiles, final ProfileListFragment fragment) {
        if(!profiles.isEmpty()) {
            Profile profile = profiles.get(0);
            boolean shouldLike = profile.shouldLike;
            if (shouldLike) {
                Log.i("PROFILE LIKED", profile.getId());
                addLikedProfile(profile);
            } else {
                Log.i("PROFILE PASSED", profile.getId());
                addPassedProfile(profile);
            }
            profiles.remove(profile);
            likeProfileImpl(fragment, profiles, profile, shouldLike);
        }
        else {
            TinderAPI.getInstance().saveProfileHistory();
            Activity activity = fragment.getActivity();
            if(activity != null && !activity.isFinishing()){
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fragment.getMoreProfileAndUpdateUI();
                    }
                });
            }
        }
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

    public abstract void likeProfileImpl(ProfileListFragment fragment, List<Profile> profiles, Profile profile, boolean shouldLike);

    public void addProfile(Profile profile) {
        //I suspect GSON to fuck up with that value
        profile.shouldLike = true;
        synchronized (profiles) {
            profiles.add(profile);
        }
    }

    public void saveProfileHistory() {
        Log.i(this.getClass().getSimpleName(), "Saving history...");
        synchronized (likedProfiles.profiles) {
            likedProfiles.serialize("liked_profiles");
        }
        synchronized (passedProfiles.profiles) {
            passedProfiles.serialize("passed_profiles");
        }
        synchronized (matchedProfiles.profiles) {
            matchedProfiles.serialize("matched_profiles");
        }
    }

    public static void dispose() {
        instance = null;
    }

    public void initialize() {
        initializeProperties();
        setupHistory();
    }

    public static TinderAPI getInstance() {
        if (instance == null) {
//            instance = new MockTinderAPI();
            instance = new RealTinderAPI();
            instance.initialize();
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

    private ProfileHistory getProfileListV1(String preferenceKey) {
        String str = mPrefs.getString(preferenceKey, null);
        if (str != null) {
            return ProfileHistory.create(str);
        }
        else return new ProfileHistory();
    }

    private ProfileHistory getProfileListV2(String preferenceKey) {
        int slices = mPrefs.getInt(preferenceKey + "_slices", -1) + 1;
        if(slices > 0) {
            ProfileHistory history = new ProfileHistory();
            for (int i = 0; i < slices; i++) {
                String str = mPrefs.getString(preferenceKey + "_" + i, null);
                if (str != null) {
                    history.profiles.addAll(ProfileHistory.create(str).profiles);
                }
            }
            return history;
        }
        return null;
    }

    private ProfileHistory getProfileList(String preferenceKey) {
        ProfileHistory history = getProfileListV2(preferenceKey);
        if(history == null) {
            history = getProfileListV1(preferenceKey);
        }
        return history;
    }

    public void getLikedProfiles() {
        likedProfiles = getProfileList("liked_profiles");
    }

    public void getPassedProfiles() {
        passedProfiles = getProfileList("passed_profiles");
    }

    public void getMatchedProfiles() {
        matchedProfiles = getProfileList("matched_profiles");
    }

    public void initializeProperties() {
        sessionManager = MyApplication.session();
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

    public abstract void updatePosition(Context context, PositionAPIModel position);

    public void setFriendProfiles(List<FriendProfile> friendProfiles) {
        this.friendProfiles = friendProfiles;
    }

    public List<FriendProfile> getFriendProfiles() {
        return friendProfiles;
    }

}
