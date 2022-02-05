package com.example.everyteamproject

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.example.everyteamproject.com.example.everyteamproject.DataBaseHandler
import com.example.everyteamproject.com.example.everyteamproject.project
import com.example.everyteamproject.databinding.ActivityStudyGroupBinding
import kotlinx.android.synthetic.main.activity_study_group.*

class StudyGroup :AppCompatActivity(){
    lateinit var binding: ActivityStudyGroupBinding
    lateinit var member:Member

    lateinit var mDataBaseHandler: DataBaseHandler
    lateinit var projects: MutableList<project>

    lateinit var tvId: TextView
    lateinit var tvTitle: TextView
    lateinit var tvRole: TextView
    lateinit var tvDeadline: TextView
    lateinit var tvClosingTime: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_group)

        tvId = findViewById(R.id.tvId)
        tvTitle = findViewById(R.id.tvTitle)
        tvRole = findViewById(R.id.tvRole)
        tvDeadline = findViewById(R.id.tvDeadline)
        tvClosingTime = findViewById(R.id.tvClosingTime)
        mDataBaseHandler = DataBaseHandler(this)

        //init()

        //DB
        val item = Member()
        val mdata : MutableList<project> = mDataBaseHandler.readData()

        if(mdata.isNotEmpty()){
            for(i in 0 until mdata.size) {
                if(item.id == mdata[i].id){
                    tvId.setText(mdata[i].id)
                    tvTitle.setText(mdata[i].ProjectName)
                    tvRole.setText(mdata[i].Role)
                    tvDeadline.setText(mdata[i].DeadLine)
                    tvClosingTime.setText(mdata[i].ClosingTime)
                }
            }
        }

    }


    /*
    fun init() {
        member = intent.extras?.get("member") as Member
        binding.tvName.setText(member.name)
    }*/
}