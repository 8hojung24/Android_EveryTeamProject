package com.example.everyteamproject

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color.*
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_evaluate_view.*

class Evaluate_View : AppCompatActivity() {

    lateinit var dbManager: Evaluate_db
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout
    lateinit var average_scoreText: TextView
    lateinit var average_scoreView: RatingBar
    lateinit var numberOfReview :TextView

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_view)

        dbManager = Evaluate_db(this, "evaluateDB", null, 1)
        sqlitedb = dbManager.readableDatabase
        layout = findViewById(R.id.evaluateDB)
        average_scoreText = findViewById(R.id.average_scoreText)
        average_scoreView = findViewById(R.id.average_scoreView)
        numberOfReview = findViewById(R.id.numberOfEvaluate)

        var cursor: Cursor
        var cursor2: Cursor
        var cursor3 : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM evaluateDB ", null)
        cursor2 = sqlitedb.rawQuery("SELECT (AVG(score)) from evaluateDB", null)
        cursor3 = sqlitedb.rawQuery("SELECT COUNT(*) FROM evaluateDB", null)

        while(cursor2.moveToNext()) {
            average_scoreText.text = String.format("%.2f", (cursor2.getString(0).toFloat()))
            average_scoreView.setRating(cursor2.getString(0).toFloat())
        }

        while(cursor3.moveToNext()) {
            numberOfReview.text = cursor3.getInt(0).toString() + "명"
        }

        var num: Int = 0
        while (cursor.moveToNext()) {
            var personal_name = cursor.getString((cursor.getColumnIndex("personal_name"))).toString()
            var score = cursor.getFloat(cursor.getColumnIndex("score"))
            var review = cursor.getString((cursor.getColumnIndex("review"))).toString()

            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.setPadding(30, 40, 30, 40)
            layout_item.id = num
            layout_item.tag = num

            var layout_item2: LinearLayout = LinearLayout(this)
            layout_item2.orientation = LinearLayout.HORIZONTAL
            layout_item2.setPadding(0, 20, 0, 0)
            layout_item2.id = num
            layout_item2.tag = num

            var tvid: TextView = TextView(this)
            tvid.text = personal_name
            tvid.setTextColor(DKGRAY)
            layout_item.addView(tvid)

            var Rvscore: RatingBar = RatingBar(this)
            Rvscore.setNumStars(5)
            Rvscore.setMax(5)
            Rvscore.setStepSize(0.5f)
            Rvscore.rating = score.toFloat()
            Rvscore.setIsIndicator(true)
            layout_item2.addView(Rvscore)
            layout_item.addView(layout_item2)

            var tvreview: TextView = TextView(this)
            tvreview.text = review
            tvreview.setTextColor(BLACK)
            layout_item.addView(tvreview)

            layout.addView(layout_item)
            num++
        }
        cursor.close()
        cursor2.close()
        cursor3.close()
        sqlitedb.close()
        dbManager.close()

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

    override fun onBackPressed() {
        startActivity(Intent(this, SetActivity::class.java))
        finish()
    }
}