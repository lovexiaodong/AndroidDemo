package zyd.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by 张玉栋 on 2017/3/29.
 */

public class LinearLayoutDecoration extends RecyclerView.ItemDecoration {

    private static String TAG = "LinearLayoutDecoration";

    private Context context;
    private int orition;
    private final int[] ATTR = {android.R.attr.listDivider};
    private Drawable mDivder;

    public LinearLayoutDecoration(Context context, int orition){
        this.orition = orition;
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(ATTR);
        mDivder = array.getDrawable(0);
        array.recycle();

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(orition == LinearLayout.HORIZONTAL){
            drawHorizontal(c, parent, state);
        }
        if(orition == LinearLayout.VERTICAL){
            drawVertical(c, parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //如果竖向排列的话，左边的位置和右边的位置 是不变的
        int left = parent.getPaddingLeft();
        int right = parent.getPaddingRight();
        //通过打log发现其实这里的childcount并不是所有的item，只是当前显示的个数，刚开始以为会全部绘制出来，是自己太傻
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            int top = view.getBottom();
            int bottom = top + mDivder.getIntrinsicHeight();
            mDivder.setBounds(left, top, right, bottom);
            mDivder.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent,  RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for(int i = 0; i< childCount; i++){
            View view = parent.getChildAt(i);
            int left = view.getLeft();
            int right = left + mDivder.getIntrinsicWidth();
            mDivder.setBounds(left, top, right, bottom);
            mDivder.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        //获取item的总数
        int chilCount = state.getItemCount();
        // 获取当前显示的位置
        int postion = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        //最后一个就不用绘制了分割框了
        if(chilCount != (postion +  1)){
            outRect.set(0, 0, 0, mDivder.getIntrinsicHeight());
        }
    }
}
