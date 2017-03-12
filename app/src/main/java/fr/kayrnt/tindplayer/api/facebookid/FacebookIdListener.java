package fr.kayrnt.tindplayer.api.facebookid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.activity.FacebookWebViewActivity;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.FacebookAccount;
import fr.kayrnt.tindplayer.model.FacebookAccounts;

/**
 * by Kayrnt
 * 28/03/16 01:43
 */
public class FacebookIdListener implements JSONObjectRequestListener {

    FacebookWebViewActivity activity;
    private final String token;

    public FacebookIdListener(FacebookWebViewActivity activity, String token) {
        this.activity = activity;
        this.token = token;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {

        try {

            final long fbId = jsonObject.getLong("id");
            final String name = jsonObject.getString("first_name");

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

            // set title
            alertDialogBuilder.setTitle(activity.getString(R.string.dialog_facebook_new_title));
            alertDialogBuilder.setMessage(activity.getString(R.string.dialog_facebook_new_message));

            // Set an EditText view to get user input
            final EditText input = new EditText(activity);
            input.setText(name);
            alertDialogBuilder.setView(input);

            alertDialogBuilder.setPositiveButton(activity.getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String accountName = input.getText().toString();
                    final FacebookAccounts accounts = FacebookAccounts.getInstance();
                    boolean accountAlreadyExists = false;
                    for(FacebookAccount account: accounts.accounts) {
                        if (account != null) {
                            if (account.getId() == fbId) {
                                TinderAPI.getInstance().account = account;
                                accountAlreadyExists = true;
                            }
                        }
                    }
                    if (!accountAlreadyExists) TinderAPI.getInstance().account = new FacebookAccount();
                    TinderAPI.getInstance().account.setId(fbId);
                    TinderAPI.getInstance().account.setToken(token);
                    TinderAPI.getInstance().account.setName(accountName);
                    TinderAPI.getInstance().account.saveCurrentAccount();
                    accounts.accounts.add(TinderAPI.getInstance().account);
                    accounts.save();
                    activity.sessionManager.saveLoginSession(TinderAPI.getInstance().account);
                    TinderAPI.getInstance().auth(activity);
                    activity.finish();
                }
            });

            alertDialogBuilder.setNegativeButton(activity.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    activity.finish();
                }
            });


            alertDialogBuilder.setCancelable(false);

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!activity.isFinishing()) {
                        alertDialogBuilder.show();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(ANError error) {
        String errorMessage = error.getMessage();
        Log.e("FacebookIdListener", "failed "+ errorMessage);
        Toast.makeText(activity, "Tinder auth error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }

}