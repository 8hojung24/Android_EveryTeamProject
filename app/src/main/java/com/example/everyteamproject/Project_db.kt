package com.example.everyteamproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 팀원명:역할 어떻게 받아올지 수정
// 마감일과 마감시간 data type 수정

class Project_db(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE projects(ProjectName text, Role text, DeadLine text, ClosingTime text)")
    }

    //갱신
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}