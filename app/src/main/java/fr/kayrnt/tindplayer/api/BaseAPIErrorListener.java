package fr.kayrnt.tindplayer.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.crashlytics.android.Crashlytics;

public class BaseAPIErrorListener {

    private Context context;

    public BaseAPIErrorListener(Context context) {
        this.context = context;
    }


    protected void fallbackErrorResponse(VolleyError error) {
        try {
            if (error instanceof NoConnectionError) {
                Toast.makeText(context, "Tinder API couldn't be reached. It might be because your connection or Tinder is down. Your device & connection should also support secured HTTPS protocol.", Toast.LENGTH_LONG).show();
            } else if (error instanceof TimeoutError) {
                Toast.makeText(context, "Your request took too long to complete. Tinder might crowded or your connection may be too slow. Please retry.", Toast.LENGTH_LONG).show();
            } else if (error instanceof ServerError) {
                Toast.makeText(context, "Tinder server ran into an error. Please retry later if the issue persist.", Toast.LENGTH_LONG).show();
            } else {
                String errorMsg = "Unexpected error : " + error.getMessage();
                Log.i("Fallback error", errorMsg);
                Crashlytics.logException(error);
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ignored) {
        }
    }
}