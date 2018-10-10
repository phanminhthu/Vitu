package danazone.com.vitu.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import danazone.com.vitu.BaseDialog;
import danazone.com.vitu.R;

public class GenderDialog extends BaseDialog implements View.OnClickListener {
    /**
     * Interface dialog click listener
     */
    public interface OnDialogClickListener {
        void onMale();

        void onFemale();
    }

    private OnDialogClickListener mListener;


    public GenderDialog(Context context, OnDialogClickListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        ImageView mImgCancelDialog = (ImageView) findViewById(R.id.mImgCancelDialog);
        TextView mTvMale = (TextView) findViewById(R.id.mTvMale);
        TextView mTvFemale = (TextView) findViewById(R.id.mTvFemale);

        mImgCancelDialog.setOnClickListener(this);
        mTvMale.setOnClickListener(this);
        mTvFemale.setOnClickListener(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_gender;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgCancelDialog:
                dismiss();
                break;

            case R.id.mTvMale:
                mListener.onMale();
                dismiss();
                break;

            case R.id.mTvFemale:
                mListener.onFemale();
                dismiss();
                break;
        }
    }
}
