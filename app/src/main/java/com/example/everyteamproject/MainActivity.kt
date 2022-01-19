package com.example.everyteamproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var mainView: View
    lateinit var backButton: Button
    lateinit var addView: ImageView
    lateinit var explainText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.mainView)
        backButton = findViewById(R.id.backButton)
        addView = findViewById(R.id.addView)
        explainText = findViewById(R.id.explainText)

        /*
        addView.bringToFront()
        explainText.bringToFront()
        */

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