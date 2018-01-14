package com.qiuchenly.ibang.Base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by qiuchenly on 2017/12/24.
 */
abstract class mBaseActivity: AppCompatActivity() {

    abstract fun InitView(mSet:mSetting):mSetting

    abstract fun InitViewEvent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mSet = InitView(mSetting(0))
        setContentView(mSet.mViewID)
        InitViewEvent()
    }
    class mSetting(var mViewID:Int)
}