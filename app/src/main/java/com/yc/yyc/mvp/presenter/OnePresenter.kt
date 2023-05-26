package com.yc.yyc.mvp.presenter

import android.widget.Toast
import com.yc.yyc.base.BaseListPresenter
import com.yc.yyc.mar.MyApplication
import com.yc.yyc.mvp.impl.OneContract

/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/25
 * Time: 11:46
 */
class OnePresenter : BaseListPresenter<OneContract.View>(), OneContract.Presenter{

    override fun onRequest(page: Int) {
        Toast.makeText(MyApplication.mContext, "能", Toast.LENGTH_SHORT).show()

        /*val disposable = RetrofitManager.service.circlePage(page)
            .compose(SchedulerUtils.ioToMain())
            .subscribe({ bean ->
                mRootView?.apply {
                    if (bean.code == ErrorStatus.SUCCESS){
                        val data = bean.data
                        val list = data?.list
                        if (list != null){
                            mRootView?.setData(list as Object)
                        }
                        mRootView?.setRefreshLayoutMode(data?.totalRow as Int)
                    }
                }
            }, { t ->
                mRootView?.apply {
                    //处理异常
                    mRootView?.errorText(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                }

            })

        addSubscription(disposable)*/
    }

}