package com.hxb.code520;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hxb.code520.util.DataHelper;
import com.hxb.code520.util.DeviceInfo;
import com.hxb.code520.util.statusbar.StatusBarUtil;
import com.hxb.code520.view.RotateYAnimation;
import com.hxb.code520.view.bluesnow.FlowerView;
import com.hxb.code520.view.typewriter.TypeTextView;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class LoveBrowserActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String YuLu = "《语录》\n心动是等你的留言\n渴望是常和你见面\n甜蜜是和你小路流连\n温馨是看着你清澈的双眼\n爱你的感觉真的妙不可言";
    private static final String LOVE = "《2020年遇见爱情遇见你》"
            + "\n" + "贺兴波&岑建业"
            + "\n" + "2020年的春天"
            + "\n" + "注定是不平凡的"
            + "\n" + "在等待春天的日子里"
            + "\n" + "我等到了你"
            + "\n" + "是什么让我们相遇？"
            + "\n" + "是网络，还是疫情？"
            + "\n" + "是友情，还是爱情？"
            + "\n" + "亦或是“缘分”？"
            + "\n" + "那是一种很奇妙的东西"
            + "\n" + "“命中注定”"
            + "\n" + "1月31日"
            + "\n" + "我们成为好友"
            + "\n" + "4月5日"
            + "\n" + "我们参加户外"
            + "\n" + "今天"
            + "\n" + "5月20日"
            + "\n" + "我们正式约会"
            + "\n" + "太多，太多的回忆"
            + "\n" + "语音、视频、喝茶、谈心..."
            + "\n" + "留下的都是美好"
            + "\n" + "快乐常伴你我左右"
            + "\n" + "幸福大概就这么简单吧"
            + "\n" + "感谢身边有你"
            + "\n" + "余生有你！"
            + "\n";
    private WebView mWebView;
    private String url;
    private FlowerView mBlueSnowView;//蓝色的雪花
    private Timer myTimer = null;
    private TimerTask mTask = null;
    private static final int SNOW_BLOCK = 1;
    private TypeTextView mTypeTextView;//打字机
    private TypeTextView mTypeTextView2;//打字机
    private LinearLayout llTop;

    private ObjectAnimator mAnimator;
    private ObjectAnimator mAnimatorBottom;
    private ObjectAnimator mAnimator520;
    private MediaPlayer mPlayer;
    private LinearLayout ll_520;
    private ImageView imgTop;
    private ImageView imgBottom;
    private boolean isPause = false;


    RotateYAnimation animation = new RotateYAnimation();


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            mBlueSnowView.inva();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranspStatusBar(null);//设置状态栏颜色为黑色
        //全部禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DeviceInfo.getInstance().initializeScreenInfo(this);
        setContentView(R.layout.activity_browser);

        openRawMusic();
        initAnimator520();
        initAnimator();
        initAnimatorBottom();
        onClick(imgTop);

        startBlueSnow();
        startLOVEText();

    }

    /**
     * 打开raw目录下的音乐mp3文件
     */
    private void openRawMusic() {
        mPlayer = MediaPlayer.create(this, R.raw.love_music);
        //用prepare方法，会报错误java.lang.IllegalStateExceptio
        //mediaPlayer.prepare();
        int duration = mPlayer.getDuration();//获取音乐总时间
        mPlayer.setLooping(true);//设置为循环播放
        mPlayer.start();//开始播放音乐
        mPlayer.pause();//暂停播放音乐
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initAnimator520() {
        ll_520 = findViewById(R.id.ll_520);
        //旋转的次数
        animation.setRepeatCount(Integer.MAX_VALUE);
//        //旋转的时间
        animation.setDuration(1000);
        //是否停留在动画的最后一帧
        animation.setFillAfter(false);
        ll_520.startAnimation(animation);

//        mAnimator520 = ObjectAnimator.ofFloat(ll_520, "rotation", 0.0f, 360.0f);
//        mAnimator520.setDuration(3000);//设定转一圈的时间
//        mAnimator520.setRepeatCount(Animation.INFINITE);//设定无限循环
//        mAnimator520.setRepeatMode(ObjectAnimator.RESTART);// 循环模式
//        mAnimator520.setInterpolator(new LinearInterpolator());// 匀速
//        mAnimator520.start();//动画开始
//        mAnimator520.pause();//动画暂停
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initAnimator() {
        imgTop = findViewById(R.id.imgTop);
        imgTop.setOnClickListener(this);
        mAnimator = ObjectAnimator.ofFloat(imgTop, "rotation", 0.0f, 360.0f);
        mAnimator.setDuration(3000);//设定转一圈的时间
        mAnimator.setRepeatCount(Animation.INFINITE);//设定无限循环
        mAnimator.setRepeatMode(ObjectAnimator.RESTART);// 循环模式
        mAnimator.setInterpolator(new LinearInterpolator());// 匀速
        mAnimator.start();//动画开始
        mAnimator.pause();//动画暂停
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initAnimatorBottom() {
        imgBottom = findViewById(R.id.imgBottom);
        imgBottom.setOnClickListener(this);
        mAnimatorBottom = ObjectAnimator.ofFloat(imgBottom, "rotation", 0.0f, 360.0f);
        mAnimatorBottom.setDuration(3000);//设定转一圈的时间
        mAnimatorBottom.setRepeatCount(Animation.INFINITE);//设定无限循环
        mAnimatorBottom.setRepeatMode(ObjectAnimator.RESTART);// 循环模式
        mAnimatorBottom.setInterpolator(new LinearInterpolator());// 匀速
        mAnimatorBottom.start();//动画开始
        mAnimatorBottom.pause();//动画暂停
    }


    /**
     * 表白文字
     */
    private void startLOVEText() {
        llTop = findViewById(R.id.ll_top);
        //初始华打字机
        mTypeTextView = (TypeTextView) findViewById(R.id.typeTextView);
        mTypeTextView.setOnTypeViewListener(new TypeTextView.OnTypeViewListener() {
            @Override
            public void onTypeOver() {
                startLOVEImg();
            }

            @Override
            public void onTypeStart() {
                llTop.setVisibility(View.GONE);
            }
        });
        mTypeTextView.start(LOVE);
    }

    /**
     * 开启语录
     */
    private void startYuLuText() {
        //初始华打字机
        mTypeTextView2 = (TypeTextView) findViewById(R.id.typeTextView2);
        mTypeTextView2.start(YuLu);
    }

    /**
     * 表白爱心
     */
    private void startLOVEImg() {
        //表白爱心
        mWebView = findViewById(R.id.webview);
        initweb();
        url = "file:///android_asset/love.html";
        if (!TextUtils.isEmpty(url)) {
            mWebView.loadUrl(url);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTypeTextView.setVisibility(View.GONE);
                llTop.setVisibility(View.VISIBLE);
//                mAnimator520.resume();//动画继续
                startYuLuText();
            }
        }, 5000);//3秒后执行Runnable中的run方法
    }

    /**
     * 雪花
     */
    private void startBlueSnow() {
        //雪花
        mBlueSnowView = (FlowerView) findViewById(R.id.flowerview);
        mBlueSnowView.loadFlower();
        mBlueSnowView.setWH(DeviceInfo.mScreenWidthForPortrait, DeviceInfo.mScreenHeightForPortrait, DeviceInfo.mDensity);
        mBlueSnowView.addRect();
        myTimer = new Timer();
        mTask = new TimerTask() {
            public void run() {
                Message msg = new Message();
                msg.what = LoveBrowserActivity.SNOW_BLOCK;
                mHandler.sendMessage(msg);
            }
        };
        myTimer.schedule(mTask, 1000, 10);
    }

    private void initweb() {
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.setWebViewClient(new InnerWebViewClient());
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    private class InnerWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
        if (this.mWebView != null) {
            this.mWebView.removeAllViews();
            this.mWebView.destroy();
            this.mWebView = null;
        }
        if (mPlayer != null) {
            mPlayer.stop();//停止播放音乐
        }
    }

    private void cancelTimer() {
        if (this.myTimer != null) {
            this.myTimer.cancel();
            this.myTimer = null;
        }
        if (this.mTask != null) {
            this.mTask.cancel();
            this.mTask = null;
        }
        if (mBlueSnowView != null) {
            mBlueSnowView.recly();
        }
    }

    /**
     * 设置沉浸式
     */
    public int setTranspStatusBar(View titleBar) {
        int statusBarHeight = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //修改状态栏颜色和文字图标颜色
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
            if (titleBar != null) {
                ViewGroup.LayoutParams params = titleBar.getLayoutParams();
                params.height += statusBarHeight;
                titleBar.setLayoutParams(params);
                titleBar.setPadding(0, statusBarHeight, 0, 0);
            }
            initStatusBar();
        }
        return statusBarHeight;
    }

    /**
     * 初始化状态栏相关，
     * PS: 设置全屏需要在调用super.onCreate(arg0);之前设置setIsFullScreen(true);否则在Android 6.0下非全屏的activity会出错;
     * SDK19：可以设置状态栏透明，但是半透明的SYSTEM_BAR_BACKGROUNDS会不好看；
     * SDK21：可以设置状态栏颜色，并且可以清除SYSTEM_BAR_BACKGROUNDS，但是不能设置状态栏字体颜色（默认的白色字体在浅色背景下看不清楚）；
     * SDK23：可以设置状态栏为浅色（SYSTEM_UI_FLAG_LIGHT_STATUS_BAR），字体就回反转为黑色。
     * 为兼容目前效果，仅在SDK23才显示沉浸式。
     */
    private void initStatusBar() {
        Window win = getWindow();

        //KITKAT也能满足，只是SYSTEM_UI_FLAG_LIGHT_STATUS_BAR（状态栏字体颜色反转）只有在6.0才有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
            // 状态栏字体设置为深色，SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 为SDK23增加
            win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            // 部分机型的statusbar会有半透明的黑色背景
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            win.setStatusBarColor(Color.TRANSPARENT);// SDK21

            StatusBarUtil.setStatusTextColor(!DataHelper.getBoolSF(this, "Current_Theme_NightMode"), this);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.imgTop || i == R.id.imgBottom) {
            if (!isPause) {
                mAnimator.resume();//动画继续
                mAnimatorBottom.resume();//动画继续
                mPlayer.start();//继续开始音乐播放
                isPause = true;
            } else {
                mAnimator.pause();//动画暂停
                mAnimatorBottom.pause();//动画暂停
                mPlayer.pause();//暂停音乐播放
                isPause = false;
            }
        }
    }

}
