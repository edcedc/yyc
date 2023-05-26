package com.yc.yyc.ui

import android.os.Bundle
import android.view.View
import com.yc.yyc.R
import com.yc.yyc.base.BaseFragment
import com.yc.yyc.mvp.impl.LoginContract
import com.yc.yyc.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.f_login.*

/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2019/10/24
 * Time: 11:44
 */
class LoginFrg : BaseFragment(), LoginContract.View{

    val mPresenter by lazy { LoginPresenter() }

    override fun setNameHead(state_name_head: String) {
    }

    override fun setError() {
    }

    override fun getLayoutId(): Int = R.layout.f_login

    override fun initParms(bundle: Bundle) {
    }

    override fun initView(rootView: View) {
        mPresenter.init(this)

        bt_play.setOnClickListener {
            mPresenter.onLogin("", "")
        }

    }

}