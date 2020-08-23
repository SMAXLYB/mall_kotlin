package cn.smaxlyb.mall.ibrary.ui.recycler

interface IDataConverter {
    fun convert(jsonText: String): List<ItemEntity>
}