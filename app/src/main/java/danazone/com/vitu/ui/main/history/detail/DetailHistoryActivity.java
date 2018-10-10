package danazone.com.vitu.ui.main.history.detail;

import android.annotation.SuppressLint;

import org.androidannotations.annotations.EActivity;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.R;

@SuppressLint("Registered")
@EActivity(R.layout.activity_detail_history)
public class DetailHistoryActivity extends BaseActivity {
    @Override
    protected void afterView() {
        getSupportActionBar().hide();
    }
}
