package danazone.com.vitu.ui.main.account;

import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import danazone.com.vitu.BaseContainerFragment;
import danazone.com.vitu.R;
import danazone.com.vitu.sqlite.DBManager;
import danazone.com.vitu.ui.main.account.update.UpdateAccountActivity_;

@EFragment(R.layout.fragment_account)
public class AccountFragment extends BaseContainerFragment {
    @ViewById
    TextView mTvName;
    @ViewById
    TextView mTvPhone;
    @ViewById
    TextView mTvGender;
    @ViewById
    TextView mTvBirthday;
    @ViewById
    TextView mTvSchool;
    @ViewById
    TextView mTvHomeTown;
    @ViewById
    TextView mTvDegree;
    @ViewById
    TextView mTvCaseClass;
    @ViewById
    TextView mTvExperience;

    private DBManager dbManager;

    @Override
    protected void afterViews() {
        dbManager = new DBManager(getContext());
        Cursor cursor = dbManager.getData();
        while (cursor.moveToNext()) {
            mTvName.setText(cursor.getString(1));
            mTvPhone.setText(cursor.getString(2));
            if (cursor.getString(4).equals("M1")) {
                mTvGender.setText("Nam");
            } else {
                mTvGender.setText("Nữ");
            }
            mTvBirthday.setText(cursor.getString(6));
            mTvSchool.setText(cursor.getString(5));
            mTvHomeTown.setText(cursor.getString(7));
            mTvDegree.setText(cursor.getString(9));
            // mTvCaseClass.setText(cursor.getString(6));
            mTvExperience.setText(cursor.getString(8));
        }
    }

    @Click(R.id.mTvSubmit)
    void onClick(View v) {
        UpdateAccountActivity_.intent(this).start();
    }
}
