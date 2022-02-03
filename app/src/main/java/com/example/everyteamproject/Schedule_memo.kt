package com.example.everyteamproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_schedule_memo.*
import java.io.FileInputStream
import java.io.FileOutputStream

class Schedule_memo : AppCompatActivity() {
    var fname: String = ""
    var str: String = ""

    lateinit var saveBtn: Button
    lateinit var delBtn: Button
    lateinit var Memo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_memo)

        saveBtn = findViewById<Button>(R.id.saveBtn)
        delBtn = findViewById<Button>(R.id.delBtn)

        // checkedDay(year, month, dayOfMonth) // 저장된 메모를 볼 수 있는 checkedDay 메소드 호출

        saveBtn.setOnClickListener { // 저장 Button이 클릭되면
            saveDiary(fname) // saveDiary 메소드 호출
            val toast = Toast.makeText(applicationContext, fname + "데이터를 저장했습니다.", Toast.LENGTH_SHORT) // 토스트 메세지
            toast.show()
        }
    }

    fun checkedDay(cYear: Int, cMonth: Int, cDay: Int) {
        fname = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt" // 저장할 파일 이름 설정. Ex) 2019-01-20.txt
        var fis: FileInputStream? = null // FileStream fis 변수 설정

        try {
            fis = openFileInput(fname) // fname 파일 오픈!!

            val fileData = ByteArray(fis.available()) // fileData에 파이트 형식으로 저장
            fis.read(fileData) // fileData를 읽음
            fis.close()

            str = String(fileData) // str 변수에 fileData를 저장


            delBtn.setOnClickListener {
                Memo.setText("")
                removeDiary(fname)
                val toast = Toast.makeText(applicationContext, fname + "데이터를 삭제했습니다.", Toast.LENGTH_SHORT) // 토스트 메세지
                toast.show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("WrongConstant")
    fun saveDiary(readyDay: String) {
        var fos: FileOutputStream? = null

        try {
            fos = openFileOutput(readyDay, MODE_NO_LOCALIZED_COLLATORS)
            var content: String = Memo.getText().toString()
            fos.write(content.toByteArray())
            fos.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @SuppressLint("WrongConstant")
    fun removeDiary(readyDay: String) {
        var fos: FileOutputStream? = null

        try {
            fos = openFileOutput(readyDay, MODE_NO_LOCALIZED_COLLATORS)
            var content: String = ""
            fos.write(content.toByteArray())
            fos.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}