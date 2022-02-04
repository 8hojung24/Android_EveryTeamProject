package com.example.everyteamproject.com.example.everyteamproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.everyteamproject.Fragment_mypage
import com.example.everyteamproject.Member
import com.example.everyteamproject.R
import com.example.everyteamproject.StudyGroup
import com.example.everyteamproject.databinding.ActivityTodolistBinding

class AdapterRecycler(private val context: Fragment_mypage):RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<Member>()

    // add
    init {
        listData = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ActivityTodolistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = listData[position]
        holder.setData(member, position)
    }
    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(val binding: ActivityTodolistBinding) : RecyclerView.ViewHolder(binding.root){
    private val mainActivity = Fragment_mypage.getInstance()
    //추가
    var context: Context?= null

    var mMember: Member? = null
    var mPosition: Int? = null

    //추가
    init {
        init()
    }

    fun bind(context: Context, mPosition: Int, mMember: Member) {
        this.context = context
        this.mPosition = mPosition
        this.mMember = mMember
        binding.textView.setText(Member().name)
        binding.textView2.setText(Member().role)
        binding.textView3.setText(Member().time)
        //binding.executePendingBindings()
    }

    fun init() {
        binding.btnDelete.setOnClickListener {
            mainActivity?.deleteMember(mMember!!)
        }
        binding.btnEdit.setOnClickListener {
            mainActivity?.editMember()
        }
        binding.viewGroup.setOnClickListener {
            mainActivity?.goActivity()
        }
        //추가
        //binding.viewGroup.setOnClickListener(this)
        binding.textView.isClickable()
    }

    fun setData(member: Member, position: Int){
        binding.textView.text = member.name
        binding.textView2.text = member.role
        binding.textView3.text = member.time
        this.mMember = member
        this.mPosition = position
    }

    /*
    // 추가
    override fun onClick(v: View){
        val id = v.id
        when(id){
            R.id.viewGroup -> {
                val intent = Intent(context, StudyGroup::class.java)
                intent.putExtra("member", Member())
                context?.startActivity(intent)
            }
        }
    }*/
}