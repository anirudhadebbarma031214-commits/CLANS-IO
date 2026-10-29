package com.anistudio.clansio

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {
    private lateinit var gameView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
        gameView = WebView(this)
        gameView.setBackgroundColor(android.graphics.Color.rgb(16, 26, 24))
        gameView.settings.javaScriptEnabled = true
        gameView.settings.domStorageEnabled = true
        gameView.settings.allowFileAccess = true
        gameView.settings.allowContentAccess = true
        gameView.webChromeClient = WebChromeClient()
        gameView.webViewClient = WebViewClient()
        setContentView(gameView)
        gameView.loadUrl("file:///android_asset/index.html")
    }

    @Deprecated("Deprecated in Android API")
    override fun onBackPressed() {
        if (::gameView.isInitialized && gameView.canGoBack()) gameView.goBack() else super.onBackPressed()
    }

    override fun onDestroy() {
        if (::gameView.isInitialized) gameView.destroy()
        super.onDestroy()
    }
}
