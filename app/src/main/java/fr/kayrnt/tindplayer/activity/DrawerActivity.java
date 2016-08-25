package fr.kayrnt.tindplayer.activity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import fr.kayrnt.tindplayer.R;
import fr.kayrnt.tindplayer.client.TinderAPI;
import fr.kayrnt.tindplayer.model.FacebookAccount;
import fr.kayrnt.tindplayer.model.FacebookAccounts;
import fr.kayrnt.tindplayer.utils.SessionManager;

/**
 * by Kayrnt
 * 14/07/16 17:22
 */
public class DrawerActivity extends AppCompatActivity {

    Drawer drawer = null;

    // Create the AccountHeader
    AccountHeader headerdrawer = null;

    protected void initDrawer() {

        headerdrawer = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_repeat_tile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        if (!currentProfile) {
                            for (FacebookAccount account : FacebookAccounts.getAccounts().accounts) {
                                if (profile.getIdentifier() == account.getProfileDrawerItem().getIdentifier()) {
                                    TinderAPI.getInstance().account = account;
                                    TinderAPI.getInstance().account.setCurrentAccount();
                                    TinderAPI.getInstance().getSessionManager().createLoginSession(
                                            TinderAPI.getInstance().account.getId(),
                                            TinderAPI.getInstance().account.getToken());
                                    TinderAPI.getInstance().auth(DrawerActivity.this);
                                    DrawerActivity.this.finish();
                                }
                            }
                        }
                        return false;
                    }
                })
                .withCurrentProfileHiddenInList(true)
                .build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerdrawer)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .withToolbar(toolbar)
                .addDrawerItems(
                        //pass your items here
                )
                .build();

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

    /*
    @Override
    public Drawable placeholder(Context ctx) {
        return super.placeholder(ctx);
    }

    @Override
    public Drawable placeholder(Context ctx, String tag) {
        return super.placeholder(ctx, tag);
    }
    */
        });

        if (TinderAPI.getInstance().account != null) {
            PrimaryDrawerItem logout = new PrimaryDrawerItem().withName("Logout")
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            TinderAPI.getInstance().getSessionManager().logoutUser();
                            return true;
                        }
                    });
            PrimaryDrawerItem addAccount = new PrimaryDrawerItem().withName("Add account")
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    TinderAPI.getInstance().getSessionManager().switchUser();
                    return true;
                }
            });
            drawer.addItem(logout);
            drawer.addItem(addAccount);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = drawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        headerdrawer.clear();
        for(FacebookAccount account: FacebookAccounts.getAccounts().accounts){
            headerdrawer.addProfiles(account.getProfileDrawerItem());
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}