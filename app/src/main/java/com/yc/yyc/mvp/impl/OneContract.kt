package com.yc.yyc.mvp.impl

import com.hazz.kotlinmvp.base.IBaseListView
import com.hazz.kotlinmvp.base.IListPresenter
import com.yc.yyc.bean.DataBean

/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/9/25
 * Time: 11:46
 */
interface OneContract {

    interface View : IBaseListView {

    }

    interface Presenter: IListPresenter<View> {

        fun onRequest(page: Int)

    }

}