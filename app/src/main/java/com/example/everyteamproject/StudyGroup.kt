package com.example.everyteamproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.everyteamproject.databinding.ActivityStudyGroupBinding

class StudyGroup :AppCompatActivity(){
    lateinit var binding: ActivityStudyGroupBinding
    lateinit var member:Member

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_group)
        //init()
    }


    /*
    fun init() {
        member = intent.extras?.get("member") as Member
        binding.tvName.setText(member.name)
    }*/
}