package com.example.everyteamproject

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MapActivity: AppCompatActivity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        webView = findViewById(R.id.webView)
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        registerForContextMenu(webView)
        webView.loadUrl("https://m.map.kakao.com/")
    }
}
