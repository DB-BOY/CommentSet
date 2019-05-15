package cn.dbboy.commentlib.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import cn.dbboy.commentlib.R;

public class HeartLayout extends RelativeLayout {

    /**
     * 桃心
     */
    private int[] hearts = new int[]{//
            R.drawable.heart0, R.drawable.heart1,// 
            R.drawable.heart2, R.drawable.heart3, //
            R.drawable.heart4, R.drawable.heart5,//
            R.drawable.heart6, R.drawable.heart7,//
            R.drawable.heart8};

    /**
     * 插补器
     */
    private Interpolator[] interpolators = new Interpolator[]{//
            new AccelerateDecelerateInterpolator(), new LinearInterpolator(), //
            new OvershootInterpolator(), new DecelerateInterpolator(),// 
            new BounceInterpolator(), new AccelerateInterpolator()};

    private int screenWidth, screenHeight;

    private Random random;

    /**
     * 进入动画持续时间
     */
    private int enterDuration = 300;
    /**
     * 贝塞尔动画持续时间
     */
    private int duration = 3000;
    /**
     * 桃心的缩放比例
     */
    private float mScale = 1.0f;

    private LayoutParams mParams;
    private PointF mStartPointF;

    public HeartLayout(@NonNull Context context) {
        this(context, null);
    }

    public HeartLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        random = new Random();
        mStartPointF = new PointF(0, 0);
        //飘心起始
        mParams = new LayoutParams(100, 100);
        mParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        mParams.addRule(CENTER_HORIZONTAL, TRUE);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HeartLayout);
        mScale = a.getFloat(R.styleable.HeartLayout_scale, mScale);
        if (mScale > 1.f) {
            mScale = 1.0f;
        }

        a.recycle();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量后的宽高
        screenWidth = getMeasuredWidth();
        screenHeight = getMeasuredHeight();

    }

    /**
     * 添加桃心
     */
    public void addHeart() {

        ImageView iv = getHeartView(randomHeart());
        addView(iv);
        getStartPoint(iv);
        getAnimator(iv).start();
    }

    /**
     * 获取一个桃心
     *
     * @param resId
     *
     * @return
     */
    private ImageView getHeartView(@DrawableRes int resId) {
        ImageView iv = new ImageView(getContext());
        iv.setLayoutParams(mParams);
        iv.setImageResource(resId);
        return iv;
    }


    /**
     * 飘心进入动画
     * 缩放，渐变
     *
     * @param target
     *
     * @return
     */
    private AnimatorSet getEnterAnimator(View target) {

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.1f, mScale);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.1f, mScale);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.1f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, alpha);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.setDuration(enterDuration);

        return animatorSet;
    }


    /**
     * 贝塞尔曲线动画
     *
     * @param target
     *
     * @return
     */
    private ValueAnimator getBezierCurveAnimator(View target) {

        //贝塞尔曲线中间的两个点
        final PointF pointf1 = getBezierPointF(4.0f);
        final PointF pointf2 = getBezierPointF(3f);

        PointF endPoint = new PointF((float) (Math.random() * screenWidth), (float) (Math.random() * 100));

        BezierEvaluator evaluator = new BezierEvaluator(pointf1, pointf2);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(evaluator, mStartPointF, endPoint);
        valueAnimator.setInterpolator(randomInterpolator());
        valueAnimator.addUpdateListener(new BezierListener(target));

        valueAnimator.setDuration(duration);

        return valueAnimator;
    }


    /**
     * 得到Animator集
     *
     * @param target
     *
     * @return
     */
    private Animator getAnimator(View target) {

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(getEnterAnimator(target), getBezierCurveAnimator(target));
        animatorSet.addListener(new EndAnimatorListener(target));
        return animatorSet;

    }

    /**
     * 测量
     *
     * @param target
     */
    private void makeMeasureSpec(View target) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        target.measure(spec, spec);
    }

    /**
     * 飘心起点
     *
     * @param target
     *
     * @return
     */
    private void getStartPoint(View target) {

        if (mStartPointF.x == 0 || mStartPointF.y == 0) {
            makeMeasureSpec(target);
            int width = target.getMeasuredWidth();
            int height = target.getMeasuredHeight();
            Rect x = new Rect();
            target.getDrawingRect(x);

            mStartPointF.x = (screenWidth + getPaddingLeft() - getPaddingRight() - width) / 2 + width / 4;
            mStartPointF.y = screenHeight + getPaddingTop() - getPaddingBottom() - height / 2;
            Log.i("----db.boy", "" + mStartPointF);
        }
    }


    /**
     * 随机贝塞尔曲线中间的点
     *
     * @param scale
     *
     * @return
     */
    private PointF getBezierPointF(float scale) {
        PointF pointF = new PointF();
        pointF.x = random.nextInt(screenWidth);
        pointF.y = random.nextInt(screenHeight) / scale;
        return pointF;
    }

    /**
     * 随机一个插补器
     *
     * @return
     */
    private Interpolator randomInterpolator() {
        return interpolators[random.nextInt(interpolators.length)];
    }

    /**
     * 随机一个桃心
     *
     * @return
     */
    private int randomHeart() {
        return hearts[random.nextInt(hearts.length)];
    }

    /**
     * 乘积
     *
     * @return
     */
    private float pow(float a, int b) {
        return (float) Math.pow(a, b);
    }

    /**
     * 动画结束，移除view
     */
    private class EndAnimatorListener extends AnimatorListenerAdapter {

        private View target;

        public EndAnimatorListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView(target);
        }
    }

    private class BezierListener implements ValueAnimator.AnimatorUpdateListener {

        private View target;

        public BezierListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //这里获取到贝塞尔曲线计算出来的的x y值
            PointF pointF = (PointF) animation.getAnimatedValue();
            target.setX(pointF.x);
            target.setY(pointF.y);
            //渐隐alpha动画
            target.setAlpha(1 - animation.getAnimatedFraction());
        }
    }

    /**
     * 三阶贝塞尔生成器
     */
    public class BezierEvaluator implements TypeEvaluator<PointF> {

        //        中间点
        private PointF pointF1;
        private PointF pointF2;

        public BezierEvaluator(PointF pointF1, PointF pointF2) {
            this.pointF1 = pointF1;
            this.pointF2 = pointF2;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

            float time1 = 1.0f - fraction;
            PointF point = new PointF();//结果

            point.x = pow(time1, 3) * (startValue.x)            //  
                    + 3 * pow(time1, 2) * fraction * (pointF1.x) //
                    + 3 * time1 * pow(fraction, 2) * (pointF2.x) //
                    + pow(fraction, 3) * (endValue.x);

            point.y = pow(time1, 3) * (startValue.y)                //
                    + 3 * pow(time1, 2) * fraction * (pointF1.y) //
                    + 3 * time1 * pow(fraction, 2) * (pointF2.y) //
                    + pow(fraction, 3) * (endValue.y);
            return point;
        }
    }
}