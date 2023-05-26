package com.yc.yyc.controller

/**
 * 作者：yc on 2018/6/20.
 * 邮箱：501807647@qq.com
 * 版本：v1.0
 */

class CloudApi private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object{

        val url =
            "47.243.120.137"

        val SERVLET_URL = "http://" +
                url + "//StandardAMS_AMSWebService_DBSchenker/MobileWebService.asmx/"


//        private val url =
//            "baobab.kaiyanapp.com"
//
        val SERVLET_IMG_URL = "https://" +
                url + "/"
//
//        val SERVLET_URL = SERVLET_IMG_URL + "api/"

        private var TEST_URL = "" //测试

        private val TAG = "CloudApi"

    }


}