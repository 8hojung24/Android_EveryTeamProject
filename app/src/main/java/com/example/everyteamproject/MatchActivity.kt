package com.example.everyteamproject

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MatchActivity : AppCompatActivity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        webView = findViewById(R.id.webView)
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        registerForContextMenu(webView)
        webView.loadUrl("https://www.when2meet.com/")
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }
}
