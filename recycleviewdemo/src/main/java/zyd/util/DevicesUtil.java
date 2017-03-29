package zyd.util;

import android.content.Context;

/**
 * Created by 张玉栋 on 2017/3/13.
 */

public class DevicesUtil {

    public static int getDeviceWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
