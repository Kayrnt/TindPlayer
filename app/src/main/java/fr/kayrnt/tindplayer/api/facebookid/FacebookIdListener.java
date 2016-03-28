package fr.kayrnt.tindplayer.api.facebookid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;

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
public class FacebookIdListener implements Response.Listener<JSONObject>, Response.ErrorListener {

    FacebookWebViewActivity activity;
    private final String token;

    public FacebookIdListener(FacebookWebViewActivity activity, String token) {
        this.activity = activity;
        this.token = token;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {

        try {

            final String fbId = jsonObject.getString("id");

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

            // set title
            alertDialogBuilder.setTitle(activity.getString(R.string.dialog_facebook_new_title));
            alertDialogBuilder.setMessage(activity.getString(R.string.dialog_facebook_new_message));

            // Set an EditText view to get user input
            final EditText input = new EditText(activity);
            input.setText(activity.getString(R.string.input_default));
            alertDialogBuilder.setView(input);

            alertDialogBuilder.setPositiveButton(activity.getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String accountName = input.getText().toString();
                    TinderAPI.getInstance().account = new FacebookAccount();
                    TinderAPI.getInstance().account.setId(fbId);
                    TinderAPI.getInstance().account.setToken(token);
                    TinderAPI.getInstance().account.setName(accountName);
                    TinderAPI.getInstance().account.setCurrentAccount();
                    final FacebookAccounts accounts = FacebookAccounts.getAccounts();
                    accounts.accounts.add(TinderAPI.getInstance().account);
                    accounts.save();
                    activity.sessionManager.createLoginSession(fbId, token);
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
                    alertDialogBuilder.show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("FacebookIdListener", "failed "+ error.getMessage());
    }
}