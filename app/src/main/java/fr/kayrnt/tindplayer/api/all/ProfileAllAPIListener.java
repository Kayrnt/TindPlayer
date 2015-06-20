package fr.kayrnt.tindplayer.api.all;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.List;
import java.util.Random;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;
import fr.kayrnt.tindplayer.model.Profile;
import fr.kayrnt.tindplayer.model.RecResponse;


/**
 * Created by Kayrnt on 06/12/14.
 */
public class ProfileAllAPIListener
        implements Response.Listener<RecResponse> {
    TinderAPI tinderAPI;
    private ProfileListFragment fragment;
    private Random random = new Random();

    public ProfileAllAPIListener(TinderAPI tinderAPI, ProfileListFragment fragment) {
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
    }

    public void likeAll() {
        while (!tinderAPI.profiles.isEmpty()) {
            synchronized (tinderAPI.profiles) {
                Profile profile = tinderAPI.profiles.poll();
                if (profile != null) {
                    //sleep to avoid too many requests
                    try {
                        Thread.sleep(100L + random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tinderAPI.likeProfile(profile, profile.shouldLike);
                }
            }
            try {
                Thread.sleep(100L + random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i("ProfileAll", "Finished batch...");
        fragment.updateLikeCount();

        if (!fragment.stopLikeAll) new ProfileLikeAllTask(tinderAPI, fragment).execute();
        else fragment.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.updateProfileList();
            }
        });
    }

    @Override
    public void onResponse(final RecResponse recResponse) {
        Log.i("Profile All Listener", "response : " + recResponse);
        if ((recResponse != null) && (recResponse.profiles != null)) {
            synchronized (tinderAPI.profiles) {
                tinderAPI.profiles.addAll(recResponse.profiles);
            }
            likeAll();
        } else {
            //it's over !
            toast("We exhausted the target pool ! Try again later :)");
            Log.i("ProfileAll", "We exhausted the target pool ! Try again later :)");
            fragment.stopLikeAllListener.onClick(null);
            fragment.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fragment.updateListUI();
                }
            });
        }
    }

    private void toast(final String newMessage) {
        Toast.makeText(fragment.getActivity(), newMessage, Toast.LENGTH_SHORT).show();
    }

}