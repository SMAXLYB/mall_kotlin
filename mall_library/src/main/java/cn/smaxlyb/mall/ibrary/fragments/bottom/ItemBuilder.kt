package cn.smaxlyb.mall.ibrary.fragments.bottom

class ItemBuilder private constructor() {

    private val mItems = LinkedHashMap<BottomTabBean, ContentItemFragment>()

    // TODO 单例
    companion object {
        internal fun builder(): ItemBuilder = ItemBuilder()
    }

    fun addItem(bean: BottomTabBean, fragment: ContentItemFragment): ItemBuilder {
        mItems[bean] = fragment
        return this
    }

    fun addItems(items: LinkedHashMap<BottomTabBean, ContentItemFragment>): ItemBuilder {
        mItems.putAll(items)
        return this
    }

    fun build(): LinkedHashMap<BottomTabBean, ContentItemFragment> {
        return mItems
    }
}