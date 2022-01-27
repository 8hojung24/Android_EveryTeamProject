package com.example.everyteamproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class Schedule_Edit : AppCompatActivity() {
    var dateString = ""
    var timeString = ""
    lateinit var back: Button
    lateinit var ChooseDate: Button
    lateinit var ShowDate: TextView
    lateinit var closingTime1: TextView
    lateinit var closingTime2: TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_registration)

        back = findViewById<Button>(R.id.back)
        ChooseDate = findViewById<Button>(R.id.ChooseDate)
        ShowDate = findViewById<TextView>(R.id.ShowDate)
        closingTime1 = findViewById<TextView>(R.id.closingTime1)
        closingTime2 = findViewById<TextView>(R.id.closingTime2)

        // 수정 필요
//        back.setOnClickListener {
//            val intent = Intent(this, Fragment_calendar::class.java)
//            startActivity(intent)
//        }

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

        closingTime1.setOnClickListener{
            val cal = Calendar.getInstance()
            var timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}"+":"+"${minute}"
                closingTime1.text = dateString
                closingTime1.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }

        closingTime2.setOnClickListener{
            val cal = Calendar.getInstance()
            var timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}"+":"+"${minute}"
                closingTime2.text = dateString
                closingTime2.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }
    }
}