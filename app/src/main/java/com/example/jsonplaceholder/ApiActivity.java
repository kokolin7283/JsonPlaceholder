package com.example.jsonplaceholder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.jsonplaceholder.Adapter.ApiAdapter;
import com.example.jsonplaceholder.Bean.DataBean;
import com.example.jsonplaceholder.Network.ContentGetAsync;

import java.util.ArrayList;

public class ApiActivity extends AppCompatActivity implements ApiAdapter.OnItemClickHandler {
    private String TAG = this.getClass().getSimpleName();
    private ProgressBar progressBar;
    private ArrayList<DataBean> list = new ArrayList<DataBean>();
    private RecyclerView recyclerView;
    private boolean sIsScrolling = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        initial();
        if(list.size() != 0){
            setRecycle();
        }else{
            loadData();
        }
    }

    private void loadData() {
        ContentGetAsync async = new ContentGetAsync(progressBar, list, new ContentGetAsync.TaskListener() {
            @Override
            public void onFinished(ArrayList<DataBean> result) {
                list = result;
                setRecycle();
            }
        });

        if(!async.isCancelled()){
            String url = "https://jsonplaceholder.typicode.com/photos";
            async.execute(url);
        }else{
            Toast.makeText(ApiActivity.this, "連線已取消", Toast.LENGTH_SHORT).show();
        }
    }

    private void setRecycle() {
        Log.d(TAG,list.size() + "");
        ApiAdapter adapter = new ApiAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4 ));
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
//                    sIsScrolling = true;
//                    Glide.with(VipMasterActivity.this).pauseRequests();
//                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    if (sIsScrolling == true) {
//                        Glide.with(VipMasterActivity.this).resumeRequests();
//                    }
//                    sIsScrolling = false;
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
    }

    @Override
    public void onItemClick(int position, DataBean contentObject) {
        Toast.makeText(this,contentObject.getId(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ApiActivity.this,ContentActivity.class);
        startActivity(intent);
    }

    private void initial() {
        progressBar = (ProgressBar)findViewById(R.id.MyprogressBar);
        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
    }
}
