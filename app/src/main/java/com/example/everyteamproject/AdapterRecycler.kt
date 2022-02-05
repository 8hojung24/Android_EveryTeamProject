package com.example.everyteamproject.com.example.everyteamproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.everyteamproject.*
import kotlinx.android.synthetic.main.activity_todolist.view.*

class AdapterRecycler(var project: MutableList<project>?, val mContext: Context) : RecyclerView.Adapter<AdapterRecycler.CustomViewHolder>() {

    var mDataBaseHandler: DataBaseHandler = DataBaseHandler(mContext)
    var index : Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRecycler.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.activity_todolist,
                parent,
                false
        )
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        var cnt: Int
        if (project?.size == null)
            cnt = 0
        else
            cnt = project?.size!!

        return cnt
    }

    override fun onBindViewHolder(holder: AdapterRecycler.CustomViewHolder, position: Int) {
        holder.tvProjectName.text = project?.get(position)?.ProjectName
        holder.tvDeadline.text = project?.get(position)?.DeadLine
        holder.tvClosingTime.text = project?.get(position)?.ClosingTime
        index = project?.get(position)?.id!!.toInt()

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView?.context, StudyGroup::class.java)
            intent.putExtra("id", (project?.get(position)?.id).toString())
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
        holder.itemView.btnEdit.setOnClickListener {
            val intent = Intent(holder.itemView?.context, Edit::class.java)
            intent.putExtra("id", (project?.get(position)?.id).toString())
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
        holder.itemView.btnDelete.setOnClickListener {
            mDataBaseHandler.Delete(project?.get(position)?.id)
            Toast.makeText(holder.itemView?.context, "삭제되었습니다.",Toast.LENGTH_SHORT).show()
            notifyItemRemoved(this.index)
            Intent(holder.itemView?.context, this::class.java)
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProjectName = itemView.findViewById<TextView>(R.id.tvProjectName)
        val tvDeadline = itemView.findViewById<TextView>(R.id.tvDeadline)
        val tvClosingTime = itemView.findViewById<TextView>(R.id.tvClosingTime)
    }

    // 액티비티에서 호출되는 함수이며, 현재 어댑터에 새로운 게시글 아이템을 전달받아 추가하는 목적이다.
    fun addItem(_item: project ) {
        project?.add(0, _item)
        notifyItemInserted(0)
    }
}