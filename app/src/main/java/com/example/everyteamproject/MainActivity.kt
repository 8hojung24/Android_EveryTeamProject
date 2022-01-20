package com.example.everyteamproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.kakao.sdk.common.util.Utility
import android.app.Application
import com.kakao.sdk.common.KakaoSdk

/* <solid android:color="#6D9773" /> // 배경색 */

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var mainView: View
    lateinit var backButton: Button
    lateinit var addView: ImageView
    lateinit var explainText: TextView
    lateinit var calendarView: CalendarView

    var fname: String = ""
    var str: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.mainView)
        backButton = findViewById(R.id.backButton)
        addView = findViewById(R.id.addView)
        explainText = findViewById(R.id.explainText)
        calendarView = findViewById(R.id.calendarView)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        /*
        addView.bringToFront()
        explainText.bringToFront()
        */

        /*
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
// 달력 날짜가 선택되면
            diaryTextView.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
            save_Btn.visibility = View.VISIBLE // 저장 버튼이 Visible
            contextEditText.visibility = View.VISIBLE // EditText가 Visible
            textView2.visibility = View.INVISIBLE // 저장된 일기 textView가 Invisible
            cha_Btn.visibility = View.INVISIBLE // 수정 Button이 Invisible
            del_Btn.visibility = View.INVISIBLE // 삭제 Button이 Invisible

            diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
// 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            contextEditText.setText("") // EditText에 공백값 넣기

            checkedDay(year, month, dayOfMonth) // checkedDay 메소드 호출


        }
        */

    }


    // 하단 소프트키 없애기 (몰입모드)
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }


    // 메뉴바 -> 몰입모드 실행 시 필요 X
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_settings -> {
                //mainView.display
                return true
            }
            R.id.action_edit -> {
                //mainView.display
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "1bb17a51c07ce090a59cb0cf97c10379")
    }
}