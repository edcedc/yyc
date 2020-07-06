package com.hazz.kotlinmvp.api

import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.Utils
import com.yc.yyc.bean.BaseListBean
import com.yc.yyc.bean.BaseResponseBean
import com.yc.yyc.bean.DataBean
import com.yc.yyc.utils.cache.ShareSessionIdCache
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import java.util.*


/**
 * Created by xuhao on 2017/11/16.
 * Api 接口
 */

interface ApiService{

    //注册
    @GET("user/register")
    fun userRegister(@Query("mobile") mobile : String, @Query("code") code : String, @Query("password") password : String, @Query("repeatPassword") repeatPassword : String): Observable<BaseResponseBean<Object>>

    //天行数据转换详情链接  今日头条
    @POST
    fun htmltextIndex(
        @Url url: String = "http://ei.ssp.eastdaymedia.com.cn/media-console/sys/dataDic/getAppInstall"
    ): Observable<Object>

}