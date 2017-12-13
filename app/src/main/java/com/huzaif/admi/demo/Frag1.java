package com.huzaif.admi.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by admi on 21-04-2017.
 */
public class Frag1 extends Fragment {
    WebView tech2;
    ProgressBar progress;
    ImageView image;
    SwipeRefreshLayout mySwipeRefreshLayout;

    public Frag1() {
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
        tech2.setLayerType(rootView.LAYER_TYPE_HARDWARE,null);
        tech2.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        tech2.setWebChromeClient(new WebChromeClient());
       // tech2.getSettings().setJavaScriptEnabled(true);
        tech2.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        image=(ImageView)rootView.findViewById(R.id.imag);
        image.setImageResource(R.drawable.fb);
        mySwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeContainer);
        progress=(ProgressBar)rootView.findViewById(R.id.progressBar);
        WebSettings setting=tech2.getSettings();
        tech2.setWebViewClient(new myweb());
        setting.setJavaScriptEnabled(true);
        tech2.getSettings().setDomStorageEnabled(true);
        tech2.loadUrl("https://www.google.com");

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        tech2.reload();
                        mySwipeRefreshLayout .setRefreshing(false);
                    }
                }
        );

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
            Frag1.this.progress.setProgress(0);
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            image.setVisibility(View.GONE);
            tech2.setVisibility(View.VISIBLE);

            progress.setVisibility(View.INVISIBLE);
            // progress.setVisibility(View.GONE);
            Frag1.this.progress.setProgress(100);

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


