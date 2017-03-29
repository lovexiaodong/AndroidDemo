package zyd.task;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import zyd.Gril;
import zyd.util.GsonUtil;
import zyd.util.OkHttpUtil;

/**
 * Created by 张玉栋 on 2017/3/10.
 */

public class DownLoadTask extends AsyncTask<String, Integer, String> {

    private static  String TAG = "DownLoadTask";

    private ListenerInterface listener;

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
       String result = OkHttpUtil.getURL(url);

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s != null)
        {
            try {
                JSONObject json = new JSONObject(s);
                s  = json.getString("results");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            List<Gril> list = GsonUtil.parseFromStr(s, Gril.class);
            if(listener != null){
                listener.ResultBack(list);
            }
        }
        Log.i(TAG, "result ======: " + s);
    }

    public void setListener(ListenerInterface listener) {
        this.listener = listener;
    }
}
