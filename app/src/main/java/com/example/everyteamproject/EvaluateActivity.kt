package com.example.everyteamproject

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.kakao.sdk.user.UserApiClient
import org.w3c.dom.Text

@Suppress("DEPRECATION")
class EvaluateActivity : AppCompatActivity() {

    lateinit var dbManager : Evaluate_db
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var evaluateRb : RatingBar
    lateinit var evaluateEditText : EditText
    lateinit var evaluate_SubmitBtn : Button
    lateinit var evaluate_CancelBtn : Button
    lateinit var userid : String
    lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate)

        evaluateRb = findViewById(R.id.evaluateRb)
        evaluateEditText = findViewById(R.id.evaluateEditText)
        evaluate_SubmitBtn = findViewById(R.id.evaluate_SubmitBtn)
        evaluate_CancelBtn = findViewById(R.id.evaluate_CancelBtn)

        dbManager = Evaluate_db(this, "evaluateDB", null,1)
        UserApiClient.instance.me { user, error ->
            userid = "${user?.id}"
            username = "${user?.kakaoAccount?.profile?.nickname}"
        }

        evaluate_SubmitBtn.setOnClickListener{

            //별점, 리뷰저장
            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO evaluateDB VALUES ('"+ userid +"', '" + username +"', '"+ evaluateRb.rating.toFloat() +"', '"+ evaluateEditText.text.toString() +"');")
            sqlitedb.close()

            //제출 후 설정페이지로 돌아가기
            Toast.makeText(this, "리뷰 남겨주셔서 감사합니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@EvaluateActivity, Evaluate_View::class.java)
            /*val intent = Intent(this@EvaluateActivity, SetActivity::class.java)*/
            startActivity(intent)
        }
        evaluate_CancelBtn.setOnClickListener{
            val intent = Intent(this@EvaluateActivity, SetActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> {
                //mainView.display
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // 하단 소프트키 없애기 (몰입모드)
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}