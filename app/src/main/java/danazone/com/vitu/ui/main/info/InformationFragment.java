package danazone.com.vitu.ui.main.info;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import danazone.com.vitu.BaseApp;
import danazone.com.vitu.BaseContainerFragment;
import danazone.com.vitu.R;
import danazone.com.vitu.bean.Transaction;
import danazone.com.vitu.ui.main.info.waiting.WaitingViTuActivity_;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@EFragment(R.layout.fragment_information)
public class InformationFragment extends BaseContainerFragment {
    public static final int MY_CAMERA_REQUEST_CODE = 100;
    @FragmentArg
    Transaction mTransaction;
    @FragmentArg
    int mKey;

    @ViewById
    TextView mTvClass;
    @ViewById
    TextView mTvCaseClass;
    @ViewById
    TextView mTvAddress;
    @ViewById
    TextView mTvSchool;
    @ViewById
    TextView mTvNumStudent;
    @ViewById
    TextView mTvHomeWork;
    @ViewById
    TextView mTvDateStart;

    @ViewById
    TextView mTvTotalFee;
    @ViewById
    TextView mTvHomeTown;
    @ViewById
    TextView mTvSchedule;
    @ViewById
    TextView mTvGender;
    @ViewById
    TextView mTvSchoolTtuto;
    @ViewById
    TextView mTvVisu;
    private String idTransaction;
    private Socket mSocket;


    @Override
    protected void afterViews() {
        BaseApp app = (BaseApp) getActivity().getApplicationContext();
        mSocket = app.getSocket();

        checkPermissions();
        if (mKey == 1 & mTransaction != null) {
            idTransaction = mTransaction.getIdTransaction();
            mTvClass.setText(mTransaction.getMyClass());
            mTvCaseClass.setText(mTransaction.getSubject());
            mTvAddress.setText(mTransaction.getStreetClass() + ", " + mTransaction.getCountyClass() + ", " + mTransaction.getCityClass());
            mTvSchool.setText(mTransaction.getSchoolStudent());
            mTvHomeWork.setText(mTransaction.getWeek());
            mTvDateStart.setText(mTransaction.getDateStart());
            String rep = mTransaction.getSchedule();
            rep = rep.replaceAll("\"\"", "");
            mTvSchedule.setText(rep);
            mTvTotalFee.setText(mTransaction.getTotalFee());
            mTvVisu.setText(mTransaction.getNameParent());
        }
    }

    @Click({R.id.mTvCancel, R.id.mTvSubmit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvCancel:

                break;

            case R.id.mTvSubmit:
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("idTransaction", idTransaction);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.emit("acceptTransaction", jsonObject);
                mSocket.on("acceptTransaction", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (args[0].toString().matches("true")) {
                                    showAlertDialog("" + args[1]);
                                } else {
                                    showAlertDialog("" + args[1]);
                                }
                            }
                        });
                    }
                });

                // WaitingViTuActivity_.intent(this).start();
                break;
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                ) {
            // getData();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_CAMERA_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mSocket.on("acceptTutor", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            showAlertDialog("" + args[0]);
                    }
                });
            }
        });

        // WaitingViTuActivity_.intent(this).start();
    }
}
