package com.zyd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List list = new ArrayList();


        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
