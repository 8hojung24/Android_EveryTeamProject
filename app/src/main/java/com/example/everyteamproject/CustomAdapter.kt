package com.example.everyteamproject.com.example.everyteamproject

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.everyteamproject.R

class CustomAdapter(val scheduleItems: MutableList<ScheduleItem>, val mContext: Context) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

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
        return scheduleItems.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.CustomViewHolder, position: Int) {
        holder.listTitle.text = scheduleItems.get(position).title
        holder.startTime.text = scheduleItems.get(position).startTime
        holder.endTime.text = scheduleItems.get(position).endTime

        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listTitle = itemView.findViewById<TextView>(R.id.listTitle)
        val startTime = itemView.findViewById<TextView>(R.id.startTime)
        val endTime = itemView.findViewById<TextView>(R.id.endTime)
    }
    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int) {

        }
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}