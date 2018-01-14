package com.qiuchenly.ibang.Net

import com.qiuchenly.ibang.Base.httpClient.httpClient
import com.qiuchenly.ibang.Utils.mUtilsJ

/**
 * Created by qiuchenly on 2017/12/24.
 */
class mLantuAPI {
    init {
        mUuid = "0"
    }

    companion object {
        private var mUuid = "0"
        private val mLogin_URL = "http://app.jsahvc.edu.cn/baseCampus/login/login.do"
        private val mLogin_DATA = "{\n" +
                "\t\"appKey\": \"GiITvn\",\n" +
                "\t\"param\": \"{\\\"userName\\\":\\\"{user}\\\",\\\"password\\\":\\\"{pass}\\\",\\\"uuId\\\":\\\"$mUuid\\\",\\\"schoolId\\\":\\\"194\\\"}\",\n" +
                "\t\"time\": {time},\n" +
                "\t\"secure\": 0,\n" +
                "\t\"sign\": \"{signs}\"\n" +
                "}"
        private val mLogin_Sign = "appKey=GiITvn&param={\"userName\":\"{user}\",\"password\":\"{name}\",\"uuId\":\"$mUuid\",\"schoolId\":\"194\"}&secure=0&time={time}"

        fun mLogin(user: String, pass: String):String {
            val t = getTime()
            val s = mUtilsJ.md5(mLogin_Sign.replace("{user}", user)
                    .replace("{pass}", pass)
                    .replace("{time}", t))
            val u = mLogin_DATA.replace("{user}", user)
                    .replace("{pass}", pass)
                    .replace("{time}", t)
                    .replace("{signs}",s)
           val a = httpClient.Request(mLogin_URL,u,"Host: app.jsahvc.edu.cn\n" +
                   "Connection: keep-alive\n" +
                   "Accept: application/json, text/plain, */*\n" +
                   "Content-Type: application/json;charset=UTF-8\n"+
                   "Accept-Language: cn\n" +
                   "User-Agent: Mozilla/5.0 (Linux; Android 7.0; MI 5s Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 lantuMobilecampus lantuMC")
            return a.responseText

        }

        fun getTime(): String {
            return System.currentTimeMillis().toString()
        }
    }


}