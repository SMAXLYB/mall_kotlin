package cn.smaxlyb.mall

import android.app.Application
import cn.smaxlyb.mall.ibrary.global.Mall

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        Mall.init(this)
            .withApiHost("http://123.123.com")
            .configure()
    }
}