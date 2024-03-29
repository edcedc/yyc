package com.yc.tea.api

import com.yc.yyc.bean.BaseResponseBean
import com.yc.yyc.bean.DataBean
import io.reactivex.Observable
import retrofit2.http.*


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
        @Url url: String = "assetsDetail"
    ): Observable<Object>

    @GET("assetsList")
    fun getFirstHomeData(@Query("userid") num:String?= "CDA015922BC44391AA00C9AF8C2DF768", @Query("companyid") companyid:String? = "dbs"
                         , @Query("assetno") assetno:String?= "", @Query("lastcalldate") lastcalldate:String?= ""):Observable<List<DataBean>>

    @GET
    fun getIssueData1(@Url url: String): Observable<DataBean>

}