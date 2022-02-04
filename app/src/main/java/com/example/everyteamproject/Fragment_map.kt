package com.example.everyteamproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment

class Fragment_map : Fragment() {

    lateinit var webView: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        webView = v.findViewById(R.id.webView)
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }
        registerForContextMenu(webView)
        webView.loadUrl("https://m.map.kakao.com/")

        return v
    }
}
