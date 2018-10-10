package danazone.com.vitu.ui.init.splash;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.R;
import danazone.com.vitu.ui.init.login.LoginActivity_;
import danazone.com.vitu.ui.main.MainActivity_;

@SuppressLint("Registered")
@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    @ViewById
    TextView mTvSplash;
    private Handler mHandler = new Handler();

    @Override
    protected void afterView() {
        getSupportActionBar().hide();
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(400);
        mAnimation.setRepeatCount(android.view.animation.Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mTvSplash.startAnimation(mAnimation);

        Runnable mActivityStarter = new Runnable() {
            @Override
            public void run() {
                LoginActivity_.intent(SplashActivity.this).start();
            }
        };
        mHandler.postDelayed(mActivityStarter, 2000);
    }
}
