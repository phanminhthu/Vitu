package danazone.com.vitu;

import android.annotation.SuppressLint;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import org.androidannotations.annotations.EApplication;

import java.net.URISyntaxException;

import danazone.com.vitu.common.Common;
import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by PC on 9/27/2018.
 */

@SuppressLint("Registered")
@EApplication
public class BaseApp extends MultiDexApplication {
    private static final String TAG = BaseApp.class.getSimpleName();
    private static BaseApp sInstance = null;
    private boolean mIsInForegroundMode;
    private Socket mSocket;

    /**
     * Get instance of app
     *
     * @return app
     */

    {
        try {
            mSocket = IO.socket(Common.URL_SOCKET);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }

    public Socket disconnect() {
        return mSocket.disconnect();
    }

    public static synchronized BaseApp getInstance() {
        if (sInstance == null) {
            sInstance = new BaseApp();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        // Session Manager SharedPreferences
        SessionManager.getInstance().init(this);

        MultiDex.install(getApplicationContext());

    }

    public void activityResumed() {
        mIsInForegroundMode = true;
    }

    public void activityPaused() {
        mIsInForegroundMode = false;
    }

    public boolean isInForeground() {
        return mIsInForegroundMode;
    }
}

