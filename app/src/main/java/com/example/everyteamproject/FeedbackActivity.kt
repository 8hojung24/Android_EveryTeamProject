package com.example.everyteamproject

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import kotlinx.android.synthetic.main.activity_evaluate.*


@Suppress("DEPRECATION")
class FeedbackActivity : AppCompatActivity() {

    lateinit var feedbackTitleText: EditText
    lateinit var feedbackEditText: EditText
    lateinit var attachFileImg: ImageView
    lateinit var feedback_SubmitBtn: Button
    lateinit var feedback_CancelBtn: Button
    lateinit var attachImg1: ImageView
    lateinit var selectedImageUri: Uri
    private var REQUEST_READ_EXTERNAL_STORAGE = 1
    var Gallery = 0 //request code


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        feedbackTitleText = findViewById(R.id.feedbackTitleText)
        feedbackTitleText.setSelection(feedbackTitleText.getText().length);
        feedbackEditText = findViewById(R.id.feedbackEditText)
        feedback_SubmitBtn = findViewById(R.id.feedback_SubmitBtn)
        feedback_CancelBtn = findViewById(R.id.feedback_CancelBtn)
        attachFileImg = findViewById(R.id.attachFileImg)
        attachImg1 = findViewById(R.id.attachImg1)

        attachFileImg.setOnClickListener {
            //파일첨부 권한 확인
            if (ContextCompat.checkSelfPermission(
                            this,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                ) {
                    var dlg = AlertDialog.Builder(this)
                    dlg.setTitle("모두의 팀플")
                    dlg.setMessage("다음 작업을 허용하시겠습니까? 기기 사진,미디어,파일 엑세스")
                    dlg.setPositiveButton("확인") { dialog, which ->
                        ActivityCompat.requestPermissions(
                                this@FeedbackActivity,
                                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                                REQUEST_READ_EXTERNAL_STORAGE
                        )
                    }
                    dlg.setNegativeButton("취소", null)
                    dlg.show()
                } else {
                    //처음 권한 요청
                    ActivityCompat.requestPermissions(
                            this@FeedbackActivity,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_READ_EXTERNAL_STORAGE
                    )
                }
            } else {
                //권한이 이미 제대로 허용
                // 파일첨부
                val intent = Intent()
                intent.type = "image/*"
                intent.setAction(Intent.ACTION_GET_CONTENT)

                startActivityForResult(intent, Gallery)
            }
        }

        //확인버튼 누를 시
        feedback_SubmitBtn.setOnClickListener {
            val send_intent = Intent(Intent.ACTION_SENDTO)
            send_intent.data = Uri.parse("mailto:abc123@naver.com")
            send_intent.putExtra(Intent.EXTRA_SUBJECT, feedbackTitleText.text.toString())
            send_intent.putExtra(Intent.EXTRA_TEXT, feedbackEditText.text.toString())

            //이미지가 있다면
            if (selectedImageUri != null) {
                send_intent.putExtra(Intent.EXTRA_STREAM, selectedImageUri.toString())
            }

            if (send_intent.resolveActivity(packageManager) != null) {
                startActivity(send_intent)
            } else {
                Toast.makeText(this, "메일을 전송할 수 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
        //취소버튼 누를시
        feedback_CancelBtn.setOnClickListener {
            val intent = Intent(this@FeedbackActivity, SetActivity::class.java)
            startActivity(intent)
        }
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> {
                //mainView.display
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //이미지 uri를 이미지뷰에 넣기 / uri 저장
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Gallery && resultCode == RESULT_OK && data != null && data.data != null) {
            var selectedImageUri: Uri = data.data!!
            attachImg1.setImageURI(selectedImageUri);
        }
    }

    /*// 하단 소프트키 없애기 (몰입모드)
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
    }*/

    // 메뉴바 -> 몰입모드 실행 시 필요 X
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}




