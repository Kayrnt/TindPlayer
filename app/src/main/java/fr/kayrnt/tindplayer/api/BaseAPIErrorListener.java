package fr.kayrnt.tindplayer.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.crashlytics.android.Crashlytics;

public class BaseAPIErrorListener {

    private Context context;

    public BaseAPIErrorListener(Context context) {
        this.context = context;
    }


    protected void fallbackErrorResponse(ANError error) {
        try {
            int errorCode = error.getErrorCode();
            if (errorCode == 408) {
                Toast.makeText(context, "Your request took too long to complete. Tinder might crowded or your connection may be too slow. Please retry.", Toast.LENGTH_LONG).show();
            } else if (errorCode >= 500 && errorCode < 600) {
                Toast.makeText(context, "Tinder server ran into an error. Please retry later if the issue persist.", Toast.LENGTH_LONG).show();
            } else {
                String errorMsg = "Unexpected error : " + error.getMessage();
                Log.w("Fallback error", errorMsg);
                Crashlytics.logException(error);
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ignored) {
        }
    }
}