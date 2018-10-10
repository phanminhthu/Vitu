package danazone.com.vitu.ui.main.info.waiting;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.R;
import danazone.com.vitu.ui.main.info.waiting.accept.AcceptVituActivity_;

@SuppressLint("Registered")
@EActivity(R.layout.activity_waiting_vitu)
public class WaitingViTuActivity extends BaseActivity {
    @ViewById
    TextView mTvTime;

    @Override
    protected void afterView() {
        getSupportActionBar().hide();
        startTimer();
    }

    @Click(R.id.mTvSubmit)
    void onClick(View v) {
        AcceptVituActivity_.intent(this).start();
    }

    private void startTimer() {
        // 5 Minutes
        long totalTimeCountInMilliseconds = 60 * 5 * 1000;

        new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                String timer = String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60);
                mTvTime.setText("Thời gian còn lại: " + timer);
            }

            @Override
            public void onFinish() {
            }

        }.start();
    }
}
