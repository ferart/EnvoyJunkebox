package com.ferart.collaborativejunkebox.domain.fcmtoken;

import android.text.TextUtils;

import com.ferart.collaborativejunkebox.data.fcm.FCMMessageDAO;
import com.ferart.collaborativejunkebox.data.preferences.AccessPreferencesDAO;
import com.ferart.collaborativejunkebox.domain.common.BaseInteractor;
import com.ferart.collaborativejunkebox.domain.thread.Executor;
import com.ferart.collaborativejunkebox.domain.thread.MainThread;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.Response;

/**
 * Created by ferar on 15/10/2016.
 */

public class FCMMessageInteractor extends BaseInteractor {
    private static final String TAG=FCMMessageInteractor.class.getName();
    private String message;
    private String tokenID;


    FCMMessageDAO fcmMessageDAO;

    @Inject
    public FCMMessageInteractor(Executor executor, MainThread mainThread,FCMMessageDAO fcmMessageDAO) {
        super(executor, mainThread);
        this.fcmMessageDAO=fcmMessageDAO;
    }

    @Override
    public void run() {
        if (TextUtils.isEmpty(message) && TextUtils.isEmpty(tokenID)  ){
            throw  new IllegalArgumentException("Token and Message can not be null or empty");
        }
        sendMessage();
    }

    private void sendMessage(){
        try {
            Response response=fcmMessageDAO.sendMessageToAppServer(tokenID,message);
            if (response.isSuccessful()){
                final String responseData = response.body().string();
                JSONObject json = new JSONObject(responseData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }
}
