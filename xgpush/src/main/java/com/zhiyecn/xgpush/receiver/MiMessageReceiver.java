package com.zhiyecn.xgpush.receiver;

import android.content.Context;
import android.util.Log;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/**
 * Created by kzl on 2018/3/7.
 */

public class MiMessageReceiver extends PushMessageReceiver {
    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageArrived(context, miPushMessage);
        Log.d("MiMessageReceiver", "onNotificationMessageArrived:" + miPushMessage.getContent());
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage);
        Log.d("MiMessageReceiver", "onNotificationMessageClicked:" + miPushMessage.getContent());
    }
}
