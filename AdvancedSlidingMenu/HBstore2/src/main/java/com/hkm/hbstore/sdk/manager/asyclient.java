package com.hkm.hbstore.sdk.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.hkm.hbstore.life.Config;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;

import java.io.IOException;

import io.realm.RealmObject;
import io.realm.exceptions.RealmException;

/**
 * Created by hesk on 2/3/15.
 */
public abstract class asyclient extends AsyncTask<Void, Void, String> {
    private String text_mc;
    protected boolean isError = false, isSystemicError = false;
    protected String errorMessage, submission_body_json, url;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    protected static String TAG = "call.api";
    protected Context ctx;
    protected HttpParams httpParams;
    private final OkHttpClient client = new OkHttpClient();
    protected callback mcallback;
    protected status _mstatus;
    protected int res_code;
    private final GsonBuilder gb = new GsonBuilder();
    private final Gson gson;

    protected GsonBuilder getGB() {
        return gb;
    }

    enum status {
        IDEL, PROCESSING, COMPLETE
    }

    protected Context getCtx() {
        return ctx;
    }

    private static final long HTTP_CACHE_SIZE = 16 * 1024 * 1024;

    public interface callback {
        public void onSuccess(final String data);

        public void onFailure(final String message, final int code, final boolean systematic);

        public void beforeStart(final asyclient task);
    }

    protected void configOkClient(OkHttpClient client) {
    }

    protected void addHeaderParam(Request.Builder request) {

    }
/*
    public static Cache createHttpClientCache(Context context) {
        try {
            File cacheDir = context.getDir("service_api_cache", Context.MODE_PRIVATE);
            return new Cache(cacheDir, HTTP_CACHE_SIZE);
        } catch (IOException e) {
            Log.e(TAG, "Couldn't create http cache because of IO problem.", e);
            return null;
        }
    }*/

    public asyclient(Context ccc, callback cb) {
        httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
        HttpConnectionParams.setSoTimeout(httpParams, 5000);
        ctx = ccc;
        mcallback = cb;
        _mstatus = status.IDEL;
        configOkClient(client);
        gson = configBuilder(gb).create();
    }

    protected Gson getGson() {
        return gson;
    }

    /**
     * GSON can parse the data.
     * Note there is a bug in GSON 2.3.1 that can cause it to StackOverflow when working with RealmObjects.
     * To work around this, use the ExclusionStrategy below or downgrade to 1.7.1
     * See more here: https://code.google.com/p/google-gson/issues/detail?id=440
     */
    protected GsonBuilder configBuilder(GsonBuilder b) {

        b.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaringClass().equals(RealmObject.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        });
        return b;
    }

    public asyclient setURL(String e) throws Exception {
        if (e == null) return this;
        url = e;
        return this;
    }

    protected void setSystemicError(String e) {
        isSystemicError = true;
        isError = false;
        errorMessage = e;
    }

    protected void setError(String e) {
        isError = true;
        isSystemicError = false;
        errorMessage = e;
    }

    abstract protected void GSONParser(final String data) throws JSONException, JsonSyntaxException, JsonIOException, JsonParseException;

    private Response makeUrlRequest() throws IOException {
        /**
         * build the custom request head
         */
        Request.Builder request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("User-Agent", Config.setting.useragent);


        addHeaderParam(request);


        //according to HB new API design and spec
        if (Config.setting.APIversion == 2)
            request.header("X-Api-Version", "2.0");

        final Response response = client.newCall(request.build()).execute();
        return response;
    }

    abstract protected void ViewConstruction();

    protected void ViewConstruction(View rootview) {
    }

    @Override
    protected String doInBackground(Void... params) {
        String body = "";
        _mstatus = status.PROCESSING;
        try {
            /**
             * operate the url request and communicate to the server.
             */
            Response r = makeUrlRequest();

            /**
             * get the header code only
             */
            res_code = r.code();


            if (res_code == 200) {
                body = r.body().string();


                /**
                 I just solved the problem. It was somewhat misleading that the cache tests where failing when I tried to use OkHttp from source.
                 The problem was quite easy and it was that other of the request methods was getting a body on the response, and it wasn't closed at the end. That explains why I saw the ".tmp" file in the cache, but still a confusing and misleading because of the fact that this request method was consuming and closing the body from the response. Its like the lock or monitor for the cache editor is global for all requests, instead of being by request. I though it wasn't when I read the code, when it used a hash for the request as a key.
                 */
                r.body().close();


            } else {
                Log.d(TAG, "error code: " + res_code);
                Log.d(TAG, "found error on curl: " + url);
                throw new Exception("server error: " + res_code);
            }


            /**
             * decode json into the internal use array list or data object
             */
            GSONParser(body);


            /**
             * develop the view construction
             */
            ViewConstruction();

            body = "complete";
        } catch (JsonIOException e) {
            setError(e.getMessage());
        } catch (JsonSyntaxException e) {
            setError(e.getMessage());
        } catch (JsonParseException e) {
            setError(e.getMessage());
        } catch (NoClassDefFoundError e) {
            setError(e.getMessage());
        } catch (RealmException e) {
            //setError("Realm related error" + e.getMessage());
            Log.d("Realm ERROR", e.getMessage());
        } catch (Exception e) {
            Log.d("work ERROR", e.getMessage());
            setError(e.getMessage());
        }
        return body;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mcallback != null) mcallback.beforeStart(this);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        _mstatus = status.COMPLETE;
        if (mcallback != null) {
            if (isError || isSystemicError) {
                mcallback.onFailure(errorMessage, res_code, isSystemicError);
            } else {
                mcallback.onSuccess(result);
            }
        }
    }
}