package com.example.everyteamproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Evaluate_db(context : Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version){
    override fun onCreate(db: SQLiteDatabase?){
        db!!.execSQL("CREATE TABLE evaluateDB (personal_key text, score INNTEGER, review text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion:Int){

    }
}