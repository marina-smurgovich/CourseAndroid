package com.yandex.smur.marina.myfinalproject.activity_web_page

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.yandex.smur.marina.myfinalproject.R
import kotlinx.android.synthetic.main.activity_web_page_with_recipe.*
import kotlinx.android.synthetic.main.activity_with_recipe.*

class ActivityWebPageWithRecipe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_page_with_recipe)

        setSupportActionBar(toolbar)

        val args: Bundle? = intent.extras
        val url: String = args!!.get("seeTheFullRecipe").toString()
        val title: String = args!!.get("seeTheFullRecipeTitle").toString()

        titleWebPage.text = title

        buttonBackFromWebPage.setOnClickListener { finish() }

        initWebView()

        loadWebPageWithUrl(url)

    }

    private fun loadWebPageWithUrl(url: String) {
        viewWebPage.loadUrl(url)

    }

    private fun initWebView() {
        with(viewWebPage) {
            settings.apply {
                loadsImagesAutomatically = true
                javaScriptEnabled = true
            }

            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    viewProgressBar.visibility = VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    viewProgressBar.visibility = GONE
                }

                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    view.loadUrl(request.url.toString())
                    return true
                }
            }

        }
    }
}