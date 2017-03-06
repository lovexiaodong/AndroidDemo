package zyd.viewpagerdemo.util;

import android.content.Context;

/**
 * Created by 张玉栋 on 2017/2/28.
 */

public class DeviceUtil {

    public static int deviceWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int deviceHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
