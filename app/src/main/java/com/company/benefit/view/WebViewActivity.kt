package com.company.benefit.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.company.benefit.databinding.ActivityWebviewBinding
import java.lang.Exception

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    private lateinit var canvas: Canvas

    private var previewWidth = 0
    private var previewHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    @SuppressLint("ClickableViewAccessibility", "SetJavaScriptEnabled")
    private fun init() {

        val webSetting = binding.webView.settings
        webSetting.javaScriptEnabled = true // allow the js

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) // 화면이 계속 켜짐
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER

        binding.webView.apply {

            webViewClient = object : WebViewClient(){

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {

                    Log.d("WebViewActivity", "url : $url")

                    if (url?.startsWith("intent:") == true || url?.startsWith("naversearchapp:") == true) {
                        try {
                            val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                            val existPackage =
                                intent.getPackage()
                                    ?.let { packageManager.getLaunchIntentForPackage(it) };
                            if (existPackage != null) {
                                startActivity(intent);
                            } else {
                                val marketIntent = Intent(Intent.ACTION_VIEW);
                                marketIntent.data = Uri.parse("market://details?id=" + intent.getPackage());
                                startActivity(marketIntent);
                            }
                            return true;
                        } catch (e : Exception) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.d("WebViewActivity", "shouldOverrideUrlLoading called.")
                        loadUrl(request?.url.toString())
                    }

                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {

                    Log.d("WebViewActivity", "onPageFinished called. ${view?.progress}")
                    if(view?.progress == 100) {
                        paint()
                    }

                    super.onPageFinished(view, url)
                }
            }

        }?.run {
            loadUrl("https://search.naver.com/search.naver?where=nexearch&sm=top_sly.hst&fbm=1&acr=2&ie=utf8&query=%EC%BB%A8%EB%B2%84%EC%8A%A4%ED%99%94")
        }


        binding.webView.setOnTouchListener { _, event ->

            Log.d("WebViewActivity", "touch 좌표 x : ${event.x} / y : ${event.y}")

            if (event?.action == MotionEvent.ACTION_DOWN) {
                Log.d("WebViewActivity", "touch 좌표 x : ${event.x} / y : ${event.y}")
            }
            true
        }


    }

    // layout 이 구성될때 실행다는 함수.
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        previewWidth = binding.preview.width
        previewHeight = binding.preview.height

        init()

    }

    private fun paint() {

        val previewBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Bitmap.Config.ARGB_8888)

        // 카드 영역 그려주기
        canvas = Canvas()
        canvas.setBitmap(previewBitmap)

        val paint = Paint()


        paint.apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 10F
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            alpha = 100
        }


        val rect = RectF()

        rect.set(640F,
            970F,
            690F,
            1020F)

        canvas.drawRect(rect, paint)

        binding.guideline.setImageBitmap(previewBitmap)

    }

}