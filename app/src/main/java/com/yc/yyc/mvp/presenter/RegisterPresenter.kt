package com.yc.yyc.mvp.presenter

import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.StringUtils
import com.yc.yyc.R
import com.yc.yyc.base.BasePresenter
import com.yc.yyc.mar.MyApplication
import com.yc.yyc.mvp.impl.RegisterContract
import com.yc.yyc.net.exception.ErrorStatus
import com.yc.yyc.net.exception.ExceptionHandle
import com.yc.yyc.net.exception.RetrofitManager
import com.yc.yyc.net.exception.SchedulerUtils

/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/23
 * Time: 15:15
 */
class RegisterPresenter : BasePresenter<RegisterContract.View>(), RegisterContract.Presenter{


    override fun onCode(phone: String) {
        if (StringUtils.isEmpty(phone)) {
            showToast(MyApplication.mContext?.resources?.getString(R.string.please_phone) as  String)
            return
        }
        mRootView?.showLoading()
//        var disposable = RetrofitManager.service.userGetRegisterCode(phone)
//            .compose(SchedulerUtils.ioToMain())
//            .subscribe({ bean ->
//                mRootView?.apply {
//                    mRootView?.hideLoading()
//                    if (bean.code == ErrorStatus.SUCCESS){
//                        mRootView?.setCode()
//                    }
//                    showToast(bean.desc as String)
//                }
//            }, { t ->
//                mRootView?.apply {
//                    //处理异常
//                    mRootView?.errorText(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
//                }
//            })
//
//        addSubscription(disposable)
    }

    override fun onSure(phone: String, code: String, pwd: String, pwd1: String, checked: Boolean?) {
        if (!RegexUtils.isMobileExact(phone)) {
            showToast(MyApplication.mContext?.resources?.getString(R.string.error_phone) as String)
            return
        }
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(pwd1)) {
            showToast(MyApplication.mContext?.resources?.getString(R.string.error_) as  String)
            return
        }
        if (!pwd.equals(pwd1)){
            showToast(MyApplication.mContext?.resources?.getString(R.string.please_pwd2) as String)
            return
        }
        if (checked == false) {
            showToast(MyApplication.mContext?.resources?.getString(R.string.error_1) as  String)
            return
        }
        mRootView?.showLoading()
        val disposable = RetrofitManager.service.userRegister(phone, code, pwd, pwd1)
            .compose(SchedulerUtils.ioToMain())
            .subscribe({ bean ->
                mRootView?.apply {
                    mRootView?.hideLoading()
                    if (bean.code == ErrorStatus.SUCCESS){
                    }
                    showToast(bean.desc as String)
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
