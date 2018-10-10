package danazone.com.vitu.ui.init.register.avatar;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.R;
import danazone.com.vitu.ui.main.MainActivity_;

@SuppressLint("Registered")
@EActivity(R.layout.activity_register_done)
public class RegisterDoneActivity extends BaseActivity {
    @Override
    protected void afterView() {
        getSupportActionBar().hide();
    }

    @Click({R.id.mTvSubmit, R.id.mImgAvatar, R.id.mImgSelectID})
    void onClick(View v) {
        switch ((v.getId())) {
            case R.id.mTvSubmit:
                MainActivity_.intent(this).start();
                break;

            case R.id.mImgAvatar:
                Toast.makeText(RegisterDoneActivity.this, "Đang phát triển", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mImgSelectID:
                Toast.makeText(RegisterDoneActivity.this, "Đang phát triển", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
