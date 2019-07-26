package com.success.alc4phase1;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class AboutALC extends AppCompatActivity {
    private WebView aboutWeb;
    private Group errorGroup;
    private ProgressBar pbLoading;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_a_l_c);

        swipe = findViewById(R.id.swipe);
        errorGroup = findViewById(R.id.group);
        pbLoading = findViewById(R.id.loading);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            pbLoading.setBackgroundColor(getResources().getColor(R.color.colorButtonNormal));
//        }

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webAction();
            }
        });

        ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

        if (mNetworkInfo != null && mNetworkInfo.isConnected()) {

            pbLoading.setVisibility(View.VISIBLE);
            errorGroup.setVisibility(View.GONE);
            webAction();
        } else {
            errorGroup.setVisibility(View.VISIBLE);
            pbLoading.setVisibility(View.GONE);
        }


    }

    public void webAction() {
        aboutWeb = findViewById(R.id.web_alc);
        aboutWeb.loadUrl("https://andela.com/alc/");
        aboutWeb.getSettings().setJavaScriptEnabled(true);
        aboutWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        aboutWeb.getSettings().setAllowContentAccess(true);
        aboutWeb.getSettings().setAllowFileAccess(true);
        aboutWeb.getSettings().setAppCacheEnabled(true);
        aboutWeb.getSettings().setBuiltInZoomControls(true);
        aboutWeb.getSettings().setSupportZoom(true);
        swipe.setRefreshing(true);

        aboutWeb.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                // Update the progress bar with page loading progress
                pbLoading.setProgress(newProgress);
                if (newProgress == 100) {
                    // Hide the progressbar
                    pbLoading.setVisibility(View.GONE);
                }
            }
        });
        aboutWeb.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(AboutALC.this);


                builder.setMessage("Oops Looks Like the certificate returned is not trusted");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                String summary = "<html><body><strong>" + getString(R.string.lost_connection) + "</strong></body></html>";
                aboutWeb.loadData(summary, "text/html", "utf-8");

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Do something on page loading started
                // Visible the progressbar
                pbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                swipe.setRefreshing(false);
                pbLoading.setVisibility(View.GONE);
            }

        });
    }
}
