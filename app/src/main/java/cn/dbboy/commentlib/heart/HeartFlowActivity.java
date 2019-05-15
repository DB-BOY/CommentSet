package cn.dbboy.commentlib.heart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import cn.dbboy.commentlib.R;
import cn.dbboy.commentlib.base.BaseActivity;
import cn.dbboy.commentlib.widget.HeartLayout;

/**
 * Created by DB_BOY on 2019/5/15.</br>
 */
public class HeartFlowActivity extends BaseActivity {
    private View btnHeart;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_CLICK:
                    if (count < 50) {
                        click();
                    }
                    break;
            }
        }
    };
    private HeartLayout heartLayout;

    public static Intent createIntent(Context ctx) {
        Intent intent = new Intent(ctx, HeartFlowActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_heart;
    }

    @Override
    protected void initView() {
        btnHeart = findViewById(R.id.iv_heart);
        heartLayout = findViewById(R.id.heart);
    }

    @Override
    public void setLisenter() {
        btnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartLayout.addHeart();
            }
        });
        handler.sendEmptyMessageDelayed(CODE_CLICK, 600);
    }

    private void click() {
        count++;
        handler.sendEmptyMessageDelayed(CODE_CLICK, 100);
        btnHeart.performClick();
    }

}
