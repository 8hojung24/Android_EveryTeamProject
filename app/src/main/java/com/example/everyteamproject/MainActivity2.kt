package com.example.everyteamproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = findViewById(R.id.bottomNavi) as BottomNavigationView

        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        // navi_menu.xml 에서 설정했던 각 아이템들의 id를 통해 알맞은 프래그먼트로 변경하게 한다.
        bnv_main.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_home -> {
                    val intent = Intent(this@MainActivity2, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_matching -> {
                    val settingFragment = Fragment_matching()
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame, settingFragment).commit()
                }
                R.id.action_mypage -> {
                    val boardFragment = Fragment_mypage()
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame, boardFragment).commit()
                }
                R.id.action_calendar -> {
                    val settingFragment = Fragment_calendar()
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame, settingFragment).commit()
                }
                R.id.action_map -> {
                    val boardFragment = Fragment_map()
                    supportFragmentManager.beginTransaction().replace(R.id.main_frame, boardFragment).commit()
                }
            }
            true
        }
            selectedItemId = R.id.action_mypage
        }
    }
}