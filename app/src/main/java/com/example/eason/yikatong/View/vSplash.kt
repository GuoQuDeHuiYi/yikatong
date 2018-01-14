package com.qiuchenly.ibang.View

import com.google.gson.Gson
import com.qiuchenly.ibang.Base.httpClient.GsonUtil
import com.qiuchenly.ibang.Base.httpClient.ResponseData
import com.qiuchenly.ibang.Base.mBaseActivity
import com.qiuchenly.ibang.R
import com.qiuchenly.ibang.Base.httpClient.httpClient
import com.qiuchenly.ibang.DataModel.LOGIN

class vSplash : mBaseActivity() {
    override fun InitView(mSet: mSetting): mSetting {
        mSet.mViewID= R.layout.v_splash
        return mSet
    }

    override fun InitViewEvent() {
        Thread{
           var s:String = request.login("123456","12356")
            var g = Gson()
            val a:LOGIN =  g.fromJson<LOGIN>(s,LOGIN::class.java)
            println(a.msgState)
        }.start()
    }
}