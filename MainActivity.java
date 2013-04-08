package com.example.sample_webviewtargetblank;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  webView = (WebView) findViewById(R.id.webView1);
  webView.getSettings().setJavaScriptEnabled(true);

  // スクロールバーをWebViewの中に表示
  webView.setVerticalScrollbarOverlay(true);

  // UIWebViewのデフォルトViewportがでかいのでfitさせる
  // via http://d.hatena.ne.jp/black-vinegar/20120305/p1
  webView.getSettings().setUseWideViewPort(true);
  webView.getSettings().setLoadWithOverviewMode(true);

  webView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
    System.out.println("request uri - " + url);

    return false;
      }
  });
  webView.setWebChromeClient(new WebChromeClient() {
      @Override
      // ここがないとalert自体が表示されない
      // これはAndroidのWebViewのデフォルトの仕組み
      public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
    return false;
      }
  });

  webView.loadUrl("http://hogehoge.com/");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
  getMenuInflater().inflate(R.menu.activity_main, menu);
  return true;
    }
}
