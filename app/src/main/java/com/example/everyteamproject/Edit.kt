package com.example.everyteamproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.everyteamproject.com.example.everyteamproject.DataBaseHandler
import com.example.everyteamproject.com.example.everyteamproject.project
import java.text.SimpleDateFormat
import java.util.*

class Edit : AppCompatActivity() {
    var dateString = ""
    var timeString = ""
    lateinit var mDataBaseHandler: DataBaseHandler
    lateinit var projects: MutableList<project>
    lateinit var ProjectName: TextView
    lateinit var DeadlineBtn: Button
    lateinit var Deadline: TextView
    lateinit var ClosingTime: TextView
    lateinit var EditBtn: TextView
    lateinit var RoleBtn:Button
    lateinit var Role:TextView
    lateinit var back: Button
    lateinit var sqliteDB: SQLiteDatabase
    var i : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        ProjectName = findViewById(R.id.ProjectName)
        DeadlineBtn = findViewById(R.id.DeadlineBtn)
        Deadline = findViewById(R.id.Deadline)
        ClosingTime = findViewById(R.id.ClosingTime)
        RoleBtn = findViewById(R.id.RoleBtn)
        Role = findViewById(R.id.Role)
        EditBtn = findViewById(R.id.EditBtn)
        back = findViewById(R.id.back)

        mDataBaseHandler = DataBaseHandler(this)

        if(intent.hasExtra("id")) {
            sqliteDB = mDataBaseHandler.readableDatabase
            var cursor: Cursor
            i = intent.getStringExtra("id")!!.toInt()
            cursor = sqliteDB.rawQuery("Select * from ProjectDB WHERE id ='"+i+"'", null)

            while(cursor.moveToNext()){
                ProjectName?.text = cursor.getString(1)
                Role?.text = cursor.getString(2)
                Deadline?.text = cursor.getString(3)
                ClosingTime?.text = cursor.getString(4)
            }
        }

        DeadlineBtn.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(year, month, dayOfMonth)
                val Date = cal.time
                val Simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                val DayName: String = Simpledateformat.format(Date)
                dateString = "${month+1}.${dayOfMonth}($DayName)"
                Deadline.text = dateString
            }
            val dpd = DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH))

//           최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }

        ClosingTime.setOnClickListener{
            val cal = Calendar.getInstance()
            var timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}"+":"+"${minute}"
                ClosingTime.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE), false).show()
        }

        //RoleDialog
        RoleBtn.setOnClickListener {
            val dialog = RoleDialog(this)
            dialog.showDialog()
            dialog.setOnClickListener(object : RoleDialog.OnDialogClickListener {
                override fun onClicked(name: String) {
                    Role.text = name
                }
            })
        }

        //DB
        EditBtn.setOnClickListener {
            //Update Database
            mDataBaseHandler.Update(ProjectName.text.toString(), Role.text.toString(), Deadline.text.toString(), ClosingTime.text.toString(), i)
            Toast.makeText(applicationContext, "수정되었습니다.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }

        back.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}