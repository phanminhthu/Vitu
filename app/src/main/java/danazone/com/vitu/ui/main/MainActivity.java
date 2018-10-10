package danazone.com.vitu.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.net.URISyntaxException;

import danazone.com.vitu.BaseActivity;
import danazone.com.vitu.BaseApp;
import danazone.com.vitu.BaseContainerFragment;
import danazone.com.vitu.BaseFragment;
import danazone.com.vitu.R;
import danazone.com.vitu.bean.Transaction;
import danazone.com.vitu.common.Common;
import danazone.com.vitu.ui.init.login.LoginActivity;
import danazone.com.vitu.ui.main.account.AccountFragment;
import danazone.com.vitu.ui.main.account.AccountFragment_;
import danazone.com.vitu.ui.main.contact.ContactFragment;
import danazone.com.vitu.ui.main.contact.ContactFragment_;
import danazone.com.vitu.ui.main.history.HistoryFragment;
import danazone.com.vitu.ui.main.history.HistoryFragment_;
import danazone.com.vitu.ui.main.info.InformationFragment;
import danazone.com.vitu.ui.main.info.InformationFragment_;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById
    DrawerLayout mDrawer;
    @ViewById
    NavigationView mNV;
    private ActionBarDrawerToggle mToggle;
    private Toast mToastExit;
    private Socket mSocket;
    private String a;


    @Override
    protected void afterView() {
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();

        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.colose);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(SOFT_INPUT_ADJUST_PAN);
        mToastExit = Toast.makeText(MainActivity.this, getResources().getString(R.string.text_back_exit), Toast.LENGTH_SHORT);
        setupDrawerContent(mNV);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            a = bundle.getString("jjj");
            Transaction transaction = (Transaction) bundle.getSerializable("kkk");
            System.out.println("66666666666666666666666666666: " + intent.getSerializableExtra("kkk") + transaction + " : " + a);
            replaceFragment(InformationFragment_.builder().mKey(1).mTransaction(transaction).build());
        } else {
            replaceFragment(InformationFragment_.builder().build());
        }
    }

    /**
     * Open new fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransactionAccount = getSupportFragmentManager().beginTransaction();
        fragmentTransactionAccount.replace(R.id.mFrameContainer, fragment);
        fragmentTransactionAccount.commit();
    }

    /**
     * check current fragment id
     *
     * @return
     */
    public BaseFragment getCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.mFrameContainer);
    }

    @Override
    public void onBackPressed() {
        BaseContainerFragment fragment = (BaseContainerFragment) getCurrentFragment();

        if (!fragment.popFragment()) {
            boolean isExit = mToastExit.getView().isShown();
            if (!isExit) {
                mToastExit.show();
            } else {
                super.onBackPressed();
                System.exit(0);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mGift:
                Toast.makeText(this, "Gift", Toast.LENGTH_SHORT).show();
                break;
        }

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Selected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mHome:
                if (!((BaseContainerFragment) getCurrentFragment() instanceof InformationFragment))
                    replaceFragment(InformationFragment_.builder().build());
                break;

            case R.id.mContact:
                if (!((BaseContainerFragment) getCurrentFragment() instanceof ContactFragment))
                    replaceFragment(ContactFragment_.builder().build());
                break;

            case R.id.mHistory:
                if (!((BaseContainerFragment) getCurrentFragment() instanceof HistoryFragment))
                    replaceFragment(HistoryFragment_.builder().build());
                break;

            case R.id.mAccount:
                if (!((BaseContainerFragment) getCurrentFragment() instanceof AccountFragment))
                    replaceFragment(AccountFragment_.builder().build());
                break;

            case R.id.mFeddBack:
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "lehieudev01@gmail.com", null));
                email.putExtra(Intent.EXTRA_SUBJECT, "Phản hồi ứng dụng");
                email.putExtra(Intent.EXTRA_TEXT, "Ứng dụng: VITU \n Phiên bản ứng dụng: 1.0 \n \n Chúng tôi có thể làm gì để ứng dụng được tốt hơn?");
                startActivity(Intent.createChooser(email, "Gởi phản hồi"));
                break;

            case R.id.mShare:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Chia sẻ ứng dụng đến nhiều người");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.devpro.xedapdana");
                startActivity(Intent.createChooser(shareIntent, "Share App"));
                break;

            case R.id.mExit:
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                finish();
                break;
        }
        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawer.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Selected(item);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}

