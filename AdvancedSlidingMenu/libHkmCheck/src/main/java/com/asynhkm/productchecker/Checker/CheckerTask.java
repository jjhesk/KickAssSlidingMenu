package com.asynhkm.productchecker.Checker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.asynhkm.productchecker.Util.Tool;
import com.asynhkm.productchecker.schema.DataProductVersion;
import com.asynhkm.productchecker.schema.ReturnResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by Hesk on 30/12/2014.
 */
public class CheckerTask extends AsyncTask<Void, Void, DataProductVersion> {
    public static final String TAG = "Redeem Class";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private HttpParams httpParams;
    private Context c;
    private OkHttpClient client = new OkHttpClient();
    private CheckerCB checker_cb;
    private String
            licenseKey = "",
            productKey = "",
            mac_id = "",
            request_url = "";
    private param.request_status status_req;
    private SharedPreferences mSP;

    public class requestRegister {
        public String domain, product_key;

        public requestRegister() {
        }
    }

    public class requestCheck {
        public String domain, key;

        public requestCheck() {
        }
    }

    public CheckerTask(Context cctxx, CheckerCB cb, SharedPreferences SP) {
        httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
        HttpConnectionParams.setSoTimeout(httpParams, 5000);
        c = cctxx;
        checker_cb = cb;
        mSP = SP;
    }

    public boolean isSuccess(String str) throws JSONException {
        final JSONObject js = new JSONObject(str);
        final boolean t = js.getBoolean("success");
        return t;
    }

    private DataProductVersion return_result(String resultString) throws Exception {
        final GsonBuilder gsonb = new GsonBuilder();
        DataProductVersion mDataProductVersion = new DataProductVersion();
        ReturnResult rt;
        try {
            System.out.println("RESPONSE: " + resultString);
            if (isSuccess(resultString)) {
                final JSONObject Jr = new JSONObject(resultString);
                JSONObject data = Jr.getJSONObject("license_detail");
                mDataProductVersion = gsonb.create().fromJson(data.toString(), DataProductVersion.class);
            } else {
                ReturnResult result = gsonb.create().fromJson(resultString, ReturnResult.class);
                mDataProductVersion.setRR(result);
            }
        } catch (JsonParseException e) {
            rt = new ReturnResult(e.getMessage());
            mDataProductVersion.setRR(rt);
        } catch (JSONException e) {
            rt = new ReturnResult(e.getMessage());
            mDataProductVersion.setRR(rt);
        } catch (Exception e) {
            rt = new ReturnResult(e.getMessage());
            mDataProductVersion.setRR(rt);
        }
        return mDataProductVersion;
    }

    public CheckerTask setProductKey(String key) {
        productKey = key;
        return this;
    }

    public CheckerTask setLicenseKey(String license) {
        licenseKey = license;
        return this;
    }

    public CheckerTask setMac(String mac) {
        mac_id = mac;
        return this;
    }

    public CheckerTask setStatusRequest(param.request_status e) {
        status_req = e;
        return this;
    }

    public CheckerTask setRequestUrl(String url) {
        request_url = url;
        return this;
    }


    protected String consolidate() {
        final GsonBuilder gsonb = new GsonBuilder();
        String request_body = "";

        Gson gson = gsonb.create();
        switch (status_req) {
            case registration:
                requestRegister mrequestRegister = new requestRegister();
                mrequestRegister.domain = mac_id;
                mrequestRegister.product_key = productKey;
                request_body = gson.toJson(mrequestRegister);
                break;
            case check:
                requestCheck mrequestCheck = new requestCheck();
                mrequestCheck.domain = mac_id;
                mrequestCheck.key = licenseKey;
                request_body = gson.toJson(mrequestCheck);
                break;
        }


        return request_body;
    }


    @Override
    protected DataProductVersion doInBackground(Void... c) {
        DataProductVersion h;
        try {
            RequestBody body = RequestBody.create(JSON, consolidate());
            Request request = new Request.Builder()
                    .url(request_url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            h = return_result(res);
        } catch (NoClassDefFoundError e) {

            h = new DataProductVersion();
            h.setRR(new ReturnResult(e.getMessage()));

        } catch (IOException e) {

            Log.d("work ERROR", e.getMessage());
            h = new DataProductVersion();
            h.setRR(new ReturnResult(e.getMessage()));

        } catch (Exception e) {

            Log.d("work ERROR", e.getMessage());
            h = new DataProductVersion();
            h.setRR(new ReturnResult(e.getMessage()));

        }
        return h;
    }

    @Override
    protected void onPostExecute(DataProductVersion result) {
        Log.d(TAG, "onPostExecute result == " + result.toString());
        super.onPostExecute(result);
        if (result.isError()) {
            checker_cb.tr_fail(result);
        } else {
          /*  if (status_req == param.request_status.registration) {
                Tool.trace(c, "new demo license is issued");
            }*/
            mSP.edit().putString(param.SAVE_ACH, result.getLicenseKey()).apply();
            checker_cb.tr_success(result, status_req);
        }
    }

    @Override
    protected void onPreExecute() {
        if (Tool.isOnline(c)) {
            super.onPreExecute();
            // assertEquals(0, progressBar.getProgress());
        } else {
            //Tool.trace(c, R.string.warning_online_alert);
        }
    }


}
