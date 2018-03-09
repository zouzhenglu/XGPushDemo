package com.zhiyecn.xgpush.receiver

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.meizu.cloud.pushinternal.DebugLogger
import com.meizu.cloud.pushsdk.MzPushMessageReceiver
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder
import com.meizu.cloud.pushsdk.platform.message.*


class MZMessageReceiver : MzPushMessageReceiver() {
    @Deprecated("")
    override fun onRegister(context: Context, s: String) {
        DebugLogger.i(TAG, "onRegister pushID " + s)
        print(context, "receive pushID " + s)
    }

    @Deprecated("")
    override fun onUnRegister(context: Context, b: Boolean) {
        DebugLogger.i(MzPushMessageReceiver.TAG, "onUnRegister " + b)
        print(context, context.packageName + " onUnRegister " + b)
    }

    override fun onMessage(context: Context, s: String) {
        DebugLogger.i(TAG, "onMessage " + s)
        //print(context,context.packageName + " receive message " + s);
    }

    override fun onMessage(context: Context, intent: Intent) {
        Log.i(TAG, "flyme3 onMessage ")
        val content = intent.extras!!.toString()
        print(context, "flyme3 onMessage " + content)
    }

    fun onMessage(context: Context, message: String, platformExtra: String) {
        Log.i(TAG, "onMessage $message platformExtra $platformExtra")
        print(context, context.packageName + " receive message " + message)
    }


    override fun onPushStatus(context: Context, pushSwitchStatus: PushSwitchStatus) {
    }

    override fun onRegisterStatus(context: Context, registerStatus: RegisterStatus) {
        DebugLogger.i(TAG, "onRegisterStatus " + registerStatus + " " + context.packageName)
        //print(this," onRegisterStatus " + registerStatus);
    }

    override fun onUnRegisterStatus(context: Context, unRegisterStatus: UnRegisterStatus) {
        DebugLogger.i(TAG, "onUnRegisterStatus " + unRegisterStatus + " " + context.packageName)
    }

    override fun onSubTagsStatus(context: Context, subTagsStatus: SubTagsStatus) {
        DebugLogger.i(TAG, "onSubTagsStatus " + subTagsStatus + " " + context.packageName)
    }

    override fun onSubAliasStatus(context: Context, subAliasStatus: SubAliasStatus) {
        DebugLogger.i(TAG, "onSubAliasStatus " + subAliasStatus + " " + context.packageName)
    }

    override fun onUpdateNotificationBuilder(pushNotificationBuilder: PushNotificationBuilder?) {
    }


    override fun onNotifyMessageArrived(context: Context, message: String) {
        DebugLogger.i(TAG, "onNotifyMessageArrived messsage " + message)
    }

    private fun print(context: Context, info: String) {
        Handler(context.mainLooper).post({ Toast.makeText(context, info, Toast.LENGTH_LONG).show() })
    }

    companion object {
        private val TAG = "MzPushMessageReceiver"
    }

}

