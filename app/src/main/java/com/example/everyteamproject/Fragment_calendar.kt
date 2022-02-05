package com.example.everyteamproject

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everyteamproject.com.example.everyteamproject.*
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*


class Fragment_calendar : Fragment(){

    lateinit var calendarView: CalendarView
    lateinit var selectedDate: TextView
    lateinit var save_Btn: Button
    lateinit var printSchedule: TextView

    var mDBHelper: DBHelper? = null
    private lateinit var rv_todo:RecyclerView

    //RecyclerView가 불러올 목록
    private var mAdapter: CustomAdapter?= null
    private var ScheduleItems : MutableList<ScheduleItem>? = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = view.findViewById(R.id.calendarView)
        selectedDate = view.findViewById(R.id.selectedDate)
        save_Btn = view.findViewById(R.id.save_Btn)

        rv_todo = view.findViewById(R.id.rv_todo)
        mDBHelper = DBHelper(context)
        mAdapter?.mDBHelper = mDBHelper as DBHelper

        loadRecentDB()
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_calendar, container, false)
        rv_todo = v.findViewById(R.id.rv_todo)

        // 일정 추가 버튼
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

        var date: Date? = null
        val v = context?.let { CalendarView(it) }
        v?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
            save_Btn.visibility = View.VISIBLE // 저장 버튼이 Visible
            printSchedule.visibility = View.VISIBLE // 저장된 일정 textView가 Invisible

            selectedDate.text = String.format(
                    "%d / %d / %d",
                    year,
                    month + 1,
                    dayOfMonth
            )  // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            date = Date(year, month, dayOfMonth)
        }

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

    private fun loadRecentDB() {
        // 저장되어있던 DB를 가져온다.
        ScheduleItems = mDBHelper?.getScheduleList()
        if (mAdapter == null) {
            mAdapter = context?.let { CustomAdapter(ScheduleItems, it) }
            rv_todo.setHasFixedSize(true)
            rv_todo.setAdapter(mAdapter)
        }
    }

    fun init() {
        mAdapter = context?.let { CustomAdapter(ScheduleItems, it) }
        mAdapter?.scheduleItems = ScheduleItems as MutableList<ScheduleItem>
        var linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        rv_todo?.adapter = mAdapter
        rv_todo?.layoutManager = linearLayoutManager

    }

}

