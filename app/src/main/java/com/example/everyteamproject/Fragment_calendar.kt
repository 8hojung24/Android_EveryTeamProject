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
import java.util.*


class Fragment_calendar : Fragment(){

    lateinit var mcalendarView: CalendarView
    lateinit var selectedDate: TextView
    lateinit var save_Btn: Button

    var mDate = ""
    var mDBHelper: DBHelper? = null
    private lateinit var rv_todo:RecyclerView

    //RecyclerView가 불러올 목록
    private var mAdapter: CustomAdapter?= null
    private var ScheduleItems : MutableList<ScheduleItem>? = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mcalendarView = view.findViewById(R.id.calendarView)
        selectedDate = view.findViewById(R.id.selectedDate)
        save_Btn = view.findViewById(R.id.save_Btn)

        rv_todo = view.findViewById(R.id.rv_todo)
        mDBHelper = DBHelper(context)
        mAdapter?.mDBHelper = mDBHelper as DBHelper

        // 캘린더 구현
        mcalendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
            selectedDate.visibility = View.VISIBLE
            mDate = "${i}/${i2+1}/${i3}"
            selectedDate.text = mDate
            loadRecentDB()
            init()
        }


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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun loadRecentDB() {
        // 저장되어있던 DB를 가져온다.
        ScheduleItems = mDBHelper?.selectData(mDate)
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

