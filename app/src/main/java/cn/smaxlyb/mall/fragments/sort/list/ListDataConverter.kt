package cn.smaxlyb.mall.fragments.sort.list

import cn.smaxlyb.mall.ibrary.ui.recycler.IDataConverter
import cn.smaxlyb.mall.ibrary.ui.recycler.ItemEntity
import cn.smaxlyb.mall.ibrary.ui.recycler.ItemFields
import cn.smaxlyb.mall.ibrary.ui.recycler.ItemType
import com.alibaba.fastjson.JSON

class ListDataConverter : IDataConverter {

    override fun convert(jsonText: String): List<ItemEntity> {
        val entityList = ArrayList<ItemEntity>()
        var dataList = JSON
            .parseObject(jsonText)
            .getJSONObject("data")
            .getJSONArray("list")
        var size = dataList.size
        for (i in 0 until size) {
            val data = dataList.getJSONObject(i)
            val id = data.getInteger("id")
            val name = data.getString("name")
            val entity = ItemEntity.Builder()
                .setField(ItemFields.ITEM_TYPE, ItemType.SORT_LIST)
                .setField(ItemFields.ID, id)
                .setField(ItemFields.NAME, name)
                .setField(ItemFields.IS_SELECT, false)
                .build()
            entityList.add(entity)
        }
        // 默认选中第一个
        entityList[0].setFiled(ItemFields.IS_SELECT, true)
        return entityList
    }
}