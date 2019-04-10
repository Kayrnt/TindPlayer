package fr.kayrnt.tindplayer.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.api.facebookid.FacebookIdListener;
import fr.kayrnt.tindplayer.utils.SessionManager;

public class FacebookWebViewActivity extends DrawerActivity {
    public SessionManager sessionManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_web_view);
        this.sessionManager = new SessionManager(getApplicationContext());
        WebView localWebView = (WebView) findViewById(R.id.webview);
        WebSettings localWebSettings = localWebView.getSettings();
        localWebSettings.setSaveFormData(false);
        localWebSettings.setJavaScriptEnabled(true);
        Log.i("USER AGENT", localWebSettings.getUserAgentString());
        localWebView.setWebViewClient(new FacebookWebView(this));
        String url = "https://www.facebook.com/v2.6/dialog/oauth?redirect_uri=fb464891386855067%3A%2F%2Fauthorize%2F&display=touch&state=%7B%22challenge%22%3A%22IUUkEUqIGud332lfu%252BMJhxL4Wlc%253D%22%2C%220_auth_logger_id%22%3A%2230F06532-A1B9-4B10-BB28-B29956C71AB1%22%2C%22com.facebook.sdk_client_state%22%3Atrue%2C%223_method%22%3A%22sfvc_auth%22%7D&scope=user_birthday%2Cuser_photos%2Cuser_education_history%2Cemail%2Cuser_relationship_details%2Cuser_friends%2Cuser_work_history%2Cuser_likes&response_type=token%2Csigned_request&default_audience=friends&return_scopes=true&auth_type=rerequest&client_id=464891386855067&ret=login&sdk=ios&logger_id=30F06532-A1B9-4B10-BB28-B29956C71AB1&ext=1470840777&hash=AeZqkIcf-NEW6vBd";
        localWebView.loadUrl(url);
        initDrawer();
        getSupportActionBar().setTitle(getString(R.string.title_facebook_connect));
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
                Toast.makeText(activity, "FB login success... going to auth Tinder", Toast.LENGTH_SHORT).show();
                //get facebook id
                String fbUrl = "https://graph.facebook.com/me?access_token=" + token;
                AndroidNetworking.get(fbUrl)
                        .build()
                        .getAsJSONObject(new FacebookIdListener(activity, token));
            }
        }


    }
}
