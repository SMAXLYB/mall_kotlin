package cn.smaxlyb.mall.ibrary.ui.recycler

import com.chad.library.adapter.base.entity.MultiItemEntity

// 数据实体类
class ItemEntity private constructor(fields: HashMap<ItemFields, Any>) : MultiItemEntity {

    private var mFields: HashMap<ItemFields, Any> = fields

    override fun getItemType(): Int {
        return mFields[ItemFields.ITEM_TYPE] as Int
    }


    fun <T> getField(key: ItemFields): T {
        @Suppress("UNCHECKED_CAST")
        return mFields[key] as T
    }

    // 静态内部类
    class Builder {
        private val mFields = HashMap<ItemFields, Any>()

        fun setField(key: ItemFields, value: Any?): Builder {

            value?.let { mFields[key] = it }
            return this
        }

        fun setFields(map: HashMap<ItemFields, Any>): Builder {
            mFields.putAll(map)
            return this
        }

        fun build(): ItemEntity {
            return ItemEntity(mFields)
        }
    }
}