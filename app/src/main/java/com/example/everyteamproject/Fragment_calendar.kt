package com.example.everyteamproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*


class Fragment_calendar : Fragment() {
    var fname: String = ""
    var str: String = ""

    lateinit var calendarView: CalendarView
    lateinit var selectedDate: TextView
    lateinit var save_Btn: Button
    lateinit var printSchedule: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = view.findViewById(R.id.calendarView)
        selectedDate = view.findViewById(R.id.selectedDate)
        save_Btn = view.findViewById(R.id.save_Btn)
        printSchedule = view.findViewById(R.id.printSchedule)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_calendar, container, false)

        save_Btn = v.findViewById<Button>(R.id.save_Btn)
        save_Btn.setOnClickListener {
            val intent = Intent(context, Schedule_registration::class.java)
            startActivity(intent)
        }
        return v
    }

    //수정 필요
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //var date: Date? = null
        val v = context?.let { CalendarView(it) }
        v?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
            save_Btn.visibility = View.VISIBLE // 저장 버튼이 Visible
            printSchedule.visibility = View.INVISIBLE // 저장된 일정 textView가 Invisible

            selectedDate.text = String.format(
                    "%d / %d / %d",
                    year,
                    month + 1,
                    dayOfMonth
            )  // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            //date = Date(year, month, dayOfMonth)
        }
//        val myView: View = inflater.inflate(R.layout.fragment_one, container, false)
//        val mybutton: Button = myView.findViewById(R.id.save_Btn)

    }

//            saveDiary(fname) // saveDiary 메소드 호출
//            var t1 = Toast.makeText(this, fname + "데이터를 저장했습니다.", Toast.LENGTH_SHORT)
//            t1.show()
//            str = contextEditText.getText().toString() // str 변수에 edittext내용을 toString 형으로 저장
//            printSchedule.text = "${str}" // textView에 str 출력
//            save_Btn.visibility = View.INVISIBLE
//            cha_Btn.visibility = View.VISIBLE
//            del_Btn.visibility = View.VISIBLE
//            contextEditText.visibility = View.INVISIBLE
//            printSchedule.visibility = View.VISIBLE

}


