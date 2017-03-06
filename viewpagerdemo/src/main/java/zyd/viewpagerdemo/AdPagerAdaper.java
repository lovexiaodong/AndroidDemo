package zyd.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by 张玉栋 on 2017/2/24.
 */

public class AdPagerAdaper extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
