package com.yc.yyc.controller

import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.google.gson.Gson
import com.yc.yyc.MainActivity
import com.yc.yyc.base.BaseFragment
import com.yc.yyc.bean.DataBean
import com.yc.yyc.ui.act.*


/**
 * Created by Administrator on 2017/2/22.
 */

class UIHelper private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        fun startMainAct() {
            ActivityUtils.startActivity(MainActivity::class.java)
        }

       fun startLoginAct() {
            ActivityUtils.startActivity(LoginAct::class.java)
        }


        /**
         *  各种H5
         */
       fun startHtmlAct(type : Int) {
           val bundle = Bundle()
           bundle.putInt("type", type)
           ActivityUtils.startActivity(bundle, HtmlAct::class.java)
        }
       fun startHtmlAct(type : Int, url : String?) {
           val bundle = Bundle()
           bundle.putInt("type", type)
           bundle.putString("url", url)
           ActivityUtils.startActivity(bundle, HtmlAct::class.java)
        }
       fun startHtmlAct(type : Int, url : String?, title: String?) {
           val bundle = Bundle()
           bundle.putInt("type", type)
           bundle.putString("url", url)
           bundle.putString("title", title)
           ActivityUtils.startActivity(bundle, HtmlAct::class.java)
        }

        /**
         *  视频页面
         */
        fun startVideoAct(video: String, image: String) {
            var bundle = Bundle()
            bundle.putString("video", video)
            bundle.putString("image", image)
//            ActivityUtils.startActivity(bundle, VideoAct::class.java)
        }

    }

}