package zyd.viewpagerdemo;

import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zyd.viewpagerdemo.util.DeviceUtil;

public class MainActivity extends AppCompatActivity {

    private View view1, view2, view3;
    private ViewPager viewPager;
    private List<View> list = new ArrayList();
    private WebView webView;
    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        LayoutInflater inflater = getLayoutInflater();
//        view1 = inflater.inflate(R.layout.layout1, null);
//        view2 = inflater.inflate(R.layout.layout2, null);
//        view3 = inflater.inflate(R.layout.layout3, null);
//        list.add(view1);
//        list.add(view2);
//        list.add(view3);
//        MyPagerAdapeter adapeter = new MyPagerAdapeter(list);
//        viewPager.setAdapter(adapeter);

        webView = (WebView) findViewById(R.id.webView);
        webView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View arg0) {
                return true;
            }
        });

        webView.setWebViewClient(new WebViewClient(){
                                     public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {};
                                     public void onPageFinished(WebView view, String url) {};
                                 }
        );

        String mURL = "http://172.21.22.90:80/index1.html?channelID=13&modelType=NEWPOSTECH+NEW9210&sn=H9250000159&ts=2017022808381";
//        String mURL = "http://172.21.22.90:80/index.html";
        initSettings();
        long time = System.currentTimeMillis();
        webView.loadUrl(mURL);
        long time1 = System.currentTimeMillis();
        Log.i(TAG, "onCreate:======================= 加载时间为：" + (time1 - time));
        Log.i(TAG, "onCreate:======================= 屏幕的宽度：" + DeviceUtil.deviceHeight(this));
        Log.i(TAG, "onCreate:======================= 屏幕的长度：" + DeviceUtil.deviceHeight(this));
    }

    private void initSettings() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true); // 设置WebView属性，能够执行Javascript脚本
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);// 支持缩放
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir()
                .getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAppCacheEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.i("url", url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }
    public void getCameraCounnt(View view){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            CameraManager manager = (CameraManager) getSystemService(CAMERA_SERVICE);
//            try {
//                String[] count =  manager.getCameraIdList();
//                Toast.makeText(MainActivity.this, "camera的个数" + count.length, Toast.LENGTH_SHORT).show();
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
//        }

        android.hardware.Camera camera = android.hardware.Camera.open();
        int count = android.hardware.Camera.getNumberOfCameras();
        Toast.makeText(MainActivity.this, "camera的个数" + count, Toast.LENGTH_SHORT).show();
        camera.release();
    }

    class MyPagerAdapeter extends PagerAdapter{
        List<View> list;
        public MyPagerAdapeter(List list){
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            Log.i("MainActivity", "isViewFromObject  view =: " + view);
            Log.i("MainActivity", "isViewFromObject   object =: " + object);
            Log.i("MainActivity", "true of false    : " + (view == object));
            return view == object;

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i("MainActivity", "instantiateItem  position =: " + position);
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("MainActivity", "destroyItem  position =: " + position);
            Log.i("MainActivity", "destroyItem  object =: " + object);
            container.removeView(list.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "xiao ming";
                case 1:
                    return "xiao hong";
                case 2:
                    return "xiao gang";
            }
            return "";
        }
    }
}
