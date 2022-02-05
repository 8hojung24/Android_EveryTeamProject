package com.example.everyteamproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.everyteamproject.com.example.everyteamproject.DBHelper
import com.example.everyteamproject.com.example.everyteamproject.ScheduleItem
import kotlinx.android.synthetic.main.activity_schedule_memo.*
import java.io.FileInputStream
import java.io.FileOutputStream

class Schedule_memo : AppCompatActivity() {
    lateinit var back: Button
    lateinit var Title: EditText
    lateinit var Memo: EditText
    lateinit var saveBtn: Button
    lateinit var delBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_memo)

        back = findViewById<Button>(R.id.back)
        Title = findViewById<EditText>(R.id.Title)
        Memo = findViewById<EditText>(R.id.Memo)
        saveBtn = findViewById<Button>(R.id.saveBtn)
        delBtn = findViewById<Button>(R.id.delBtn)

        loadData()

        back.setOnClickListener {
            var intent = Intent(this, Schedule_Edit::class.java)
            startActivity(intent)
        }

        saveBtn.setOnClickListener {
            saveData(Title.text.toString(), Memo.text.toString())
            var intent = Intent(this, Schedule_Edit::class.java)
            intent.putExtra("title", Title.text.toString())
            intent.putExtra("memo", Memo.text.toString())
            Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show() // 토스트 메세지
            startActivity(intent)
        }

        delBtn.setOnClickListener {
            Title.setText(null)
            Memo.setText(null)
            Toast.makeText(this, "삭제되었습니다", Toast.LENGTH_SHORT).show()
        }

    }

    private fun saveData(title: String, memo: String){
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        editor.putString("KEY_TITLE", Title.text.toString()).apply()
        editor.putString("KEY_MEMO", Memo.text.toString()).apply()
    }

    private fun loadData(){
        var pref = this.getPreferences(0)
        var title = pref.getString("KEY_TITLE", "")
        var memo = pref.getString("KEY_MEMO", "")

        if(title != "" && memo != ""){
            Title.setText(title.toString())
            Memo.setText(memo.toString())
        }
    }
}