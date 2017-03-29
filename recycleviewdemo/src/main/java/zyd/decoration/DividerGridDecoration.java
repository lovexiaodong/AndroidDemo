package zyd.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * Created by 张玉栋 on 2017/3/29.
 */

public class DividerGridDecoration extends RecyclerView.ItemDecoration {

    private static String TAG = "DividerGridDecoration1";

    private Context context;
    private final int[] ATTR = {android.R.attr.listDivider};
    private Drawable mDivder;

    public DividerGridDecoration(Context context){
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(ATTR);
        mDivder = array.getDrawable(0);
        array.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent, state);
        drawHorizotal(c, parent, state);
    }

    private void drawHorizotal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();

        for(int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            int left =  view.getRight() - params.rightMargin;

            int right = left + mDivder.getIntrinsicWidth();
            int top = view.getTop();
            int bottom = view.getBottom() + mDivder.getIntrinsicHeight();
            mDivder.setBounds(left, top, right, bottom);
            mDivder.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
           RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

            int left =  view.getLeft();
            int right = view.getRight() + mDivder.getIntrinsicWidth();
            int top = view.getBottom() - params.bottomMargin;
            int bottom = top + mDivder.getIntrinsicHeight();
            mDivder.setBounds(left, top, right, bottom);
            mDivder.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int childCount = state.getItemCount();
        int columnCount = getColumnCount(parent);
        int postion =((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        Log.i(TAG, "getItemOffsets: childCount = " + childCount + " ; columnCount = " + columnCount + " postion = " + postion  + " ; (postion + 1) % childCount) = " + (postion + 1) % childCount);
        //最后一列
        boolean isLastColumn = ((postion + 1) % columnCount) == 0;
        //最后-行
        boolean isLastRow = postion > (childCount - (childCount % columnCount) - 1);
        if(!isLastColumn && !isLastRow){
            outRect.right = mDivder.getIntrinsicWidth();
            outRect.bottom =  mDivder.getIntrinsicHeight();
//            outRect.set(0, 0, mDivder.getIntrinsicWidth(), mDivder.getIntrinsicHeight());
        }else if(isLastColumn && !isLastRow){
            outRect.bottom = mDivder.getIntrinsicHeight();
//            outRect.set(0, 0, 0, mDivder.getIntrinsicHeight());
        }else if(!isLastColumn && isLastRow){
            outRect.right = mDivder.getIntrinsicWidth();
//            outRect.set(0, 0, mDivder.getIntrinsicWidth(), 0);
        }
//
//        if(((postion + 1) % columnCount) != 0){
//            outRect.set(0, 0, mDivder.getIntrinsicWidth(), 0);
//        }
    }

    private int getColumnCount(RecyclerView parent){
       RecyclerView.LayoutManager manager =  parent.getLayoutManager();

        int count = 0;
        if(manager instanceof GridLayoutManager){
          count = ((GridLayoutManager) manager).getSpanCount();
        }
        if(manager instanceof StaggeredGridLayoutManager){
            count = ((StaggeredGridLayoutManager) manager).getSpanCount();
        }
        return count;
    }
}
