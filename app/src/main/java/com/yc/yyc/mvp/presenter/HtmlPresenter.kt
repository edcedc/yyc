package com.yc.yyc.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.net.RetrofitManager
import com.hazz.kotlinmvp.net.exception.ErrorStatus
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import com.yc.yyc.mvp.impl.HtmlContract
import com.yc.yyc.ui.act.HtmlAct

/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/23
 * Time: 19:34
 */
class HtmlPresenter  : BasePresenter<HtmlContract.View>(), HtmlContract.Presenter{

    override fun onUrl(type: Int) {
        when(type){
            HtmlAct.USER_AGREEMENT -> {
                informationAgreement()
            }
        }
    }

    fun informationAgreement(){
        var disposable = RetrofitManager.service.informationAgreement()
            .compose(SchedulerUtils.ioToMain())
            .subscribe({ bean ->
                mRootView?.apply {
                    if (bean.code == ErrorStatus.SUCCESS){
                        val data = bean.data
                        mRootView?.setUrl(data?.title, data?.content)
                    }
                }
            }, { t ->
                mRootView?.apply {
                    //处理异常
                    mRootView?.errorText(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                }
            })

        addSubscription(disposable)
    }

}