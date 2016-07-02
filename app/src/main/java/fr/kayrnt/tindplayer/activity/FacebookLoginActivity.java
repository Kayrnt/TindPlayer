package fr.kayrnt.tindplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.gc.materialdesign.views.ButtonRectangle;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.adapter.AccountsAdapter;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.FacebookAccount;
import fr.kayrnt.tindplayer.model.FacebookAccounts;

public class FacebookLoginActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        getSupportActionBar().setTitle(getString(R.string.title_facebook_login_activity));
        setupSpinner();
    }

    public void openFbWebView(View view) {
        startActivity(new Intent(this, FacebookWebViewActivity.class));
    }

    private void setupSpinner() {
        FacebookAccounts facebookAccounts = FacebookAccounts.getAccounts();
        if (!facebookAccounts.isEmpty()) {
            final Spinner accounts = (Spinner) findViewById(R.id.accounts);
            accounts.setAdapter(new AccountsAdapter(this, facebookAccounts.accounts));
            accounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(view != null) {
                        TinderAPI.getInstance().account = (FacebookAccount) view.getTag();
                        TinderAPI.getInstance().account.setCurrentAccount();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ButtonRectangle loginButton = (ButtonRectangle) findViewById(R.id.login);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TinderAPI.getInstance().getSessionManager().createLoginSession(
                            TinderAPI.getInstance().account.getId(),
                            TinderAPI.getInstance().account.getToken());
                    TinderAPI.getInstance().auth(FacebookLoginActivity.this);
                    FacebookLoginActivity.this.finish();
                }
            });
        } else {
            LinearLayout layout = (LinearLayout) findViewById(R.id.accounts_layout);
            layout.setVisibility(View.GONE);
        }
    }
}