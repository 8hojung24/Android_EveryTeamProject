package com.example.everyteamproject

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "projects"
val COL_NAME = "projectName"
val COL_ROLE = "role"
val COL_DEADLINE = "deadline"
val COL_CLOSEINGTIME = "closingTime"
val COL_ID = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_NAME VARCHAR(50),$COL_ROLE VARCHAR(50), $COL_DEADLINE VARCHAR(50), $COL_CLOSEINGTIME VARCHAR(50))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    // 데이터 삽입
    fun insertData(project:Project_db){
        val db = this.writableDatabase  //DB를 수정하기 위해
        val cv = ContentValues()    //값을 key, value 쌍으로 put
        cv.put(COL_NAME,project.ProjectName)
        cv.put(COL_ROLE,project.Role)
        cv.put(COL_DEADLINE,project.Deadline)
        cv.put(COL_CLOSEINGTIME,project.ClosingTime)

        val result = db.insert(TABLE_NAME,null,cv)

        if(result == (-1).toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Success", Toast.LENGTH_LONG).show()
    }

    // 데이터 읽어오기
    @SuppressLint("Range")
    fun readData():MutableList<Project_db>{
        val list :MutableList<Project_db> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result: Cursor = db.rawQuery(query,null)

        if(result.moveToFirst()){
            do {
                val project = Project_db()
                project.id = result.getInt(result.getColumnIndex(COL_ID))
                project.ProjectName = result.getString(result.getColumnIndex(COL_NAME))
                project.Role = result.getString(result.getColumnIndex(COL_ROLE))
                project.Deadline = result.getString(result.getColumnIndex(COL_DEADLINE))
                project.ClosingTime = result.getString(result.getColumnIndex(COL_CLOSEINGTIME))
                list.add(project)
            }while (result.moveToNext())
        }else
            Toast.makeText(context,"There is no data.",Toast.LENGTH_LONG).show()

        result.close()
        db.close()
        return list
    }

    fun deleteData(){
        val db = this.writableDatabase
        //db.delete(TABLE_NAME, "$COL_ID=?", arrayOf("1")) 특정 레코드 삭제
        db.delete(TABLE_NAME, null,null) //해당 레코드 전체 삭제
        db.close()
    }
}