package cn.dbboy.commentlib.toutiao;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.sum.slike.SuperLikeLayout;

import cn.dbboy.commentlib.R;
import cn.dbboy.commentlib.base.BaseActivity;

/**
 * Created by DB_BOY on 2019/5/17.</br>
 */
public class ToutiaoActivity extends BaseActivity {

    private View btnHeart1, btnHeart;
    private SuperLikeLayout superLike;

    public static Intent createIntent(Context ctx) {
        Intent intent = new Intent(ctx, ToutiaoActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tou;
    }

    @Override
    protected void initView() {

        btnHeart = findViewById(R.id.iv_heart);
        btnHeart1 = findViewById(R.id.iv_heart1);
        superLike = findViewById(R.id.super_like_layout);
        superLike.setProvider(BitmapProviderFactory.getHDProvider(this));
    }

    @Override
    public void setListener() {
        btnHeart.setOnClickListener(new View.OnClickListener() {
            long duration = 200;
            long lastClickTime;

            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastClickTime > duration) {
                    return;
                }
                lastClickTime = System.currentTimeMillis();
                int x = (int) (v.getX() + v.getWidth() / 2);
                int y = (int) (v.getY() + v.getHeight() / 2);
                superLike.launch(x, y);
            }
        });
    }
}
