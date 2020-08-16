package cn.smaxlyb.mall.ibrary.global

import android.content.Context
import cn.smaxlyb.mall.ibrary.utils.storage.MemoryStore

object Mall {
    val configurator = Configurator.getInstance()

    fun init(context: Context): Configurator {
        MemoryStore.getInstance()
            .addData(GlobalKeys.APPLICATION, context.applicationContext)
        return configurator
    }

    fun <T> getConfiguration(key: String): T {
        return configurator.getConfiguration(key)
    }

    fun <T> getConfiguration(key: Enum<GlobalKeys>): T {
        return configurator.getConfiguration(key)
    }
}