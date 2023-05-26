package com.yc.yyc.mvp.impl

import com.yc.yyc.base.IBaseView
import com.yc.yyc.base.IPresenter


/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/19
 * Time: 17:02
 */
interface LoginContract{

    interface View : IBaseView {

        fun setNameHead(state_name_head: String)
        fun setError()

    }

    interface Presenter: IPresenter<View> {

        fun onLogin(phone : String, pwd : String)

    }

}