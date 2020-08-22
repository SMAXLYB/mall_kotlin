package cn.smaxlyb.mall.ibrary.utils.log

import android.util.Log
import cn.smaxlyb.mall.ibrary.BuildConfig

object LogUtils {
    private var IS_DEBUG = BuildConfig.DEBUG

    fun d(tag: String, msg: String) {
        if (IS_DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (IS_DEBUG) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (IS_DEBUG) {
            Log.e(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (IS_DEBUG) {
            Log.i(tag, msg)
        }
    }
}