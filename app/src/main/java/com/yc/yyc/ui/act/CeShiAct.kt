package com.yc.yyc.ui.act

import android.os.Bundle
import com.yc.yyc.R
import com.yc.yyc.base.BaseActivity
import com.yc.yyc.mvp.impl.LoginContract
import com.yc.yyc.mvp.presenter.LoginPresenter
import com.yc.yyc.utils.FileOperate
import java.io.File

/**
 * Created by Android Studio.
 * User: ${edison}
 * Date: 2020/7/2
 * Time: 11:55
 */
class CeShiAct : BaseActivity(), LoginContract.View{

    val mPresenter by lazy { LoginPresenter() }


    override fun getLayoutId(): Int = R.layout.b_title_recycler

    override fun initView() {
        setTitle1("ggggg")
        mPresenter.init(this)

        mPresenter.onLogin("", "")
    }

    override fun initParms(bundle: Bundle) {
    }

    override fun setNameHead(state_name_head: String) {
        val filePath = getExternalFilesDir(null).absolutePath+
                File.separator+"a.txt"
        File(filePath).writeText(state_name_head)//覆盖原先的文本内容
    }

    override fun setError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}