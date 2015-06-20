package fr.kayrnt.tindplayer.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Map;

public class GsonRequest<T> extends JsonRequest<T> {

    private final Gson gson ;
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
                       String body,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, body, listener, errorListener);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampDeserializer());
        this.gson = gsonBuilder.create();
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.d("GsonRequest", "json => " + json);
            T result = gson.fromJson(json, clazz);
            if(result == null) Log.e("GsonRequest", "result is null (failed parsing)");
            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            Log.e("unsupported encoding", "failed parse api :", e);
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            Log.e("error json syntax", "failed parse api :", e);
            return Response.error(new ParseError(e));
        }
    }
}