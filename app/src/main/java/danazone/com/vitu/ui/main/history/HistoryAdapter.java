package danazone.com.vitu.ui.main.history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import danazone.com.vitu.BaseAdapter;
import danazone.com.vitu.R;

public class HistoryAdapter extends BaseAdapter {
    public interface OnSelectClickListener {
        void onClickItem(int position);
    }

    private OnSelectClickListener mListener;

    protected HistoryAdapter(@NonNull Context context, OnSelectClickListener listener) {
        super(context);
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_history, parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    /**
     * ViewHolderItem
     */
    private class ViewHolderItem extends RecyclerView.ViewHolder {
        // private TextView mTvStatus;


        public ViewHolderItem(View view) {
            super(view);
            // mTvStatus = (TextView) view.findViewById(R.id.mTvStatus);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClickItem(getLayoutPosition());
                    }
                }
            });
        }
    }
}

