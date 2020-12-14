package com.xingzhi.android.biz.push.receive

import android.content.Context
import com.xingzhi.android.biz.push.update.UpdatePushToken
import com.xiaomi.mipush.sdk.*

/**
 * Created by xiedongdong on 2020/12/14
 * 以下方法运行在非 UI 线程中
 */
class XiaomiPushReceiver : PushMessageReceiver() {

    /**
     * 方法用来接收客户端向服务器发送命令后的响应结果
     */
    override fun onCommandResult(context: Context?, message: MiPushCommandMessage?) {
        super.onCommandResult(context, message)
        if (message == null) {
            return
        }
    }

    /**
     * 方法用来接收服务器向客户端发送的透传消息
     */
    override fun onReceivePassThroughMessage(context: Context?, message: MiPushMessage?) {
        super.onReceivePassThroughMessage(context, message)
    }

    /**
     * 自定义消息会触发此方法
     * 方法用来接收服务器向客户端发送的通知消息，这个回调方法会在用户手动点击通知后触发
     */
    override fun onNotificationMessageClicked(context: Context?, message: MiPushMessage?) {
        super.onNotificationMessageClicked(context, message)
        if (message == null) {
            return
        }
        // 小米推送走"预定义消息"，暂且用不到
        //AppOperator.runOnMainThread { MessageHandler.handle(context, message.content) }
    }

    /**
     * 方法用来接收服务器向客户端发送的通知消息，这个回调方法是在通知消息到达客户端时触发；另外应用在前台时不弹出通知的通知消息到达客户端也会触发这个回调函数
     */
    override fun onNotificationMessageArrived(context: Context?, message: MiPushMessage?) {
        super.onNotificationMessageArrived(context, message)
        if (message == null) {
            return
        }
    }

    /**
     * 方法用来接收客户端向服务器发送注册命令后的响应结果
     */
    override fun onReceiveRegisterResult(context: Context?, message: MiPushCommandMessage?) {
        super.onReceiveRegisterResult(context, message)
        if (message == null) {
            return
        }

        val command = message.command
        val arguments = message.commandArguments
        if (MiPushClient.COMMAND_REGISTER == command) {
            if (message.resultCode.toInt() == ErrorCode.SUCCESS && arguments.size > 0) {
                val mRegID = arguments[0]
                UpdatePushToken.instance.update(mRegID)
            }
        }
    }

}