package zyd.viewpagerdemo;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by 张玉栋 on 2017/2/23.
 */

public class MatrixDemo extends Activity {

    private ImageView image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.img);
        Log.i("MatrixDemo", "---------------");
        Matrix matrix = new Matrix();
        float[] matrixValues = new float[9];
        matrix.setTranslate(100, 91);
        matrix.getValues(matrixValues);
        for(int i = 0; i < 3; ++i)
        {
            String temp = new String();
            for(int j = 0; j < 3; ++j)
            {
                temp += matrixValues[3 * i + j ] + "\t";
            }
            Log.i("MatrixDemo", temp);
        }
        matrix = image.getMatrix();
        matrix.getValues(matrixValues);
        for(int i = 0; i < 3; ++i)
        {
            String temp = new String();
            for(int j = 0; j < 3; ++j)
            {
                temp += matrixValues[3 * i + j ] + "\t";
            }
            Log.i("MatrixDemo", temp);
        }


    }
}
