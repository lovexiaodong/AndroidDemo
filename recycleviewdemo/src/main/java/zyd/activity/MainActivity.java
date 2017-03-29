package zyd.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zyd.Gril;
import zyd.adapter.MyRecycleAdapter;
import zyd.decoration.DividerGridDecoration;
import zyd.task.DownLoadTask;
import zyd.task.ListenerInterface;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    RecyclerView mRecyclerView;
    private SwipeRefreshLayout refreshLayout ;
    private List<Gril> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        initLister();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        intData();


        DownLoadTask task = new DownLoadTask();
        task.setListener(new ListenerInterface() {
            @Override
            public void ResultBack(List<Gril> grils) {
//                grils.remove(0);
                final MyRecycleAdapter adapter = new MyRecycleAdapter(MainActivity.this, grils);

                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.addItemDecoration(new DividerGridDecoration(MainActivity.this));
//                mRecyclerView.addItemDecoration(new LinearLayoutDecoration(MainActivity.this, LinearLayout.VERTICAL));

            }
        });
        task.execute("http://gank.io/api/data/福利/10/1");
    }

    private void initLister() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this, "正在刷新", Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });
    }

//    private void intData() {
//
//        PackageManager manager = this.getPackageManager();
//        List<PackageInfo> listpg = manager.getInstalledPackages(0);
//       for(int i = 0 ; i < listpg.size(); i++){
//           list.add(listpg.get(i).packageName);
//       }
//    }

}
