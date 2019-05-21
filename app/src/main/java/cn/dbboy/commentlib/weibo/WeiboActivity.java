package cn.dbboy.commentlib.weibo;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import cn.dbboy.commentlib.R;
import cn.dbboy.commentlib.base.BaseActivity;

/**
 * Created by DB_BOY on 2019/5/20.</br>
 */
public class WeiboActivity extends BaseActivity {
    private View btnHeart;

    public static Intent createIntent(Context ctx) {
        Intent intent = new Intent(ctx, WeiboActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weibo;
    }

    @Override
    protected void initView() {
        btnHeart = findViewById(R.id.iv_heart);
    }

    @Override
    public void setListener() {
        //        btnHeart.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                v.setSelected(true);
        //            }
        //        });
        //等大拇指变到最大，顶到最高，开始背景圆圈动画
        //        roundAnimator.setStartDelay(250);
    }

}
