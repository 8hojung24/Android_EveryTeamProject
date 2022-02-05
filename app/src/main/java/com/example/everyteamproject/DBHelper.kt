package com.example.everyteamproject.com.example.everyteamproject

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.everyteamproject.Fragment_calendar

class DBHelper(var context: Context?) : SQLiteOpenHelper(context, "mySQL",null,1){
    lateinit var sqlDB: SQLiteDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE IF NOT EXISTS ScheduleDB (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, date TEXT NOT NULL, startTime TEXT NOT NULL, endTime TEXT NOT NULL, place TEXT NOT NULL)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }
    // SELECT 문 (할일 목록들을 조회)
    @SuppressLint("Range")
    fun getScheduleList(): MutableList<ScheduleItem> {
        val ScheduleItems :MutableList<ScheduleItem> = ArrayList()

        sqlDB = this.readableDatabase
        val cursor : Cursor = sqlDB.rawQuery("Select * from ScheduleDB", null)
        if (cursor.count != 0) {
            while (cursor.moveToNext()) {
                val scheduleItem = ScheduleItem()

                scheduleItem.id= cursor.getInt(cursor.getColumnIndex("id"))
                scheduleItem.title = cursor.getString(cursor.getColumnIndex("title"))
                scheduleItem.date = cursor.getString(cursor.getColumnIndex("date"))
                scheduleItem.startTime = cursor.getString(cursor.getColumnIndex("startTime"))
                scheduleItem.endTime = cursor.getString(cursor.getColumnIndex("endTime"))
                scheduleItem.place = cursor.getString(cursor.getColumnIndex("place"))

                ScheduleItems.add(scheduleItem)
            }
        }
        cursor.close()

        return ScheduleItems
    }


    // INSERT 문
    fun Insert(_title : String, _date : String, _startTime : String, _endTime : String, _place : String) {
        sqlDB = this.writableDatabase

        sqlDB.execSQL("INSERT INTO ScheduleDB (title, date, startTime, endTime, place) VALUES ('" + _title + "','" + _date + "', '" + _startTime + "', '" + _endTime + "', '" + _place + "');")
    }

    // UPDATE 문 (할일 목록을 수정 한다.)
    fun Update(_title : String, _date : String, _startTime : String, _endTime : String, _place : String, _id : Int){
        sqlDB = this.writableDatabase

        sqlDB.execSQL("UPDATE ScheduleDB SET title = '"+ _title +"', date = '"+ _date +"', startTime = '"+ _startTime +"', endTime = '"+ _endTime +"', place = '"+ _place +"', WHERE id ='" + _id +"'")
    }

    // DELETE 문 (할일 목록을 제거 한다.)
    fun Delete(_id : Int){
        sqlDB = this.writableDatabase
        sqlDB.execSQL("DELETE FROM ScheduleDB WHERE id = '"+ _id + "'")
    }


}