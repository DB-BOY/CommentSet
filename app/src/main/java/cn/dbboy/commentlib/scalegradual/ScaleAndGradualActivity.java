package cn.dbboy.commentlib.scalegradual;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import cn.dbboy.commentlib.R;
import cn.dbboy.commentlib.base.BaseActivity;
import cn.dbboy.commentlib.utils.AnimatorUtil;

/**
 * 缩放、渐变动画代码
 * Created by DB_BOY on 2019/5/15.</br>
 */
public class ScaleAndGradualActivity extends BaseActivity {

    View btnHeart, btnHeart1, tvRotation, tvRotation1, tvRotation2, llColor;


    public static Intent createIntent(Context ctx) {
        Intent intent = new Intent(ctx, ScaleAndGradualActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scale_gradual;
    }

    @Override
    protected void initView() {
        btnHeart = findViewById(R.id.iv_heart);
        btnHeart1 = findViewById(R.id.iv_heart1);
        tvRotation = findViewById(R.id.tv_rotation);
        tvRotation1 = findViewById(R.id.tv_rotation1);
        tvRotation2 = findViewById(R.id.tv_rotation2);
        llColor = findViewById(R.id.ll_color);
    }

    @Override
    public void setListener() {
        btnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorUtil.scaleAnimate(v);
            }
        });
        btnHeart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorUtil.scaleAnimate2(v);
            }
        });
        tvRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorUtil.rotationAnimate(v);
            }
        });
        tvRotation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorUtil.rotationAnimateX(v);
            }
        });
        tvRotation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorUtil.rotationAnimateY(v);
            }
        });
        llColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorUtil.colorAnimate(findViewById(R.id.tv_color), true);
                AnimatorUtil.colorAnimate(findViewById(R.id.tv_color1), false);
            }
        });

        handler.sendEmptyMessageDelayed(CODE_CLICK, 600);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_CLICK:
                    if (count < 3) {
                        click();
                    }
                    break;
            }
        }
    };
    private void click() {
        count++;
        handler.sendEmptyMessageDelayed(CODE_CLICK, 3000);
        btnHeart.performClick();
        btnHeart1.performClick();
        tvRotation.performClick();
        tvRotation1.performClick();
        tvRotation2.performClick();
        llColor.performClick();
    }

}
