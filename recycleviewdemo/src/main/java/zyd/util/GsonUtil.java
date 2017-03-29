package zyd.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by 张玉栋 on 2017/3/10.
 */

public class GsonUtil {

    public static <T> List<T> parseFromStr(String res, Class<T> Object){
        JsonArray array = new JsonParser().parse(res).getAsJsonArray();
        List<T> list = new ArrayList<T>();
        Gson gson = new Gson();
        for(JsonElement element : array){
            list.add(gson.fromJson(element, Object));
        }
        return list;
    }
}
