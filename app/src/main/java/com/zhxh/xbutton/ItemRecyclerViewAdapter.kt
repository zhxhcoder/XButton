package com.zhxh.xbutton

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.zhxh.xbutton.dummy.ChartData
import com.zhxh.xbuttonlib.XButton
import com.zhxh.xchartlib.LineChart

class ItemRecyclerViewAdapter(private val mValues: List<ChartData>, private val mListener: ItemFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    private val handler = Handler()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = mValues[position]

        holder.mItem = mValues[position]
        holder.content.text = mValues[position].name
        holder.lineChart.bindData(mValues[position].list)
        holder.lineChart.bindAnimTime(50)
        holder.lineChart.show()

        holder.mView.setOnClickListener { v ->
            mListener?.onListFragmentInteraction(holder.mItem!!)
        }

        holder.xgifBtn.visibility = View.VISIBLE
        holder.xgifBtn.setTextColorAnimEnd(-0xb3af)
        holder.xgifBtn.setAnimDrawable(R.drawable.like_bg_start, R.drawable.like_bg_end) { holder.xgifBtn.text = StringBuilder().append(holder.xgifBtn.text.toString()).append("后").toString() }

        if (itemData.isFollow) {
            holder.xgifBtn.setIsAnimComplete(true)
            holder.xgifBtn.text = "12后"
        } else {
            holder.xgifBtn.setIsAnimComplete(false)
            holder.xgifBtn.text = "12"
        }

        holder.xgifBtn.setOnClickListener { v ->
            //无论是否点赞成功都先运行动画，然后根据接口请求结果展现，
            //请求结果出来时，先不更新数据或状态，等动画运行完再更新
            //动画运行完时，接口还未返回，=这时就要展现

            if (!holder.xgifBtn.isAnimComplete) {
                holder.xgifBtn.startAnim()
            }
        }

    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val content: TextView
        val lineChart: LineChart
        val xgifBtn: XButton
        var mItem: ChartData? = null

        init {
            content = mView.findViewById<View>(R.id.content) as TextView
            lineChart = mView.findViewById<View>(R.id.lineChart) as LineChart
            xgifBtn = mView.findViewById<View>(R.id.xgifBtn) as XButton
        }

        override fun toString(): String {
            return super.toString() + " '" + content.text + "'"
        }
    }
}
