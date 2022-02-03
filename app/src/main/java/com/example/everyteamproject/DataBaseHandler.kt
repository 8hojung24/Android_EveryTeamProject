package com.example.everyteamproject.com.example.everyteamproject

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.everyteamproject.com.example.everyteamproject.project

class DataBaseHandler(var context: Context?) : SQLiteOpenHelper(context, "MyDB2",null,1){
    lateinit var sqliteDB: SQLiteDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE IF NOT EXISTS ProjectDB (id INTEGER PRIMARY KEY AUTOINCREMENT, ProjectName TEXT NOT NULL, Role TEXT NOT NULL, DeadLine TEXT NOT NULL, ClosingTime TEXT NOT NULL)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //db!!.execSQL("DROP TABLE IF EXISTS ProjectDB")
        //onCreate(db)
    }

    @SuppressLint("Range")
    fun getProjectsList(): MutableList<project> {
        val projects :MutableList<project> = ArrayList()

        sqliteDB = this.readableDatabase
        val cursor : Cursor = sqliteDB.rawQuery("Select * from ProjectDB", null)
        if (cursor.count != 0) {
            while (cursor.moveToNext()) {
                val project = project()

                project.id= cursor.getInt(cursor.getColumnIndex("id"))
                project.ProjectName = cursor.getString(cursor.getColumnIndex("ProjectName"))
                project.Role = cursor.getString(cursor.getColumnIndex("Role"))
                project.DeadLine = cursor.getString(cursor.getColumnIndex("DeadLine"))
                project.ClosingTime = cursor.getString(cursor.getColumnIndex("ClosingTime"))

                projects.add(project)
            }
        }
        cursor.close()

        return projects
    }


    // INSERT 문
    fun Insert(_ProjectName : String, _Role : String, _DeadLine : String, _ClosingTime : String) {
        sqliteDB = this.writableDatabase

        sqliteDB.execSQL("INSERT INTO ProjectDB (ProjectName, Role, DeadLine, ClosingTime) VALUES ('" + _ProjectName + "','" + _Role + "', '" + _DeadLine + "', '" + _ClosingTime  + "');")
    }

    // UPDATE 문 (프로젝트 목록을 수정 한다.)
    fun Update(_ProjectName : String, _Role : String, _DeadLine : String, _ClosingTime : String, _id: Int){
        sqliteDB = this.writableDatabase

        sqliteDB.execSQL("UPDATE ProjectDB SET ProjectName = '"+ _ProjectName +"', Role = '"+ _Role +"', DeadLine = '"+ _DeadLine +"', endTime = '"+ _ClosingTime  +"', WHERE id ='" + _id +"'")
    }

    // DELETE 문 (프로젝트 목록을 제거 한다.)
    fun Delete(_id : Int){
        sqliteDB = this.writableDatabase
        sqliteDB.execSQL("DELETE FROM ProjectDB WHERE id = '"+ _id + "'")
    }
}