package cn.dbboy.commentlib.utils;

import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * Created by db.boy on 2018/5/15.
 */
public class AnimatorUtil {


    public static void scaleAnimate(final View view) {
            /* 
                AnimationSet相当于一个动画的集合，true表示使用Animation的interpolator 
                false则是使用自己的。 
                Interpolator 被用来修饰动画效果，定义动画的变化率，可以使存在的动画效果 
                accelerated(加速)，decelerated(减速),repeated(重复),bounced(弹跳)等。 
             */
        AnimationSet animationSet = new AnimationSet(true);  
            /* 
            0~1 之间 浮点数，表示缩放比例，1-正常大小，0-无
             view坐标系，x轴-水平，y轴-竖直
             0：X轴起始 fromX
             1：X轴结束 toX
             2：Y轴起始 fromY 
             3：Y轴结束 toY 
             4：pivotXType  - X轴相对于View位置的类型 
             5：pivotXValue - 动画相对于View的X轴的开始位置 
             6：pivotXType  - Y轴上相对于View位置的类型 
             7：pivotYValue - 动画相对于View的Y轴的开始位置 

              (4,5)(6,7)中心点 
               0.5f代表从中心缩放
             */
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.9f, 1f, 0.9f, 1,//
                Animation.RELATIVE_TO_SELF, 0.5f, //
                Animation.RELATIVE_TO_SELF, 0.5f);
        //        重复
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        //        重复次数
        scaleAnimation.setRepeatCount(2);
        scaleAnimation.setDuration(800);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中  
        animationSet.addAnimation(scaleAnimation);
        //启动动画  
        view.startAnimation(animationSet);
    }

    /**
     * 属性动画实现
     *
     * @param view
     */
    public static void scaleAnimate2(View view) {

        if (view == null) {
            return;
        }

        float scaleSmall = 0.5f, scaleLarge = 1.0f;
        float shakeDegrees = 0.5f;
        //先变小后变大
        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,//
                Keyframe.ofFloat(0f, scaleLarge),                                 //
                Keyframe.ofFloat(1f, scaleSmall),                               //
                Keyframe.ofFloat(0.5f, scaleLarge),                                  //
                Keyframe.ofFloat(1f, scaleLarge),                                    //
                Keyframe.ofFloat(1f, scaleSmall),                               //
                Keyframe.ofFloat(0.5f, scaleLarge),                                  //
                Keyframe.ofFloat(1f, scaleLarge)                                     //
        );
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,   //
                Keyframe.ofFloat(0f, scaleLarge),                                    //
                Keyframe.ofFloat(1f, scaleSmall),                                  //
                Keyframe.ofFloat(0.5f, scaleLarge),                                     //
                Keyframe.ofFloat(1f, scaleLarge),                                       //
                Keyframe.ofFloat(1f, scaleSmall),                                  //
                Keyframe.ofFloat(0.5f, scaleLarge),                                     //
                Keyframe.ofFloat(1f, scaleLarge)                                        //
        );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleXHolder, scaleYHolder);
        objectAnimator.setDuration(1000);
        objectAnimator.start();

    }

    /**
     * X轴
     *
     * @param view
     */
    public static void rotationAnimateX(View view) {
        float shakeDegrees = 100f;
        PropertyValuesHolder rotateHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION_X, //
                Keyframe.ofFloat(0f, 0f),                                                        //
                Keyframe.ofFloat(0.1f, -shakeDegrees),                                           //
                Keyframe.ofFloat(0.2f, shakeDegrees),                                       //
                Keyframe.ofFloat(1.0f, 0f));

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, rotateHolder);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
    }

    /**
     * 旋转
     *
     * @param view
     */
    public static void rotationAnimate(View view) {
        
        /*
            rotation针对2D旋转
        
            rotation - 控制View对象围绕支点进行旋转 
            rotationX - 控制View对象围绕X支点进行旋转 
            rotationY - 控制View对象围绕Y支点进行旋转 
            
            fraction:动画过渡时间因子，决定了动画变化的速率，值为0-1之间
         */
        float shakeDegrees = 100f;
        PropertyValuesHolder rotateHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION, //
                Keyframe.ofFloat(0f, 0f),                                                        //
                Keyframe.ofFloat(0.1f, -shakeDegrees),                                           //
                Keyframe.ofFloat(0.2f, shakeDegrees),                                       //
                Keyframe.ofFloat(0.3f, -shakeDegrees),                                           //
                Keyframe.ofFloat(0.4f, shakeDegrees),                                            //
                Keyframe.ofFloat(0.5f, -shakeDegrees),                                           //
                Keyframe.ofFloat(0.6f, shakeDegrees),                                            //
                Keyframe.ofFloat(0.7f, -shakeDegrees),                                           //
                Keyframe.ofFloat(0.8f, shakeDegrees),                                            //
                Keyframe.ofFloat(0.9f, -shakeDegrees),                                           //
                Keyframe.ofFloat(1.0f, 0f));

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, rotateHolder);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
    }


    /**
     * Y轴旋转
     *
     * @param view
     */
    public static void rotationAnimateY(View view) {

        float shakeDegrees = 100f;
        PropertyValuesHolder rotateHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION_Y, //
                Keyframe.ofFloat(0f, 0f),                                                        //
                Keyframe.ofFloat(0.1f, -shakeDegrees),                                           //
                Keyframe.ofFloat(0.2f, shakeDegrees),                                       //
                Keyframe.ofFloat(1.0f, 0f));

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, rotateHolder);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
    }

    public static void colorAnimate(View view, boolean isEvaluator) {
        //对背景色颜色进行改变，操作的属性为"backgroundColor"
        ValueAnimator animator = ObjectAnimator.ofInt(view, "backgroundColor", 0x44ff0000, 0x6600ff00);
        animator.setDuration(1500);
        //如果要颜色渐变必须要ArgbEvaluator，来实现颜色之间的平滑变化，否则会出现颜色不规则跳动
        if (isEvaluator) {
            animator.setEvaluator(new ArgbEvaluator());
        }
        animator.start();
    }

}
              
              