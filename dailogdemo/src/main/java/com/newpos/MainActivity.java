package com.newpos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.newpos.common.CommonDialog;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonDialog dialog = CommonDialog.build(this);
        dialog.setTitle("测试dialog");
        dialog.setOkBut("OK", new CommonDialog.onOkListener() {
            @Override
            public boolean onOK(CommonDialog dlg) {
                Toast.makeText(MainActivity.this, "OK点击了", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        dialog.setCancle("Cancle", new CommonDialog.onCancelListener() {
            @Override
            public void onCancel(CommonDialog dlg) {
                Toast.makeText(MainActivity.this, "Cancle 点击了", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
