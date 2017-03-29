package zyd.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;
import java.util.Random;

import zyd.Gril;
import zyd.activity.R;
import zyd.util.DevicesUtil;

/**
 * Created by 张玉栋 on 2017/3/7.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter {

    private static String TAG = "MyRecycleAdapter";

    private Context context;
    private List<Gril> list;
    public  MyRecycleAdapter(Context context, List list){
        this.context = context;
        this.list = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder myHolder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;
        Gril gril = list.get(position);
        Picasso.with(context).load(gril.getUrl())
                .transform(foramtion)
                .into(myHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    Transformation foramtion = new Transformation() {
        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth  = DevicesUtil.getDeviceWidth(context)/3;
//             int minWidth = targetWidth - 1500;
//            Random random = new Random();
//            int randomNumber =  random.nextInt(targetWidth)%(targetWidth-minWidth+1) + minWidth;
            double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            Log.i(TAG, "transform: aspectRatio = " + aspectRatio);
            int targetHeight = (int) (targetWidth * aspectRatio);
               // (int)(targetWidth * (Math.random() * 2))
            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, (int)(targetWidth * (Math.random() + 2)), false);
            if (result != source) {
                // Same bitmap is returned if sizes are the same
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
    };
}
