package cn.dbboy.commentlib;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import cn.dbboy.commentlib.base.BaseActivity;
import cn.dbboy.commentlib.base.ItemType;
import cn.dbboy.commentlib.main.MainAdapter;
import cn.dbboy.commentlib.scalegradual.ScaleAndGradual;

public class MainActivity extends BaseActivity {
    ItemType[] s = {ItemType.ScaleGradual, ItemType.Wechat, ItemType.HeadPile, ItemType.Toutiao, ItemType.HeartFlow};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        RecyclerView recyclerView = findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setList(Arrays.asList(s));

        adapter.setListener(new MainAdapter.ItemClickListener() {
            @Override
            public void click(ItemType item) {
                clickItem(item);
            }
        });
    }

    private void clickItem(ItemType item) {
        Intent intent = null;
        switch (item) {
            case ScaleGradual:
                intent = ScaleAndGradual.createIntent(MainActivity.this);
                break;
        }
        startActivity(intent);
    }
}
