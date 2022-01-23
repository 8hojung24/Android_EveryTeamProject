package com.example.everyteamproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

@Suppress("DEPRECATION")
class SetActivity : AppCompatActivity() {

    lateinit var backBtn: Button
    lateinit var logout: Button
    lateinit var feedback: Button
    lateinit var evaluate: Button
    lateinit var developerIntro: Button
    lateinit var developerEmail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set)

        backBtn = findViewById(R.id.backBtn)
        logout = findViewById(R.id.logout)
        feedback = findViewById(R.id.feedback)
        evaluate = findViewById(R.id.evaluate)
        developerIntro = findViewById(R.id.developerIntro)
        //developerEmail = findViewById(R.id.developerEmail)

        // 뒤로가기 버튼 클릭시 MainActivity 로 이동
        backBtn.setOnClickListener {
            val intent = Intent(this@SetActivity, MainActivity::class.java)
            startActivity(intent)
        }

        // 로그아웃 버튼 클릭시 LoginActivity 로 이동
        logout.setOnClickListener {
            val intent = Intent(this@SetActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // 피드백 버튼 클릭시 FeedbackActivity 로 이동
        feedback.setOnClickListener {
            val intent = Intent(this@SetActivity, FeedbackActivity::class.java)
            startActivity(intent)
        }

        // 평가 버튼 클릭시 EvaluateActivity 로 이동
        evaluate.setOnClickListener {
            val intent = Intent(this@SetActivity, EvaluateActivity::class.java)
            startActivity(intent)
        }

        // 개발자 소개 버튼 클릭시 IntroActivity 로 이동
        developerIntro.setOnClickListener {
            val intent = Intent(this@SetActivity, IntroActivity::class.java)
            startActivity(intent)
        }


        /*
        // 개발자 이메일 버튼 클릭시 메일 보내기로 이동
        developerEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:example@example.com")
            if(intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }*/
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