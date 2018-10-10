package danazone.com.vitu.ui.init.login;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.BaseApp;
import danazone.com.vitu.R;
import danazone.com.vitu.bean.InfoUser;
import danazone.com.vitu.common.Common;
import danazone.com.vitu.service.VituService;
import danazone.com.vitu.sqlite.DBManager;
import danazone.com.vitu.ui.init.register.RegisterActivity_;
import danazone.com.vitu.ui.main.MainActivity_;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewById
    EditText mEdtPhone;
    @ViewById
    EditText mEdtPassword;
    private Socket mSocket;
    private DBManager dbManager;
    private Intent mServiceIntent;
    private VituService mSensorService;
    private Context ctx;

    public Context getCtx() {
        return ctx;
    }

    @Override
    protected void afterView() {
        getSupportActionBar().hide();
        ctx = this;
        mSensorService = new VituService(getCtx());
        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());

        if (!isMyServiceRunning(mSensorService.getClass())) {
            startService(mServiceIntent);

        }

        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();

        dbManager = new DBManager(this);
    }

    @Click({R.id.mTvLogin, R.id.mTvAccount, R.id.mTvFacebook, R.id.mTvGoogle})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvLogin:
                //Todo click mTvLogin
//                MainActivity_.intent(this).start();
                JSONObject object = new JSONObject();
                try {
                    object.put("phoneNumber", "01234567999");
                    object.put("pass", "111111");
                    object.put("species", 0);
                    object.put("type", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.emit("login", object);
                mSocket.on("login", onLogin);

                break;

            case R.id.mTvAccount:
                RegisterActivity_.intent(this).start();
                finish();
                break;

            case R.id.mTvFacebook:
                RegisterActivity_.intent(this).start();
                finish();
                break;

            case R.id.mTvGoogle:
                RegisterActivity_.intent(this).start();
                finish();
                break;
        }
    }

    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (args[0].toString().matches("true")) {
                        Toast.makeText(LoginActivity.this, "" + args[1].toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("999999999999999999999" + args[1].toString());
//                        if (SessionManager.getInstance().getKeySavePhone().equals("") && SessionManager.getInstance().getKeySavePass().equals("")) {
//                            SessionManager.getInstance().setKeySavePhone(mEdtPhone.getText().toString().trim());
//                            SessionManager.getInstance().setKeySavePass(mEdtPassWord.getText().toString().trim());
//                        }
                        //   System.out.println("11111111111111111111111111: " + homeTowm + degree);
                        JSONObject jsonObject = (JSONObject) args[1];
                        try {
                            String name = jsonObject.getString("name");
                            String phone = jsonObject.getString("phoneNumber");
                            String email = jsonObject.getString("email");
                            String gender = jsonObject.getString("sex");
                            String school = jsonObject.getString("school");
                            String birthday = jsonObject.getString("yearOfBirth");
                            String homeTowm = jsonObject.getString("homeTown");
                            String ex = jsonObject.getString("experience");
                            String type = jsonObject.getString("type");
                            String avatar = jsonObject.getString("avatar");
                            String verify = jsonObject.getString("verified");
                            String degree = jsonObject.getString("degree");
                            String coin = jsonObject.getString("coin");

                            System.out.println("1111111111111111111111111111111111111222: " + avatar);

                            InfoUser infoUser = new InfoUser();
                            infoUser.setName(name);
                            infoUser.setPhone(phone);
                            infoUser.setEmail(email);
                            infoUser.setGender(gender);
                            infoUser.setSchool(school);
                            infoUser.setBirthday(birthday);
                            infoUser.setHomeTown(homeTowm);
                            infoUser.setExperience(Integer.valueOf(ex));
                            infoUser.setType(Integer.valueOf(type));
                            infoUser.setAvatar(avatar);
                            infoUser.setVerify(Integer.valueOf(verify));
                            infoUser.setDegree(degree);
                            infoUser.setCoin(Integer.valueOf(coin));
                            dbManager.addIfoUser(infoUser);

                            MainActivity_.intent(LoginActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    | Intent.FLAG_ACTIVITY_NEW_TASK).start();
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        showAlertDialog("" + args[1].toString());
                    }
                }
            });
        }
    };

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }
}
