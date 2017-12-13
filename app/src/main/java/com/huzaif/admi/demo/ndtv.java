package com.huzaif.admi.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by admi on 21-04-2017.
 */
public class ndtv extends Fragment {
    WebView tech2;
    ProgressBar progress;
    ImageView imag;
    public ndtv() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.frag1, container, false);

        tech2=(WebView)rootView.findViewById(R.id.webView);
        imag=(ImageView)rootView.findViewById(R.id.imag);
        imag.setImageResource(R.drawable.ndtv);
        progress=(ProgressBar)rootView.findViewById(R.id.progressBar);
        WebSettings setting=tech2.getSettings();

        tech2.setWebViewClient(new myweb()
        {
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                rootView.findViewById(R.id.imag).setVisibility(View.GONE);
                //show webview
                rootView. findViewById(R.id.webView).setVisibility(View.VISIBLE);
            }});
        setting.setJavaScriptEnabled(true);
        tech2.getSettings().setDomStorageEnabled(true);
        tech2.loadUrl("https://www.ndtv.com");

        return rootView;
    }
    public class myweb extends WebViewClient
    {
        public myweb() {
            super();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // return super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            ndtv.this.progress.setProgress(0);
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progress.setVisibility(View.INVISIBLE);
            // progress.setVisibility(View.GONE);
            ndtv.this.progress.setProgress(100);

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }




        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }
    }





}


