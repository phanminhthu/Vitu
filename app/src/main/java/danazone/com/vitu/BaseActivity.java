package danazone.com.vitu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by PC on 9/27/2018.
 */

@EActivity
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    private ProgressDialog mProgressDialog;
    private Handler mHandlerProcess = new Handler();

    protected abstract void afterView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intProgressBar();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Lato_Regular.ttf").setFontAttrId(R.attr.fontPath).build());
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @AfterViews
    protected void initView() {
        this.afterView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseApp.getInstance().activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseApp.getInstance().activityPaused();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * Init dialog.
     */
    private void intProgressBar() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("waiting");
        mProgressDialog.setCancelable(false);
    }

    /**
     * show dialog message.
     */
    public void showProgressDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing() && !isFinishing()) {
            mProgressDialog.show();
        }

        // dismiss process bar about 10 second
        mHandlerProcess.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        }, 30000);
    }

    /**
     * dismiss message dialog
     */
    public void dismissProgressDialog() {
        if (!isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * show message dialog
     *
     * @param msg
     */
    protected void showAlertDialog(@NonNull String msg) {
        try {
            new AlertDialog.Builder(this)
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * show message and title
     *
     * @param title
     * @param message
     */
    public void showDialogMessage(String title, String message) {
        if (!isFinishing()) {
            new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        }
    }
}
