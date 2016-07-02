package fr.kayrnt.tindplayer.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.toolbox.JsonObjectRequest;

import fr.kayrnt.tindplayer.MyApplication;
import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.api.facebookid.FacebookIdListener;
import fr.kayrnt.tindplayer.utils.SessionManager;

public class FacebookWebViewActivity extends AppCompatActivity {
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

                //get facebook id
                String fbUrl = "https://graph.facebook.com/me?access_token=" + token;
                FacebookIdListener facebookIdListener = new FacebookIdListener(activity, token);
                JsonObjectRequest request = new JsonObjectRequest(fbUrl, null,
                        facebookIdListener, facebookIdListener);
                MyApplication.getInstance().queue().add(request);

            }
        }
    }
}
