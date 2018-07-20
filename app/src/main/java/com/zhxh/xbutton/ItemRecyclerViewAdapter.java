package com.zhxh.xbutton;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhxh.xbutton.dummy.ChartData;
import com.zhxh.xbuttonlib.XGifButton;
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
        holder.xgifBtn.bindGifSource(R.drawable.like_bg_anim)
                .bindBeforeTextColor(Color.parseColor("#ffffff"))
                .bindAfterTextColor(Color.parseColor("#ff4c51"));

        holder.xgifBtn.setText("12");

        holder.xgifBtn.setIsAnimComplete(false);
        holder.xgifBtn.setClickable(true);

        holder.xgifBtn.setOnClickListener(v -> {


            if (!holder.xgifBtn.isAnimComplete()) {
                holder.xgifBtn.getGifDrawable().addAnimationListener(loopNumber -> {


                    holder.xgifBtn.setIsAnimComplete(true);
                });

                holder.xgifBtn.bindGifSource(R.drawable.like_bg_anim);

            }
            holder.xgifBtn.setClickable(false);

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
        public final XGifButton xgifBtn;
        public ChartData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            content = (TextView) view.findViewById(R.id.content);
            lineChart = (LineChart) view.findViewById(R.id.lineChart);
            xgifBtn = (XGifButton) view.findViewById(R.id.xgifBtn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + content.getText() + "'";
        }
    }
}
