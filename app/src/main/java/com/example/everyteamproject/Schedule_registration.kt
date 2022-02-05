package com.example.everyteamproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.everyteamproject.com.example.everyteamproject.CustomAdapter
import com.example.everyteamproject.com.example.everyteamproject.DBHelper
import com.example.everyteamproject.com.example.everyteamproject.ScheduleItem
import java.text.SimpleDateFormat
import java.util.*

class Schedule_registration : AppCompatActivity() {
    var mDate = ""
    var dateString = ""
    var timeString = ""

    lateinit var Title: EditText
    lateinit var ShowDate: TextView
    lateinit var closingTime1: TextView
    lateinit var closingTime2: TextView

    lateinit var back: Button
    lateinit var ChooseDate: Button
    lateinit var addShedule: Button

    lateinit var mDBHelper: DBHelper
    lateinit var ScheduleItems : MutableList<ScheduleItem>
    private var mAdapter: CustomAdapter?= null


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_registration)

        Title = findViewById<EditText>(R.id.Title)
        ShowDate = findViewById<TextView>(R.id.ShowDate)
        closingTime1 = findViewById<TextView>(R.id.closingTime1)
        closingTime2 = findViewById<TextView>(R.id.closingTime2)
        back = findViewById<Button>(R.id.back)
        ChooseDate = findViewById<Button>(R.id.ChooseDate)
        addShedule = findViewById<Button>(R.id.addShedule)

        mDBHelper = DBHelper(this)
        ScheduleItems = mutableListOf<ScheduleItem>()


        // 뒤로가기 버튼
        back.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        ChooseDate.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth -> // 달력 날짜가 선택되면
                cal.set(year, month, dayOfMonth)
                val Date = cal.time
                val Simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                val DayName: String = Simpledateformat.format(Date)
                dateString = "${month+1}.${dayOfMonth}($DayName)"
                ShowDate.text = dateString // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
                mDate = "${year}/${month+1}/${dayOfMonth}"
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
                closingTime1.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }

        closingTime2.setOnClickListener{
            val cal = Calendar.getInstance()
            var timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}"+":"+"${minute}"
                closingTime2.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }

        // 저장 Button이 클릭되면
        addShedule.setOnClickListener {
            //Insert Database
            mDBHelper.Insert(Title.text.toString(), mDate, closingTime1.text.toString(), closingTime2.text.toString(), "장소")
            Toast.makeText(applicationContext, "입력됨", Toast.LENGTH_SHORT).show()

            // Insert UI
            init()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    fun init() {
        val item = ScheduleItem()
        item.title = Title.text.toString()
        item.startTime = closingTime1.text.toString()
        item.endTime = closingTime2.text.toString()
        ScheduleItems?.add(item)
        mAdapter?.addItem(item)
        mAdapter?.notifyDataSetChanged()
    }
}