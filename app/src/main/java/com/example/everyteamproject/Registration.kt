package com.example.everyteamproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Registration : AppCompatActivity() {
    lateinit var R_Subject: EditText
    lateinit var R_ClosingDateBtn: Button
    lateinit var R_ClosingTime:TextView
    lateinit var R_ClosingDate:TextView
    lateinit var R_EditBtn:Button
    var dateString=""
    var timeString=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        R_Subject = findViewById(R.id.R_Subject)
        R_ClosingDateBtn = findViewById(R.id.R_ClosingDateBtn)
        R_ClosingTime = findViewById(R.id.R_ClosingTime)
        R_ClosingDate = findViewById(R.id.R_ClosingDate)
        R_EditBtn = findViewById(R.id.R_EditBtn)

        R_ClosingDateBtn.setOnClickListener{
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener {
                view, month, dayOfMonth, weekDay ->
                dateString = "${month+1}"+"." +"${dayOfMonth}"+"("+"${weekDay}"+")"
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_WEEK)).show()
        }

        R_ClosingTime.setOnClickListener{
            val cal = Calendar.getInstance()
            var timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}"+":"+"${minute}"
                R_ClosingDate.text = dateString
                R_ClosingTime.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }
    }
}