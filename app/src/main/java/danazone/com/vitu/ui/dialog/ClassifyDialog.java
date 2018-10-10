package danazone.com.vitu.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import danazone.com.vitu.BaseDialog;
import danazone.com.vitu.R;

public class ClassifyDialog extends BaseDialog implements View.OnClickListener {
    /**
     * Interface dialog click listener
     */
    public interface OnDialogClickListener {
        void onStudent();

        void onGraduating();
    }

    private OnDialogClickListener mListener;


    public ClassifyDialog(Context context, OnDialogClickListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        ImageView mImgCancelDialog = (ImageView) findViewById(R.id.mImgCancelDialog);
        TextView mTvStudent = (TextView) findViewById(R.id.mTvStudent);
        TextView mTvGraduating = (TextView) findViewById(R.id.mTvGraduating);

        mImgCancelDialog.setOnClickListener(this);
        mTvStudent.setOnClickListener(this);
        mTvGraduating.setOnClickListener(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_classify;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgCancelDialog:
                dismiss();
                break;

            case R.id.mTvStudent:
                mListener.onStudent();
                dismiss();
                break;

            case R.id.mTvGraduating:
                mListener.onGraduating();
                dismiss();
                break;
        }
    }
}
