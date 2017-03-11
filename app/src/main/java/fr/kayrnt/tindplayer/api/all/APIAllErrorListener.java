package fr.kayrnt.tindplayer.api.all;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import fr.kayrnt.tindplayer.api.BaseAPIErrorListener;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.fragment.ProfileListFragment;


/**
 * Created by Kayrnt on 03/12/14.
 */
public class APIAllErrorListener extends BaseAPIErrorListener
        implements Response.ErrorListener
{
    TinderAPI tinderAPI;
    private final ProfileListFragment fragment;

    public APIAllErrorListener(TinderAPI tinderAPI, ProfileListFragment fragment) {
        super(fragment.getContext());
        this.tinderAPI = tinderAPI;
        this.fragment = fragment;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("Profile API Listener", "Error : " + error.getMessage());
        NetworkResponse networkResponse = error.networkResponse;
        if ((networkResponse != null) &&
                (networkResponse.statusCode == 401) &&
                (!this.tinderAPI.authInProgress)) {
            this.tinderAPI.authInProgress = true;
            this.tinderAPI.auth(null);
            Toast.makeText(fragment.getActivity(), "Authenticating...",
                    Toast.LENGTH_SHORT).show();
            new ProfileAllAPIListener(tinderAPI, fragment).likeAll();

        } else if ((networkResponse != null) &&
        (networkResponse.statusCode == 429)){
            //we retry because too many requests in 3 sec
            Handler handler = new Handler();
            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                    new ProfileAllAPIListener(tinderAPI, fragment).likeAll();
                }
            }, 3000);
        } else {
            fallbackErrorResponse(error);
        }
        fragment.updateListUI();
    }

}
