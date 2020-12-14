package com.xingzhi.android.biz.push.update

/**
 * Created by xiedongdong on 2020/12/14
 */
class UpdatePushToken {

    companion object {
        val instance: UpdatePushToken by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UpdatePushToken()
        }

        // 失败后，最大重试次数
        private const val MAX_RETRY = 3
    }

    private var token: String? = null

    // 已经尝试次数
    private var retryCount = 0

    fun update(token: String) {
        this.token = token
        retryCount = 0

        updateUserPushToken()
    }

    /**
     * TODO
     * 根据业务逻辑，调接口，更新Token。
     * 如果调失败，增加尝试次数。
     */
    private fun updateUserPushToken() {

    }

}