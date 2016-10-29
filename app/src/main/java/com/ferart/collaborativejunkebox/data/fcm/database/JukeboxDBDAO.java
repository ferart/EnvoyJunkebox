package com.ferart.collaborativejunkebox.data.fcm.database;

/**
 * Created by Envoy on 10/27/16.
 */

public interface JukeboxDBDAO {

    /**
     * JUKEBOX HOST
     * to save party data, becoming a jukebox
     * @param userUID
     * @param name
     * @param hostToken
     */
    void writeNewParty(String userUID, String name, String hostToken);

    /**
     * JUKEBOX HOST
     * update fcm token in database, only for use by a jukebox host
     * @param userUID
     * @param hostToken
     */
    void updateHostToken(String userUID, String hostToken);

    /**
     * JUKEBOX HOST
     * Deletes party
     * @param userUID
     */
    void deleteParty(String userUID);

    /**
     * follow any update to a party i belong
     * @param userUID
     */
    void followPartyUpdates(String userUID);

    void unfollowPartyUpdates(String userUID);
}
