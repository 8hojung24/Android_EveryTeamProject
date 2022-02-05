package com.example.everyteamproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.everyteamproject.com.example.everyteamproject.DataBaseHandler
import com.example.everyteamproject.com.example.everyteamproject.project

class Todolist  : AppCompatActivity() {

    lateinit var tvProjectName: TextView
    lateinit var tvDeadline:TextView
    lateinit var tvClosingTime : TextView
    lateinit var btnEdit:Button
    lateinit var btnDelete:Button

    lateinit var mDataBaseHandler: DataBaseHandler
    lateinit var projects: MutableList<project>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todolist)

        tvProjectName = findViewById(R.id.tvProjectName)
        tvDeadline = findViewById(R.id.tvDeadline)
        tvClosingTime = findViewById(R.id.tvClosingTime)
        btnEdit = findViewById(R.id.btnEdit)
        btnDelete = findViewById(R.id.btnDelete)

        mDataBaseHandler = DataBaseHandler(this)
        projects = mutableListOf<project>()

    }

}
