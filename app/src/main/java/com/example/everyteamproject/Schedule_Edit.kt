package com.example.everyteamproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.everyteamproject.com.example.everyteamproject.CustomAdapter
import com.example.everyteamproject.com.example.everyteamproject.DBHelper
import com.example.everyteamproject.com.example.everyteamproject.ScheduleItem
import java.text.SimpleDateFormat
import java.util.*

class Schedule_Edit() : AppCompatActivity() {
    var dateString = ""
    var timeString = ""
    lateinit var Title: EditText
    lateinit var memoBtn: Button
    lateinit var back: Button
    lateinit var ChooseDate: Button
    lateinit var ShowDate: TextView
    lateinit var closingTime1: TextView
    lateinit var closingTime2: TextView
    lateinit var delBtn: Button
    lateinit var saveBtn: Button

    lateinit var mDBHelper: DBHelper
    lateinit var ScheduleItems : MutableList<ScheduleItem>
    private var mAdapter: CustomAdapter?= null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule__edit)

        Title = findViewById<EditText>(R.id.Title)
        memoBtn = findViewById<Button>(R.id.memoBtn)
        back = findViewById<Button>(R.id.back)
        ChooseDate = findViewById<Button>(R.id.ChooseDate)
        ShowDate = findViewById<TextView>(R.id.ShowDate)
        closingTime1 = findViewById<TextView>(R.id.closingTime1)
        closingTime2 = findViewById<TextView>(R.id.closingTime2)
        delBtn = findViewById<Button>(R.id.delBtn)
        memoBtn = findViewById<Button>(R.id.memoBtn)
        saveBtn = findViewById<Button>(R.id.saveBtn)

        ScheduleItems = mutableListOf<ScheduleItem>()
        mDBHelper = DBHelper(this)

        // 뒤로가기 버튼
        back.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

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
            val dpd = DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))

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

        memoBtn.setOnClickListener {
            var intent = Intent(this, Schedule_memo::class.java)
            startActivity(intent)
        }

        delBtn.setOnClickListener {
            var num = (intent.getStringExtra("id"))?.toInt()
            mDBHelper.Delete(num)
            mAdapter?.notifyDataSetChanged()
            Toast.makeText(applicationContext, "일정이 제거 되었습니다", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        saveBtn.setOnClickListener {
            var num = (intent.getStringExtra("id"))?.toInt()
            mDBHelper.Update(Title.text.toString(), ShowDate.text.toString(), closingTime1.text.toString(), closingTime2.text.toString(), "장소", num)
            mAdapter?.notifyDataSetChanged()
            Toast.makeText(applicationContext, "일정이 수정 되었습니다", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }
    }

}