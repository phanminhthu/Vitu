package danazone.com.vitu.ui.init.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.BaseApp;
import danazone.com.vitu.R;
import danazone.com.vitu.SessionManager;
import danazone.com.vitu.common.Common;
import danazone.com.vitu.ui.dialog.ClassifyDialog;
import danazone.com.vitu.ui.dialog.ExperienceDialog;
import danazone.com.vitu.ui.dialog.GenderDialog;
import danazone.com.vitu.ui.init.login.LoginActivity_;
import danazone.com.vitu.ui.init.register.avatar.RegisterDoneActivity_;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    @ViewById
    EditText mEdtName;
    @ViewById
    EditText mEdtPhone;
    @ViewById
    EditText mEdtPassword;
    @ViewById
    TextView mTvGender;
    @ViewById
    EditText mEdtBirthday;
    @ViewById
    EditText mEdtSchool;
    @ViewById
    EditText mEdtHomeTown;
    @ViewById
    TextView mTvClassify;
    @ViewById
    TextView mTvExperience;
    @ViewById
    TextView mTvBase;

    @Extra
    String mKey;
    private Socket mSocket;

    @Override
    protected void afterView() {
        getSupportActionBar().hide();
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();
        mSocket.connect();
    }

    @Click({R.id.mTvGender, R.id.mTvClassify, R.id.mTvExperience, R.id.mTvAccount, R.id.mTvRegister})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvGender:
                new GenderDialog(this, new GenderDialog.OnDialogClickListener() {
                    @Override
                    public void onMale() {
                        //Todo set value
                        // if Male => Nam (1)
                        mTvGender.setText("M1");
                    }

                    @Override
                    public void onFemale() {
                        //Todo set value
                        // if Female => Nữ (2)
                        mTvGender.setText("M2");
                    }
                }).show();
                break;

            case R.id.mTvClassify:
                new ClassifyDialog(this, new ClassifyDialog.OnDialogClickListener() {
                    @Override
                    public void onStudent() {
                        //Todo set value
                        // if student => Sinh viên (1)
                    }

                    @Override
                    public void onGraduating() {
                        //Todo set value
                        // if graduating => đã tốt nghiệp (2)
                    }
                }).show();
                break;

            case R.id.mTvExperience:
                new ExperienceDialog(this, new ExperienceDialog.OnDialogClickListener() {
                    @Override
                    public void onOneYear() {
                        //Todo set value
                        // (1)
                        mTvExperience.setText("1");
                    }

                    @Override
                    public void onThreeYears() {
                        //Todo set value
                        // (2)
                        mTvExperience.setText("2");
                    }

                    @Override
                    public void onFiveYears() {
                        //Todo set value
                        // (3)
                        mTvExperience.setText("3");
                    }

                    @Override
                    public void onThanFiveYears() {
                        //Todo set value
                        // (4)
                        mTvExperience.setText("4");
                    }
                }).show();
                break;

            case R.id.mTvAccount:
                LoginActivity_.intent(this).start();
                finish();
                break;

            case R.id.mTvRegister:
                //Todo mTvRegister click
                JSONObject object = new JSONObject();
                // String [] a = new String[]{"\"S1\"","\"S2\""};
                //System.out.println("666666666666666666666666666666666: " + Arrays.asList(a));

                try {
                    object.put("listSubjects", new JSONArray("[ \"S1\"]"));
                    object.put("experience", 1);
                    object.put("homeTown", "44");
                    object.put("name", "lllmmm");
                    object.put("pass", "111111");
                    object.put("phoneNumber", "01234567999");
                    object.put("school", "fffv");
                    object.put("sex", "M1");
                    object.put("yearOfBirth", 1993);
                    object.put("type", 1);
                    object.put("species", 0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.emit("register", object);
                mSocket.on("register", onRegister);
                break;
        }
    }

    private Emitter.Listener onRegister = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (args[0].toString().matches("true")) {
                        System.out.println("666666666666666666666666666667777777" + args[1].toString());
//                        MainActivity_.intent(RegisterActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                                | Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                | Intent.FLAG_ACTIVITY_NEW_TASK).start();
//                        finish();
                    } else {
                        showAlertDialog("" + args[1].toString());
                    }
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.close();
    }
}
