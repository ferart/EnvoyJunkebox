package com.ferart.collaborativejunkebox.scoop.controllers.parties;

import com.ferart.collaborativejunkebox.model.Party;

/**
 * Created by ferar on 31/10/2016.
 */

public interface PartiesInTownActionsController {

    void showMarker(Party party);
    void moveMarker(Party party);
    void removeMarker(String partyUserUID);
    void addPartyToList(Party party);
}
