package cn.smaxlyb.mall.ibrary.global

import cn.smaxlyb.mall.ibrary.utils.storage.MemoryStore
import com.joanzapata.iconify.IconFontDescriptor
import com.joanzapata.iconify.Iconify

class Configurator private constructor() {
    // 定义图标集列表
    private val mIcons = ArrayList<IconFontDescriptor>()

    // 单例
    private object Holder {
        internal val singleton = Configurator()
    }

    companion object {
        // 获取单例
        @JvmStatic
        fun getInstance(): Configurator = Holder.singleton

        // 获取全局存储容器
        private val mStore: MemoryStore = MemoryStore.getInstance()

        // 获取handler
        private val mHandler = android.os.Handler()
    }

    // 初始化
    init {
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY, false)
            .addData(GlobalKeys.HANDLER, mHandler)
    }

    private fun initIcons() {
        // 循环加入所有图标样式集合
        if (mIcons.size > 0) {
            val initializer = Iconify.with(mIcons[0])
            for (i in 1 until mIcons.size) {
                initializer.with(mIcons[i])
            }
        }
    }

    // 全局api服务器的host
    fun withApiHost(host: String): Configurator {
        mStore.addData(GlobalKeys.API_HOST, host)
        return this
    }

    fun configure() {
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY, true)
        initIcons()
    }

    fun withIcon(descriptor: IconFontDescriptor): Configurator {
        mIcons.add(descriptor)
        return this
    }

    private fun checkConfiguration() {
        val isReady = mStore.getData<Boolean>(GlobalKeys.IS_CONFIGURE_READY)
        if (!isReady) {
            throw RuntimeException("configure is not ready!")
        }
    }

    fun <T> getConfiguration(key: String): T {
        checkConfiguration()
        return mStore.getData(key)
    }

    fun <T> getConfiguration(key: Enum<*>): T {
        checkConfiguration()
        return mStore.getData(key)
    }

}