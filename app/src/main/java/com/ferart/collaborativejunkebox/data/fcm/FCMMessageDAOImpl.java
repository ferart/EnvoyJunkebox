package com.ferart.collaborativejunkebox.data.fcm;

import android.util.Log;

import com.ferart.collaborativejunkebox.data.SimpleCallback;

import org.json.JSONException;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ferar on 15/10/2016.
 */

public class FCMMessageDAOImpl implements FCMMessageDAO {

    private static final String TAG=FCMMessageDAOImpl.class.getName();

    OkHttpClient okHttpClient;
    String appServerURL;
    MediaType jsonMediaType;

    @Inject
    public FCMMessageDAOImpl(OkHttpClient okHttpClient, String appServerURL, MediaType jsonMediaType) {
        this.okHttpClient = okHttpClient;
        this.appServerURL = appServerURL;
        this.jsonMediaType = jsonMediaType;
    }

    @Override
    public Response sendMessageToAppServer(String token, String message) throws IOException {
        Log.i(TAG,"sending message to the app server");
        String messageToSend="{\"token\":\""+token  +"\",\"message\":\""+message+"\"}";
        RequestBody body = RequestBody.create(jsonMediaType, messageToSend);


        Request request = new Request.Builder()
                .url(appServerURL)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }
}
