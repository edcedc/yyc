package com.yc.yyc.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.yc.yyc.R
import me.yokeyword.fragmentation.SwipeBackLayout
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity

/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/16
 * Time: 17:41

 */
abstract class BaseActivity : SwipeBackActivity() {

    protected lateinit var act: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())
        act = this
        // 初始化参数
        var bundle = intent.extras
        if (bundle == null) {
            bundle = Bundle()
        }
        initParms(bundle)
        initView()
        swipeBackLayout.setEdgeOrientation(SwipeBackLayout.EDGE_ALL)
    }

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     *  传参
     */
    abstract fun initParms(bundle: Bundle)

    protected fun showToast(str: String) {
        act.runOnUiThread {
            ToastUtils.showShort(str)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
     * @return true: Activity优先滑动退出;  false: Fragment优先滑动退出
     */
    override fun swipeBackPriority(): Boolean {
        return super.swipeBackPriority()
    }

    protected fun setTitle1(title : String){
        setTitle(true, title, null, -1)
    }
    protected fun setTitle1(title : String, right : String){
        setTitle(true, title, right, -1)
    }


    private fun setTitle(isBack : Boolean, title : String, rightTitle : String?, rightImg : Int){
        setSofia(false)
        val mAppCompatActivity = act as AppCompatActivity
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val topTitle = findViewById<AppCompatTextView>(R.id.top_title)
        val topRightFy = findViewById<FrameLayout>(R.id.top_right_fy)
        val topRight = findViewById<AppCompatTextView>(R.id.top_right)
        val topImg = findViewById<AppCompatImageView>(R.id.top_img)
        mAppCompatActivity.setSupportActionBar(toolbar)
        mAppCompatActivity.supportActionBar?.setTitle("")
        if (isBack) {
            toolbar?.setNavigationOnClickListener { act?.onBackPressed() }
        } else {
            toolbar?.navigationIcon = null
        }
        topTitle?.setText(title)
        if (!StringUtils.isEmpty(rightTitle)) {
            topRightFy?.setVisibility(View.VISIBLE)
            topRight?.setText(rightTitle)
            topImg?.setVisibility(View.GONE)
        } else if (rightImg != -1) {
            topRightFy?.setVisibility(View.VISIBLE)
            topImg?.setBackgroundResource(rightImg)
            topRight?.setVisibility(View.GONE)
        } else {

        }
        topRightFy?.setOnClickListener({ setOnRightClickListener() })
    }


    protected open fun setOnRightClickListener( ) {

    }

    protected fun setSofia(isFullScreen: Boolean) {
        if (!isFullScreen) {
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            ImmersionBar.with(this).statusBarColor(R.color.red_FF5A60).titleBar(toolbar).statusBarDarkFont(false).init()
        } else {
            ImmersionBar.with(this).transparentBar().statusBarDarkFont(false).init()
        }
    }

    private var dialog: ProgressDialog? = null
    private val handler_load = 0
    private val handler_hide = 1

    fun showLoading() {
        mHandler.sendEmptyMessage(handler_load)
    }

    fun hideLoading() {
        mHandler.sendEmptyMessage(handler_hide)
    }

    open fun errorText(text : String, errorCode : Int) {
        LogUtils.e(text, errorCode)
        showToast(text + "\n" + errorCode)
        hideLoading()
    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                handler_load -> {
                    if (dialog != null && dialog!!.isShowing()) return
                    dialog = ProgressDialog(act)
                    dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog!!.setCanceledOnTouchOutside(false)
                    dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    dialog!!.setMessage("正在请求网络...")
                    dialog!!.show()
                }
                handler_hide -> {
                    if (dialog != null && dialog!!.isShowing()) {
                        dialog!!.dismiss()
                    }
                }
            }
        }
    }

    /**
     * Android 点击EditText文本框之外任何地方隐藏键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(v!!.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.height
            val right = left + v.width
            return if (event.x > left && event.x < right
                && event.y > top && event.y < bottom
            ) {
                // 点击的是输入框区域，保留点击EditText的事件
                false
            } else {

                true
            }
        }
        return false
    }

}