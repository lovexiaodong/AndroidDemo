package zyd.util;

import android.support.v7.widget.RecyclerView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 张玉栋 on 2017/3/10.
 */

public class OkHttpUtil {

    public static String getURL(String URL){
        String result = "";
        OkHttpClient clinet = new OkHttpClient();
        Request request = new Request.Builder().url(URL).build();
        try {
            Response response = clinet.newCall(request).execute();
            if(response.isSuccessful()){
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
