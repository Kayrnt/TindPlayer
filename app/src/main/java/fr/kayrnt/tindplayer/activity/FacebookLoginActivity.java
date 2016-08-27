package fr.kayrnt.tindplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.kayrnt.tindplayer.R;

public class FacebookLoginActivity extends DrawerActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        initDrawer();
        getSupportActionBar().setTitle(getString(R.string.title_facebook_login_activity));
    }

    public void openFbWebView(View view) {
        startActivity(new Intent(this, FacebookWebViewActivity.class));
    }
}