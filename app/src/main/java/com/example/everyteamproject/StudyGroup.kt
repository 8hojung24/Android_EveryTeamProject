package com.example.everyteamproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.everyteamproject.com.example.everyteamproject.DataBaseHandler
import com.example.everyteamproject.com.example.everyteamproject.project
import java.util.*

class StudyGroup :AppCompatActivity(){

    lateinit var back : Button
    var tvProjectName: TextView? = null
    var tvRole: TextView? = null
    var tvDeadline: TextView? = null
    var tvClosingTime: TextView? = null
    lateinit var mDataBaseHandler: DataBaseHandler
    lateinit var sqliteDB: SQLiteDatabase
    var projects :MutableList<project> =  ArrayList()

    var i : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_group)
        mDataBaseHandler = DataBaseHandler(this)
        tvProjectName = findViewById(R.id.tvProjectName)
        tvRole = findViewById(R.id.tvRole)
        tvDeadline = findViewById(R.id.tvDeadline)
        tvClosingTime = findViewById(R.id.tvClosingTime)
        back = findViewById(R.id.back)

        if(intent.hasExtra("id")) {
            i = intent.getStringExtra("id")!!.toInt()
            sqliteDB = mDataBaseHandler.readableDatabase
            var cursor: Cursor
            cursor = sqliteDB.rawQuery("Select * from ProjectDB WHERE id ='"+i+"'", null)

            while(cursor.moveToNext()){
                tvProjectName?.text = cursor.getString(1)
                tvRole?.text = cursor.getString(2)
                tvDeadline?.text = cursor.getString(3)
                tvClosingTime?.text = cursor.getString(4)
            }
        }

        back.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

}

