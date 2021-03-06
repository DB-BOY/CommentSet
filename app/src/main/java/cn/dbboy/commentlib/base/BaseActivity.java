package cn.dbboy.commentlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DB_BOY on 2019/3/5.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final int CODE_CLICK = 1;
    protected int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initData();
        initView();
        setListener();
    }

    public void initData() {

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    public void setListener() {

    }

}
