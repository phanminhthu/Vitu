package danazone.com.vitu.ui.main.history;

import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import danazone.com.vitu.BaseContainerFragment;
import danazone.com.vitu.R;
import danazone.com.vitu.RecyclerViewUtils;
import danazone.com.vitu.ui.main.history.detail.DetailHistoryActivity_;

@EFragment(R.layout.fragment_history)
public class HistoryFragment extends BaseContainerFragment {
    @ViewById
    RecyclerView mRecycleView;
    private HistoryAdapter mAdapter;

    @Override
    protected void afterViews() {
        setUpAdapter();
    }

    private void setUpAdapter() {
        RecyclerViewUtils.Create().setUpVertical(getContext(), mRecycleView);
        mAdapter = new HistoryAdapter(getContext(), new HistoryAdapter.OnSelectClickListener() {
            @Override
            public void onClickItem(int position) {
                //Todo intent
                DetailHistoryActivity_.intent(getContext()).start();
            }
        });
        mRecycleView.setAdapter(mAdapter);
    }
}

