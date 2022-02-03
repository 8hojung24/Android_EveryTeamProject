package com.example.everyteamproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var R_EditBtn:Button

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        R_EditBtn = findViewById(R.id.R_EditBtn)
        R_EditBtn.setOnClickListener {
            val intent = Intent(this@Edit, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}