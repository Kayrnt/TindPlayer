package fr.kayrnt.tindplayer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import fr.kayrnt.tindplayer.R;

import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.FacebookAccount;
import fr.kayrnt.tindplayer.model.FacebookAccounts;
import fr.kayrnt.tindplayer.utils.SessionManager;

public class FacebookWebViewActivity extends ActionBarActivity {
    public SessionManager sessionManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getString(R.string.title_facebook_connect));
        this.sessionManager = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_facebook_web_view);
        WebView localWebView = (WebView) findViewById(R.id.webview);
        localWebView.getSettings().setSaveFormData(false);
        localWebView.setWebViewClient(new FacebookWebView(this));
        localWebView.loadUrl("https://www.facebook.com/dialog/oauth?client_id=464891386855067&redirect_uri=https://www.facebook.com/connect/login_success.html&scope=basic_info,email,public_profile,user_about_me,user_activities,user_birthday,user_education_history,user_friends,user_interests,user_likes,user_location,user_photos,user_relationship_details&response_type=token");
    }

    public class FacebookWebView extends WebViewClient {

        FacebookWebViewActivity activity;

        public FacebookWebView(FacebookWebViewActivity activity) {
            this.activity = activity;
        }

        public void onPageStarted(WebView webView, String url, Bitmap favicon) {
            Log.i("TAG", url);
            if (url.contains("access_token")) {
                final String token = url.split("access_token=")[1].split("&")[0];
                Log.i("AUTH_TOKEN", token);

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

                // set title
                alertDialogBuilder.setTitle(getString(R.string.dialog_facebook_new_title));
                alertDialogBuilder.setMessage(getString(R.string.dialog_facebook_new_message));

                // Set an EditText view to get user input
                final EditText input = new EditText(activity);
                input.setText(getString(R.string.input_default));
                alertDialogBuilder.setView(input);

                alertDialogBuilder.setPositiveButton(getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String accountName = input.getText().toString();
                        TinderAPI.getInstance().account = new FacebookAccount();
                        TinderAPI.getInstance().account.setToken(token);
                        TinderAPI.getInstance().account.setName(accountName);
                        TinderAPI.getInstance().account.setCurrentAccount();
                        final FacebookAccounts accounts = FacebookAccounts.getAccounts();
                        accounts.accounts.add(TinderAPI.getInstance().account);
                        accounts.save();
                        activity.sessionManager.createLoginSession(token);
                        TinderAPI.getInstance().auth(activity);
                        activity.finish();
                    }
                });

                alertDialogBuilder.setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
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



            }
        }
    }
}
