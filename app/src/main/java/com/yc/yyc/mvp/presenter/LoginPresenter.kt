package com.yc.yyc.mvp.presenter

import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.yc.yyc.base.BasePresenter
import com.yc.yyc.mar.MyApplication
import com.yc.yyc.mvp.impl.LoginContract
import com.yc.yyc.net.exception.ExceptionHandle
import com.yc.yyc.net.exception.RetrofitManager
import com.yc.yyc.net.exception.SchedulerUtils
import java.io.FileOutputStream


/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/19
 * Time: 17:05
 */
class LoginPresenter : BasePresenter<LoginContract.View>(),LoginContract.Presenter{

        override fun onLogin(phone : String, pwd : String) {
            /*val disposable = RetrofitManager.service.getFirstHomeData()
                .subscribe({
                        bean ->
                    mRootView?.apply {

                        //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
//                        val bannerItemList = bean.issueList[0].itemList

                        LogUtils.e(bean)
                    }
                },{ t ->
                    mRootView?.apply {
                        //处理异常
                        mRootView?.errorText(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }

                })*/

            val disposable = RetrofitManager.service.getFirstHomeData()
                .compose(SchedulerUtils.ioToMain())
                .subscribe({ bean ->
                    mRootView?.apply {

                        Log.e("xxx", "进来了" + bean.get(0).id + "???")
                    }
                }, { t ->
                    mRootView?.apply {
                        //处理异常
                        mRootView?.errorText(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })
            addSubscription(disposable)

  /*   val create = RequestBody.create(MediaType.parse("multipart/form-data"), File(localMediaList[0].path))
       val imgBody = MultipartBody.Part.createFormData("image", "不知传什么",  create)

        val map = HashMap<String, RequestBody>()
        for (i in localMediaList.indices) {
            val create = RequestBody.create(MediaType.parse("multipart/form-data"), File(localMediaList[i].path))
            map.put("file" + i, create)
        }*/

    }

    fun saveToRom(content: String) {
        try {
            val fos: FileOutputStream = MyApplication.mContext.openFileOutput("tel.txt", Context.MODE_PRIVATE)
            fos.write(content.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            LogUtils.e(e)
            e.printStackTrace()
        }
    }


}