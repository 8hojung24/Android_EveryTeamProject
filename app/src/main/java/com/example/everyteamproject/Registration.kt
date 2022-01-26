package com.example.everyteamproject

import CustomDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registration.*
import java.text.SimpleDateFormat
import java.util.*

class Registration : AppCompatActivity() {
    lateinit var R_Subject: EditText
    lateinit var R_ClosingDateBtn: Button
    lateinit var R_ClosingTime:TextView
    lateinit var R_ClosingDate:TextView
    lateinit var R_EditBtn:Button
//    lateinit var R_RoleBtn:Button
//    lateinit var R_Role:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        R_Subject = findViewById(R.id.R_Subject)
        R_ClosingDateBtn = findViewById(R.id.R_ClosingDateBtn)
        R_ClosingTime = findViewById(R.id.R_ClosingTime)
        R_ClosingDate = findViewById(R.id.R_ClosingDate)
        R_EditBtn = findViewById(R.id.R_EditBtn)
//        R_RoleBtn = findViewById(R.id.R_RoleBtn)
//        R_Role = findViewById(R.id.R_Role)

        R_ClosingDateBtn.setOnClickListener{
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(year, month, dayOfMonth)
                val Date = cal.time
                val Simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                val DayName: String = Simpledateformat.format(Date)
                R_ClosingDate.text = "${month+1}.${dayOfMonth}($DayName)"
            }
            val dpd = DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH))

            //           최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }

        R_ClosingTime.setOnClickListener{
            val cal = Calendar.getInstance()
            var timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                R_ClosingTime.text = "${hourOfDay}"+":"+"${minute}"

            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }

        //RoleDialog
        R_RoleBtn.setOnClickListener{
            val dialog = CustomDialog(this)
            dialog.showDialog()
            dialog.setOnClickListener(object:CustomDialog.OnDialogClickListener{
                override fun onClicked(name: String) {
                    R_Role.text = name
                }
            })
        }
    }
}