package com.example.everyteamproject

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kakao.sdk.common.util.Utility
import me.relex.circleindicator.CircleIndicator3


/* <solid android:color="#6D9773" /> // 배경색 */

private val mPager: ViewPager2? = null
private var pagerAdapter: FragmentStateAdapter? = null
private const val num_page = 6
private val mIndicator: CircleIndicator3? = null

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var mainView: View
    lateinit var calendarView: CalendarView
    lateinit var profileImg: ImageView
    lateinit var viewpager: ViewPager2
    lateinit var indicator: CircleIndicator3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.mainView)
        calendarView = findViewById(R.id.calendarView)
        profileImg = findViewById(R.id.profileImg)
        viewpager = findViewById(R.id.viewpager)
        indicator = findViewById(R.id.indicator)

        pagerAdapter = MyAdapter(this, num_page)
        viewpager.setAdapter(pagerAdapter)

        indicator.setViewPager(viewpager)
        indicator.createIndicators(num_page, 0)

        viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        viewpager.setCurrentItem(1000)
        viewpager.setOffscreenPageLimit(3)

        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                indicator.animatePageSelected(position%num_page)
            }
        })

        val pageMargin: Int = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pageOffset: Int = resources.getDimensionPixelOffset(R.dimen.offset)

        viewpager.setPageTransformer(object: ViewPager2.OnPageChangeCallback(),
            ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val myOffset: Float = position * -(2 * pageOffset + pageMargin)
                if(viewpager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if(ViewCompat.getLayoutDirection(viewpager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset)
                    } else {
                        page.setTranslationX(myOffset)
                    }
                } else {
                    page.setTranslationY(myOffset)
                }
            }

        })

        // 카카오 로그인 hash 키
        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)


        // 프로필 사진 설정
        profileImg.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==
                        PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else {
                    pickImageFromGallery()
                }
            }
            else {
                pickImageFromGallery()
            }
        }
    }


    class ZoomOutPageTransformer : ViewPager2.PageTransformer {

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }

    // 프로필 사진 설정
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }
    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            PERMISSION_CODE -> {
                if(grantResults.size >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            profileImg.setImageURI(data?.data)
        }
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

    // 설정 액션바 -> SetActivity   편집 액션바 -> Bottom_sheet_layout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)

        when(item?.itemId){
            R.id.action_settings -> {
                val intent = Intent(this@MainActivity, SetActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_edit -> {
                bottomSheetDialog.show()
                //val intent = Intent(this@MainActivity, Bottom_sheet_layout::class.java)
                //startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

/*
// LoginActivity.kt로 class 이동
// 카카오 로그인
class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "1bb17a51c07ce090a59cb0cf97c10379")
    }
}*/