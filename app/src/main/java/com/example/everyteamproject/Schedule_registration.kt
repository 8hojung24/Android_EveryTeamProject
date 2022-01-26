package com.example.everyteamproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class Schedule_registration : AppCompatActivity() {
    var dateString = ""
    var timeString = ""
    lateinit var ChooseDate: Button
    lateinit var ShowDate: TextView
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_registration)

        ChooseDate = findViewById<Button>(R.id.ChooseDate)
        ShowDate = findViewById<TextView>(R.id.ShowDate)

        ChooseDate.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(year, month, dayOfMonth)
                val Date = cal.time
                val Simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                val DayName: String = Simpledateformat.format(Date)
                dateString = "${month+1}.${dayOfMonth}($DayName)"
                ShowDate.text = dateString
            }
            val dpd = DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH))

//           최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }
    }
}