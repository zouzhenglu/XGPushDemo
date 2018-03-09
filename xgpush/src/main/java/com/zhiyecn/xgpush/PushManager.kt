package com.zhiyecn.xgpush

import android.content.Context
import com.tencent.android.tpush.XGIOperateCallback
import com.tencent.android.tpush.XGPushConfig
import com.tencent.android.tpush.XGPushManager

interface IXGPushManager {
    fun init(isDebug: Boolean)
    fun login(account: String, tags: Set<String>, block: () -> Unit)
    fun logout(account: String, tags: Set<String>, block: () -> Unit)
}

class PushManager(private val ctx: Context) : IXGPushManager {

    //    private var _token: String? = null
    //    val token: String?
    //        get() = _token ?: XGPushConfig.getToken(ctx)

    override fun init(isDebug: Boolean) {
        XGPushConfig.enableDebug(ctx, isDebug)
        //        println("token1:${XGPushConfig.getToken(ctx)}")

        //小米
        XGPushConfig.setMiPushAppId(ctx, PushConfig.MI_APPID)
        XGPushConfig.setMiPushAppKey(ctx, PushConfig.MI_APPKEY)
        //华为
        /**
         * http://docs.developer.qq.com/xg/android_access/huawei_push.html
         * 如果出现：
         * otherPushType = huawei otherPushToken = null,这个日志
         * 请在注册代码之前调用：
         * XGPushConfig.setHuaweiDebug(true)
         */
        XGPushConfig.setHuaweiDebug(isDebug)
        //魅族
        XGPushConfig.setMzPushAppId(ctx, PushConfig.MZ_APPID)
        XGPushConfig.setMzPushAppKey(ctx, PushConfig.MZ_APPKEY)

        //打开第三方推送
        XGPushConfig.enableOtherPush(ctx, true)
    }

    private var loginCnt = 0
    override fun login(account: String, tags: Set<String>, block: () -> Unit) {
        loginCnt++
        toLogin(account, tags,
                success = {
                    loginCnt = 0
                    block()
                },
                fail = {
                    if (loginCnt < 3) {
                        login(account, tags, block)
                    }
                }
        )

        //        // 获取token
        //        println("token2:${XGPushConfig.getToken(ctx)}")

    }

    private fun toLogin(account: String, tags: Set<String>, success: () -> Unit, fail: () -> Unit) {
        XGPushManager.bindAccount(ctx, account, object : XGIOperateCallback {
            override fun onSuccess(data: Any, flag: Int) {
                success()
            }

            override fun onFail(data: Any, errCode: Int, msg: String) {
                fail()
            }
        })
        tags.forEach { tag ->
            XGPushManager.setTag(ctx, tag)
        }

    }

    override fun logout(account: String, tags: Set<String>, block: () -> Unit) {
        XGPushManager.delAccount(ctx, account)
        tags.forEach { tag ->
            XGPushManager.deleteTag(ctx, tag)
        }
    }


}