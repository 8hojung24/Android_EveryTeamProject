package com.example.everyteamproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment

class Fragment_matching : Fragment() {

    lateinit var webView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_matching, container, false)

        webView = v.findViewById(R.id.webView)
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        registerForContextMenu(webView)
        webView.loadUrl("https://www.when2meet.com/")

        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        /*wide viewport를 사용하도록 설정*/
        webView.getSettings().setUseWideViewPort(true);
        /*스크린 크기에 맞게 조정*/
        webView.getSettings().setLoadWithOverviewMode(true);
        /*zoom 허용*/
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);

        return v
    }
}
