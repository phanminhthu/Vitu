package danazone.com.vitu;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PC on 9/27/2018.
 */

public class SessionManager {
    private static final String SHARED_PREFERENCES_NAME = "danazone.com.vitu";

    private static final String KEY_SAVE_ID_SOCKET = "key_save_socket";
    private static final String KEY_SAVE_PHONE = "key_save_phone";
    private static final String KEY_SAVE_PASS = "key_save_pass";
    private static SessionManager sInstance;

    private SharedPreferences sharedPref;

    public synchronized static SessionManager getInstance() {
        if (sInstance == null) {
            sInstance = new SessionManager();
        }
        return sInstance;
    }

    private SessionManager() {
        // no instance
    }

    public void init(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public String getKeySaveIdSocket() {
        return sharedPref.getString(KEY_SAVE_ID_SOCKET, "");
    }

    public void setKeySaveIdSocket(String token) {
        sharedPref.edit().putString(KEY_SAVE_ID_SOCKET, token).apply();
    }

    public String getKeySavePhone() {
        return sharedPref.getString(KEY_SAVE_PHONE, "");
    }

    public void setKeySavePhone(String token) {
        sharedPref.edit().putString(KEY_SAVE_PHONE, token).apply();
    }

    public String getKeySavePass() {
        return sharedPref.getString(KEY_SAVE_PASS, "");
    }

    public void setKeySavePass(String token) {
        sharedPref.edit().putString(KEY_SAVE_PASS, token).apply();
    }
}


