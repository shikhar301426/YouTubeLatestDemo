package com.example.mayank.youtubelatestdemo;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity{

    private Fragment contentFragment;
    String testDevice = "D0A04359EA1ECE9BA0CD4B6F457A9991";
    String testDevice2 = "63C3530DA03C191310DB9AB8F0672E5C";
    String testDevice3 = "801F2141A1DC3F743363AFDFDC42AF3A";
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    boolean displayAd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView mainWebView = (WebView) findViewById(R.id.mainWebView);
        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mainWebView.setWebViewClient(new MyCustomWebViewClient());
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mainWebView.loadUrl(this.getString(R.string.channel_url));

        mAdView = (AdView) findViewById(R.id.ad_view);
        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(testDevice)
                .addTestDevice(testDevice2)
                .addTestDevice(testDevice3)
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayAd = true;
//				View servername = findViewById(R.id.txt_List);
//				RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams) servername.getLayoutParams();
//				layoutparams.addRule(RelativeLayout.BELOW, mAdView.getId());
//				layoutparams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
//				servername.setLayoutParams(layoutparams);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                if (!displayAd) {
                }
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
            }
        });

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }

    private class MyCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                finish();
                //goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

        } else {
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(testDevice)
                .addTestDevice(testDevice2)
                .addTestDevice(testDevice3)
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }


    /*
     * We call super.onBackPressed(); when the stack entry count is > 0. if it
     * is instanceof EmpListFragment or if the stack entry count is == 0, then
     * we prompt the user whether to quit the app or not by displaying dialog.
     * In other words, from EmpListFragment on back press it quits the app.
     */
    @Override
    public void onBackPressed() {
        onShowQuitDialog();
    }

    public void onShowQuitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        builder.setMessage("Do You Want To Quit?");
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        showInterstitial();
                    }
                });
        builder.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }

}
