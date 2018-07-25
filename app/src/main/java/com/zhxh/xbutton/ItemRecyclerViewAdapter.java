package com.zhxh.xbutton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhxh.xbutton.dummy.ChartData;
import com.zhxh.xbuttonlib.XButton;
import com.zhxh.xchartlib.LineChart;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private final List<ChartData> mValues;
    private final ItemFragment.OnListFragmentInteractionListener mListener;

    public ItemRecyclerViewAdapter(List<ChartData> items, ItemFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ChartData itemData = mValues.get(position);

        holder.mItem = mValues.get(position);
        holder.content.setText(mValues.get(position).getName());
        holder.lineChart.bindData(mValues.get(position).getList());
        holder.lineChart.bindAnimTime(50);
        holder.lineChart.show();

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

        holder.xgifBtn.setVisibility(View.VISIBLE);
        holder.xgifBtn.setAnimDrawable(R.drawable.like_bg_start, R.drawable.like_bg_end, () -> {
            holder.xgifBtn.setText(new StringBuilder().append(holder.xgifBtn.getText().toString()).append("后").toString());
        });

        if (itemData.isFollow()) {
            holder.xgifBtn.setIsAnimComplete(true);
            holder.xgifBtn.setText("12后");
        } else {
            holder.xgifBtn.setIsAnimComplete(false);
            holder.xgifBtn.setText("12");
        }

        holder.xgifBtn.setOnClickListener(v -> {

            if (!holder.xgifBtn.isAnimComplete()) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView content;
        public final LineChart lineChart;
        public final XButton xgifBtn;
        public ChartData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            content = (TextView) view.findViewById(R.id.content);
            lineChart = (LineChart) view.findViewById(R.id.lineChart);
            xgifBtn = (XButton) view.findViewById(R.id.xgifBtn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + content.getText() + "'";
        }
    }
}
