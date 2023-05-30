package com.yc.yyc.utils;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.*;
import android.widget.PopupWindow;
import com.blankj.utilcode.util.ScreenUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.yc.yyc.R;

/**
 * 作者：yc on 2018/8/23.
 * 邮箱：501807647@qq.com
 * 版本：v1.0
 */

public class PopupWindowTool {


    /**
     * 创建popupWindow弹窗
     *
     * @param context
     * @param anchor   用于弹出PopupWindow的View
     * @param x        点击坐标到屏幕左边的距离
     * @param y        点击坐标到屏幕上边的距离
     * @param listener popupWindow中的点击事件接口
     */
    public static void createPopupWindow(Context context, View anchor, final OnGestureClickListener listener) {
        // 自定义的布局View
        View view = LayoutInflater.from(context).inflate(R.layout.p_report_copy, null, false);
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setContentView(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(dip2px(context, 160));
        popupWindow.setBackgroundDrawable(new ColorDrawable()); // 需要设置一个背景setOutsideTouchable(true)才会生效
        popupWindow.setFocusable(true); // 防止点击事件穿透
        popupWindow.setOutsideTouchable(true); // 设置点击外部时取消
        int windowPos[] = calculatePopWindowPos(anchor, view, x, y);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(anchor, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);

        view.findViewById(R.id.tv_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.copy();
                }
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.tv_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.report();
                }
                popupWindow.dismiss();
            }
        });
    }

    public interface OnGestureClickListener {
        void copy();

        void report();
    }

    private static int x;
    private static int y;

    /**
     * 获取点击坐标的方法
     *
     * @param view 点击的View
     */
    public static void clickXY(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                return false;
            }
        });
    }

    /**
     * 计算popupWindow在长按view 的什么位置显示
     *
     * @param anchorView  长按锚点的view
     * @param contentView 弹出框的布局View
     * @param touchX      锚点距离屏幕左边的距离
     * @param touchY      锚点距离屏幕上方的距离
     * @return popupWindow在长按view中的xy轴的偏移量
     */
    private static int[] calculatePopWindowPos(final View anchorView, final View contentView,
                                               int touchX, int touchY) {
        final int windowLoc[] = new int[2];
        int offset = 144;
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight();
        final int screenWidth = ScreenUtils.getScreenWidth();
        // 测量弹出框View的宽高
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        final int popHeight = contentView.getMeasuredHeight();
        final int popWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        // 屏幕高度-触点距离左上角的高度 < popupWindow的高度
        // 如果小于弹出框的高度那么说明下方空间不够显示 popupWindow，需要放在触点的上方显示
        final boolean isNeedShowTop = (popHeight + touchY > screenHeight);
        // 判断需要向右边弹出还是向左边弹出显示
        //判断触点右边的剩余空间是否够显示popupWindow 大于就说明够显示
        final boolean isNeedShowRight = (touchX < (screenWidth / 2));
        if (isNeedShowTop) {
            //如果在上方显示 则用 触点的距离上方的距离 - 弹框的高度
            windowLoc[1] = touchY - popHeight;
        } else {
            //如果在下方显示 则用 触点的距离上方的距离
            windowLoc[1] = touchY;
        }
        if (isNeedShowRight) {
            windowLoc[0] = touchX;
        } else {
            //显示在左边的话 那么弹出框的位置在触点左边出现，则是触点距离左边距离 - 弹出框的宽度
            windowLoc[0] = touchX - popWidth - offset;
        }
        return windowLoc;
    }

    /**
     * dp转px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }




    /**
     *  可已复用的弹窗
     * @param act
     */
    public static XPopup.Builder showDialog(Context act){
        return new XPopup.Builder(act)
                .setPopupCallback(new SimpleCallback() {
                    @Override
                    public void onCreated() {
                    }

                    @Override
                    public void beforeShow() {
                        super.beforeShow();
//                        Log.e("tag", "beforeShow，在每次show之前都会执行，可以用来进行多次的数据更新。");
                    }

                    @Override
                    public void onShow() {
                    }
                    @Override
                    public void onDismiss() {
                    }
                    //如果你自己想拦截返回按键事件，则重写这个方法，返回true即可
                    @Override
                    public boolean onBackPressed() {
//                        ToastUtils.showShort("我拦截的返回按键，按返回键XPopup不会关闭了");
                        return false;
                    }
                });
    }



}
