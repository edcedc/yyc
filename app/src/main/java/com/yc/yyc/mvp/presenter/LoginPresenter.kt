package com.yc.yyc.mvp.presenter

import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.net.RetrofitManager
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import com.yc.yyc.mar.MyApplication
import com.yc.yyc.mvp.impl.LoginContract
import com.yc.yyc.utils.FileOperate
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileOutputStream


/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/19
 * Time: 17:05
 */
class LoginPresenter : BasePresenter<LoginContract.View>(),LoginContract.Presenter{

        override fun onLogin(phone : String, pwd : String) {
            val disposable = RetrofitManager.service.htmltextIndex()
                .compose(SchedulerUtils.ioToMain())
                .subscribe({ json ->
                    mRootView?.apply {

                        val jsonString = Gson().toJson(json)
                        val bean = JSONObject(jsonString)
                        val data = bean.optJSONObject("data")
                        val classify = data.optJSONArray("classify")
                        /*val array = JSONArray()
                        for (i in 0 until array.length()) {
                            val `object` = array.optJSONObject(i)
                        }*/
                        val classifyArray = JSONArray()
                        val app = data.optJSONArray("app")

                        for (i in 0 until app.length()){
                            val any = app[i]
                            val obt = JSONObject()
                            obt.put("id", i)

                            val split = any.toString().split("-")

                            obt.put("name", split[0])
                            classifyArray.put(obt)
                        }
//                        LogUtils.e(classifyArray)
//                        Log.e("-----", classifyArray.toString())

                        mRootView!!.setNameHead(classifyArray.toString())
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