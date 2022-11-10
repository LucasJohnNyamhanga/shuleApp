package shule.co.shule;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    LinearLayout splashScreen;
    WebView webView;
    TextView shuleText;
    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shuleText = findViewById(R.id.shuleText);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);
        splashScreen = findViewById(R.id.splashScreen);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.loadUrl("https://www.shule.co.tz/");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("https://www.shule.co.tz/");
                progressBar.setVisibility(View.VISIBLE);
                shuleText.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            }
        });
    }




    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            splashScreen.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            if (request.getUrl().toString().equalsIgnoreCase(view.getUrl())) {
                // showInternetConnectionError()
                shuleText.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);
                splashScreen.setVisibility(View.VISIBLE);
            }
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {

            super.onPageCommitVisible(view, url);
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.isFocused() && webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();}
    }
}