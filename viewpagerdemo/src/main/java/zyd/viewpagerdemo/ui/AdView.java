package zyd.viewpagerdemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.zip.Inflater;

import zyd.viewpagerdemo.R;

/**
 * Created by 张玉栋 on 2017/2/24.
 */

public class AdView extends LinearLayout {
    private boolean canLoop;
    private CBLoopViewPager viewPager;
    private LinearLayout linearLayout;
    public AdView(Context context) {
        super(context);
    }

    public AdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AdView);
        canLoop = typedArray.getBoolean(R.styleable.AdView_canLoope, true);
        View view = LayoutInflater.from(context).inflate(R.layout.include_viewpager, this, true);
        viewPager = (CBLoopViewPager) view.findViewById(R.id.cbLoopViewPager);
        linearLayout = (LinearLayout) view.findViewById(R.id.loPageTurningPoint);
    }

    public AdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
