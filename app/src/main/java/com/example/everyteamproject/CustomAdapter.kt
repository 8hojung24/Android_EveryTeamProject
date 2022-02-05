package com.example.everyteamproject.com.example.everyteamproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.everyteamproject.Fragment_calendar
import com.example.everyteamproject.R
import com.example.everyteamproject.Schedule_Edit

class CustomAdapter(var scheduleItems: MutableList<ScheduleItem>?, val mContext: Context) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    var mDBHelper: DBHelper = DBHelper(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.schedule_list,
                parent,
                false
        )
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        var cnt: Int
        if (scheduleItems?.size == null)
            cnt = 0
        else
            cnt = scheduleItems?.size!!

        return cnt
    }

    override fun onBindViewHolder(holder: CustomAdapter.CustomViewHolder, position: Int) {
        holder.listTitle.text = scheduleItems?.get(position)?.title
        holder.startTime.text = scheduleItems?.get(position)?.startTime
        holder.endTime.text = scheduleItems?.get(position)?.endTime

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, Schedule_Edit::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listTitle = itemView.findViewById<TextView>(R.id.listTitle)
        val startTime = itemView.findViewById<TextView>(R.id.startTime)
        val endTime = itemView.findViewById<TextView>(R.id.endTime)
    }

    // 액티비티에서 호출되는 함수이며, 현재 어댑터에 새로운 게시글 아이템을 전달받아 추가하는 목적이다.
    fun addItem(_item: ScheduleItem ) {
        scheduleItems?.add(0, _item)
        notifyItemInserted(0)
    }
}