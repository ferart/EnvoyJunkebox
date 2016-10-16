package com.ferart.collaborativejunkebox.data.fcm;

import com.ferart.collaborativejunkebox.data.SimpleCallback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by ferar on 15/10/2016.
 */

public interface FCMMessageDAO {

    /**
     * Send a message to the app Server
     * @param token of destination client
     * @param message phrase to send
     */
    Response sendMessageToAppServer(String token, String message) throws IOException;
}
