package com.ferart.collaborativejunkebox.data.preferences;

/**
 * Created by ferar on 15/10/2016.
 */

public interface AccessPreferencesDAO {
    void clear();
    void remove(String key);
    void putString(String key, String value);
    String getString(String key, String defaultValue);
}
