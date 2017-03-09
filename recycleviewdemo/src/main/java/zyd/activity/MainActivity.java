package zyd.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import zyd.adapter.MyRecycleAdapter;
import zyd.decoration.DividerGridDecoration;
import zyd.decoration.MyDecoration;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        intData();
        MyRecycleAdapter adapter = new MyRecycleAdapter(this, list);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerGridDecoration(this));

    }

    private void intData() {

        PackageManager manager = this.getPackageManager();
        List<PackageInfo> listpg = manager.getInstalledPackages(0);
       for(int i = 0 ; i < listpg.size(); i++){
           list.add(listpg.get(i).packageName);
       }
    }

}
