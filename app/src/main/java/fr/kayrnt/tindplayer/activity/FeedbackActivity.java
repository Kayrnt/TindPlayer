package fr.kayrnt.tindplayer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.gc.materialdesign.views.ButtonRectangle2;

import androidx.annotation.Nullable;
import fr.kayrnt.tindplayer.R;

/**
 * by Kayrnt
 * 10/07/16 18:02
 */
public class FeedbackActivity extends DrawerActivity {

    public static String POLL_1_DONE_PREF = "poll1.done";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        boolean poll1Done = prefs.getBoolean(POLL_1_DONE_PREF, false);

        final RelativeLayout poll1Layout = (RelativeLayout) findViewById(R.id.poll1_layout);
        final TextView poll1Completed = (TextView) findViewById(R.id.poll1Completed);

        if(poll1Done){
            poll1Layout.setVisibility(View.GONE);
            poll1Completed.setVisibility(View.VISIBLE);
        } else {
            final CheckBox happnSupport = (CheckBox) findViewById(R.id.happnSupport_checkbox);
            final CheckBox lovooSupport = (CheckBox) findViewById(R.id.lovooSupport_checkbox);
            final CheckBox automatedSwipper = (CheckBox) findViewById(R.id.automatedSwipper_checkbox);

            ButtonRectangle2 poll1Button = (ButtonRectangle2) findViewById(R.id.sendPoll1_button);

            poll1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomEvent event = new CustomEvent("poll1");
                    if (happnSupport.isChecked()) event.putCustomAttribute("happn support", "true");
                    if (lovooSupport.isChecked()) event.putCustomAttribute("lovoo support", "true");
                    if (automatedSwipper.isChecked()) event.putCustomAttribute("automated swipper", "true");
                    Answers.getInstance().logCustom(event);
                    Log.i(this.getClass().getSimpleName(), "Sent poll 1");
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean(POLL_1_DONE_PREF, true);
                    editor.apply();
                    poll1Layout.setVisibility(View.GONE);
                    poll1Completed.setVisibility(View.VISIBLE);
                }
            });
        }

        ButtonRectangle2 contactButton = (ButtonRectangle2) findViewById(R.id.contact_button);

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback about TindPlayer");
                intent.setData(Uri.parse("mailto:kayrnt@gmail.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        initDrawer();

    }

}
