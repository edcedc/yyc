package com.yc.yyc.mvp.impl

import com.yc.yyc.base.IBaseView
import com.yc.yyc.base.IPresenter


/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/23
 * Time: 19:33
 */
interface HtmlContract{

    interface View : IBaseView {
        fun setUrl(title: String?, content: String?)
    }

    interface Presenter: IPresenter<View> {

        fun onUrl(type : Int)

    }

}
