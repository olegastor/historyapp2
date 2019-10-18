package by.znaj.rogachev2.historyapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends /*AppCompatActivity*/ YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private WebView mWebView;
    private LinearLayout mContentView;
    private FrameLayout mCustomViewContainer;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

    public static final String API_KEY = "AIzaSyA_SsxErQ96lpa6SM61llFb0ITHWhdyBbY";

    long taskId = 0;
    int id;
    String[] videoId = {"uXUOUuC5yUc",
            "uXUOUuC5yUc",
            "9IQVvkccz-c",
            "I76QX-f8_JY",
            "n85_6QYQyFI",
            "55U0sSsF30I",
            "TZyCB6RQseo",
            "oHGyyQOJzHE",
            "WDKvu7Rr_wk",
            "3HZMl1eVteo",
            "h_Htv4VxXvI"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //getSupportActionBar().hide();

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);

        /*getActionBar().setTitle(R.string.app_name_short);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setIcon(R.mipmap.ic_launcher_new);*/

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getLong("id");
        }
        id = Integer.valueOf(Long.toString(taskId));

        /*mContentView = (LinearLayout) findViewById(R.id.linearlayout);
        mWebView = (WebView) findViewById(R.id.webView);
        mCustomViewContainer = (FrameLayout) findViewById(R.id.fullscreen_custom_content);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        mWebView.loadUrl("https://www.youtube.com/watch?v=" + videoId[id]);
        mWebView.setWebViewClient(new HelloWebViewClient());*/

    }

    /*private class HelloWebViewClient extends WebViewClient  {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url)
        {
            webview.setWebChromeClient(new WebChromeClient() {

                private View mCustomView;

                @Override
                public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback)
                {
                    // if a view already exists then immediately terminate the new one
                    if (mCustomView != null)
                    {
                        callback.onCustomViewHidden();
                        return;
                    }

                    // Add the custom view to its container.
                    mCustomViewContainer.addView(view, COVER_SCREEN_GRAVITY_CENTER);
                    mCustomView = view;
                    mCustomViewCallback = callback;

                    // hide main browser view
                    mContentView.setVisibility(View.GONE);

                    // Finally show the custom view container.
                    mCustomViewContainer.setVisibility(View.VISIBLE);
                    mCustomViewContainer.bringToFront();
                }

            });

            webview.loadUrl(url);

            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack())
        {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }*/
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(videoId[id]);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }
}
