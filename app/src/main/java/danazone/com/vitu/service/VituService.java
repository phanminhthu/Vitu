package danazone.com.vitu.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import danazone.com.vitu.BaseApp;
import danazone.com.vitu.R;
import danazone.com.vitu.bean.InfoUser;
import danazone.com.vitu.bean.Transaction;
import danazone.com.vitu.ui.main.MainActivity;
import danazone.com.vitu.ui.main.MainActivity_;
import danazone.com.vitu.ui.main.account.update.UpdateAccountActivity;
import danazone.com.vitu.ui.main.account.update.UpdateAccountActivity_;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
public class VituService extends Service {
    private NotificationCompat.Builder notBuilder;
    private Context context;
    private Socket mSocket;
    private Transaction transaction;

    public VituService(Context applicationContext) {
        super();
        context = applicationContext;
        Log.i("HERE", "here service created!");
    }

    public VituService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.notBuilder = new NotificationCompat.Builder(this);
        this.notBuilder.setAutoCancel(true);

        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        mSocket.connect();
        mSocket.on("transaction", new Emitter.Listener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void call(final Object... args) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {

                        String data = args[0].toString();
                        System.out.println("22222222222222222222222222222222222222244" + data);
                        JSONObject jsonObject = (JSONObject) args[0];
                        try {

                            transaction = new Transaction();
                            transaction.setIdTransaction("" + jsonObject.get("idTransaction"));
                            transaction.setMyClass("" + jsonObject.get("class"));
                            transaction.setSubject("" + jsonObject.get("subject"));
                            transaction.setStreetClass("" + jsonObject.get("streetClass"));
                            transaction.setCountyClass("" + jsonObject.get("countyClass"));
                            transaction.setCityClass("" + jsonObject.get("cityClass"));
                            transaction.setNumberStudent("" + jsonObject.get("numberStudent"));
                            transaction.setSchedule("" + jsonObject.get("schedule"));
                            transaction.setSchoolStudent("" + jsonObject.get("schoolStudent"));
                            transaction.setFeePerDay("" + jsonObject.get("feePerDay"));
                            transaction.setTotalFee("" + jsonObject.get("totalFee"));
                            transaction.setDateStart("" + jsonObject.get("dateStart"));
                            transaction.setNameParent("" + jsonObject.get("nameParent"));
                            transaction.setWeek("" + jsonObject.get("week"));
                            transaction.setWeek("" + jsonObject.get("nameParent"));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        notification();
                    }
                });
            }
        });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void notification() {
        Bundle bundle = new Bundle();
        Intent resultIntent = new Intent(getApplicationContext(), MainActivity_.class);
        bundle.putString("jjj", "1");
        bundle.putSerializable("kkk", transaction);
        resultIntent.putExtras(bundle);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        @SuppressLint("WrongConstant")
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Context context = getApplicationContext();
        Notification.Builder builder;
        builder = new Notification.Builder(context)
                .setContentTitle("Hellolllllllll")
                .setContentText("hahahha")
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
