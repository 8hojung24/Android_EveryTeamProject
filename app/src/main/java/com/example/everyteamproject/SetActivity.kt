package com.example.everyteamproject

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient


@Suppress("DEPRECATION")
class SetActivity : AppCompatActivity() {

    lateinit var backBtn: Button
    lateinit var logout: Button
    lateinit var feedback: Button
    lateinit var evaluate: Button
    lateinit var developerIntro: Button
    lateinit var developerEmail : Button
    lateinit var name : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set)

        backBtn = findViewById(R.id.backBtn)
        logout = findViewById(R.id.logout)
        feedback = findViewById(R.id.feedback)
        evaluate = findViewById(R.id.evaluate)
        developerIntro = findViewById(R.id.developerIntro)
        developerEmail = findViewById(R.id.developerEmail)

        // 뒤로가기 버튼 클릭시 MainActivity 로 이동
        backBtn.setOnClickListener {
            val intent = Intent(this@SetActivity, MainActivity::class.java)
            startActivity(intent)
        }

        //카카오톡 닉네임으로 설정
        val nickname = findViewById<TextView>(R.id.name) // 로그인 버튼
        UserApiClient.instance.me { user, error ->
            nickname.text = "${user?.kakaoAccount?.profile?.nickname}"
        }

        // 로그아웃 버튼 클릭시 LoginActivity 로 이동
        logout.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if(error!=null){
                    Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                finish()
                }
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


        // 개발자 이메일 보여주기
        developerEmail.setOnClickListener {
            var dlg = AlertDialog.Builder(this)
            dlg.setTitle("모두의 팀플 E-mail")
            dlg.setMessage("abc123@naver.com")
            dlg.setPositiveButton("확인"){ dialog, i: Int ->
                //확인버튼을 누르면 개발자 이메일주소 클립보드에 복사
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label", "abc123@naver.com")
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this@SetActivity, "복사되었습니다.", Toast.LENGTH_SHORT).show()
            }
            dlg.show()
        }
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