package zyd.alertmanagerdemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 张玉栋 on 2017/3/1.
 */

public class TimerServices extends IntentService{

    private static String TAG = "TimerServices";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TimerServices(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent:  intent execute!!!!!!!!!!!");


    }
}
