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
        String url = "https://m.facebook.com/login" +
                ".php?skip_api_login=1&api_key=464891386855067&signed_next=1&next=https%3A%2F%2Fm.facebook.com%2Fv2.6%2Fdialog%2Foauth%3Fredirect_uri%3Dfbconnect%253A%252F%252Fsuccess%26display%3Dtouch%26state%3D%257B%25220_auth_logger_id%2522%253A%2522c932da8e-39d9-4e64-ac57-2cbe3af32c72%2522%252C%25223_method%2522%253A%2522web_view%2522%257D%26scope%3Duser_birthday%252Cuser_education_history%252Cuser_friends%252Cemail%252Cuser_likes%252Cuser_photos%252Cuser_relationship_details%252Cuser_work_history%26response_type%3Dtoken%252Csigned_request%26default_audience%3Dfriends%26return_scopes%3Dtrue%26auth_type%3Drerequest%26client_id%3D464891386855067%26ret%3Dlogin%26sdk%3Dandroid-4.12.1%26logger_id%3Dc932da8e-39d9-4e64-ac57-2cbe3af32c72&cancel_url=fbconnect%3A%2F%2Fsuccess%3Ferror%3Daccess_denied%26error_code%3D200%26error_description%3DPermissions%2Berror%26error_reason%3Duser_denied%26state%3D%257B%25220_auth_logger_id%2522%253A%2522c932da8e-39d9-4e64-ac57-2cbe3af32c72%2522%252C%25223_method%2522%253A%2522web_view%2522%257D%26e2e%3D%257B%2522init%2522%253A1470940140091%257D&display=touch&locale=en_US&logger_id=c932da8e-39d9-4e64-ac57-2cbe3af32c72&_rdr";
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
