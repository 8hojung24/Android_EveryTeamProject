package com.example.everyteamproject

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.Color.*
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Evaluate_View : AppCompatActivity() {

    lateinit var dbManager: Evaluate_db
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate_view)

        dbManager = Evaluate_db(this, "evaluateDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        layout = findViewById(R.id.evaluateDB)

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM evaluateDB", null)

        var num: Int = 0
        while (cursor.moveToNext()) {
            var personal_key = cursor.getString((cursor.getColumnIndex("personal_key"))).toString()
            var score = cursor.getInt((cursor.getColumnIndex("score")))
            var review = cursor.getString((cursor.getColumnIndex("review"))).toString()

            var layout_item: LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.setPadding(20, 10, 20, 10)
            layout_item.id = num
            layout_item.setTag(num)

            var tvid: TextView = TextView(this)
            tvid.text = personal_key
            layout_item.addView(tvid)

            var Rvscore: RatingBar = RatingBar(this)
            Rvscore.setRating(score.toFloat())
            Rvscore.setIsIndicator(true)
            Rvscore.setNumStars(5)
            Rvscore.setStepSize(0.5F)
            layout_item.addView(Rvscore)

            var tvscore : TextView = TextView(this)
            tvscore.text = score.toString() + " 점"
            layout_item.addView(tvscore)

            var tvreview: TextView = TextView(this)
            tvreview.text = review
            tvreview.setTextColor(BLACK)
            layout_item.addView(tvreview)

            layout.addView(layout_item)
            num++;
        }
        cursor.close()
        sqlitedb.close()
        dbManager.close()

    }
}