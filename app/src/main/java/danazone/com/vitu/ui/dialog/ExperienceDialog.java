package danazone.com.vitu.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import danazone.com.vitu.BaseDialog;
import danazone.com.vitu.R;

public class ExperienceDialog extends BaseDialog implements View.OnClickListener {
    /**
     * Interface dialog click listener
     */
    public interface OnDialogClickListener {
        void onOneYear();
        void onThreeYears();
        void onFiveYears();
        void onThanFiveYears();
    }

    private OnDialogClickListener mListener;


    public ExperienceDialog(Context context, OnDialogClickListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        ImageView mImgCancelDialog = (ImageView) findViewById(R.id.mImgCancelDialog);
        TextView mTvOneYear = (TextView) findViewById(R.id.mTvOneYear);
        TextView mTvThreeYears = (TextView) findViewById(R.id.mTvThreeYears);
        TextView mTvFiveYears = (TextView) findViewById(R.id.mTvFiveYears);
        TextView mTvThanFiveYears = (TextView) findViewById(R.id.mTvThanFiveYears);

        mImgCancelDialog.setOnClickListener(this);
        mTvOneYear.setOnClickListener(this);
        mTvThreeYears.setOnClickListener(this);
        mTvFiveYears.setOnClickListener(this);
        mTvThanFiveYears.setOnClickListener(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_experience;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgCancelDialog:
                dismiss();
                break;

            case R.id.mTvOneYear:
                mListener.onOneYear();
                dismiss();
                break;

            case R.id.mTvThreeYears:
                mListener.onThreeYears();
                dismiss();
                break;

            case R.id.mTvFiveYears:
                mListener.onFiveYears();
                dismiss();
                break;

            case R.id.mTvThanFiveYears:
                mListener.onThanFiveYears();
                dismiss();
                break;
        }
    }
}
