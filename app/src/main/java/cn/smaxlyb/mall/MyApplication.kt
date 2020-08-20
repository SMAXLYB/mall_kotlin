package cn.smaxlyb.mall

import android.app.Application
import cn.smaxlyb.mall.ibrary.global.Mall
import com.blankj.utilcode.util.Utils
import com.joanzapata.iconify.fonts.FontAwesomeModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // 工具类初始化
        Utils.init(this)

        // 业务类初始化
        Mall.init(this)
            .withIcon(FontAwesomeModule())
            .withApiHost("http://mock.fulingjie.com/mock/api/")
            .configure()
    }
}