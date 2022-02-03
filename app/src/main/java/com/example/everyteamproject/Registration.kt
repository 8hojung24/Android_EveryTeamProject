package com.example.everyteamproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.everyteamproject.com.example.everyteamproject.AdapterRecycler
import kotlinx.android.synthetic.main.activity_registration.*
import java.text.SimpleDateFormat
import java.util.*

class Registration : AppCompatActivity() {
    var dateString = ""
    var timeString = ""
    lateinit var Project_db: Project_db
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var R_DeadlineBtn: Button
    lateinit var R_Deadline: TextView
    lateinit var R_ClosingTime: TextView
    lateinit var R_RoleBtn:Button
    lateinit var R_Role:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        Project_db = Project_db(this, "MyDB", null, 1)
        R_DeadlineBtn = findViewById(R.id.R_DeadlineBtn)
        R_Deadline = findViewById(R.id.R_Deadline)
        R_ClosingTime = findViewById(R.id.R_ClosingTime)
        R_RoleBtn = findViewById(R.id.R_RoleBtn)
        R_Role = findViewById(R.id.R_Role)

        R_DeadlineBtn.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(year, month, dayOfMonth)
                val Date = cal.time
                val Simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                val DayName: String = Simpledateformat.format(Date)
                dateString = "${month+1}.${dayOfMonth}($DayName)"
                R_Deadline.text = dateString
            }
            val dpd = DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH))

//           최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }

        R_ClosingTime.setOnClickListener{
            val cal = Calendar.getInstance()
            var timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}"+":"+"${minute}"
                R_ClosingTime.text = timeString
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }

        //RoleDialog
        R_RoleBtn.setOnClickListener {
            val dialog = RoleDialog(this)
            dialog.showDialog()
            dialog.setOnClickListener(object : RoleDialog.OnDialogClickListener {
                override fun onClicked(name: String) {
                    R_Role.text = name
                }
            })
        }

        //DB
        R_EditBtn.setOnClickListener {
            var str_projectname: String = R_ProjectName.text.toString()
            var str_role: String = R_Role.text.toString()
            var str_deadline: String = R_Deadline.text.toString()
            var str_closingtime: String = R_ClosingTime.text.toString()

            sqlitedb=Project_db.writableDatabase
            sqlitedb.execSQL("INSERT INTO projects VALUES ('"+str_projectname+"', " +
                    "'"+str_role+"', '"+str_deadline+"', '"+str_closingtime+"')")
            sqlitedb.close()

            val intent = Intent(this@Registration, MainActivity2::class.java)
            intent.putExtra("intent_name", str_projectname)
            startActivity(intent)
        }
    }
}