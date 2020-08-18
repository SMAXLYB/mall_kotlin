package cn.smaxlyb.mall.ibrary.utils.storage

// 职责:存储全局属性
class MemoryStore private constructor() {
    // 单例
    private object Holder {
        internal val singleton = MemoryStore()
    }

    // 静态方法获取单例
    companion object {
        @JvmStatic
        fun getInstance(): MemoryStore = Holder.singleton
    }

    // 数据
    private val mDataMap = HashMap<String, Any>()

    // 添加数据
    fun addData(key: String, value: Any): MemoryStore {
        mDataMap[key] = value
        return this
    }

    fun addData(key: Enum<*>, value: Any): MemoryStore {
        addData(key.name, value)
        return this
    }

    // 获取数据
    @Suppress("UNCHECKED_CAST")
    fun <T> getData(key: String): T {
        return mDataMap[key] as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getData(key: Enum<*>): T {
        return getData(key.name)
    }
}