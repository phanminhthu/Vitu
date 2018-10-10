package danazone.com.vitu.ui.main.info.waiting.accept;

import android.annotation.SuppressLint;

import org.androidannotations.annotations.EActivity;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.R;

@SuppressLint("Registered")
@EActivity(R.layout.activity_accept_vitu)
public class AcceptVituActivity extends BaseActivity {
    @Override
    protected void afterView() {
        getSupportActionBar().hide();
    }
}
