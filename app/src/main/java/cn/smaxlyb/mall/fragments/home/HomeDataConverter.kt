package cn.smaxlyb.mall.fragments.home

import cn.smaxlyb.mall.ibrary.ui.recycler.ItemEntity
import cn.smaxlyb.mall.ibrary.ui.recycler.ItemFields
import cn.smaxlyb.mall.ibrary.ui.recycler.ItemType
import com.alibaba.fastjson.JSON

class HomeDataConverter {

    fun convert(jsonText: String): ArrayList<ItemEntity> {

        val entities = ArrayList<ItemEntity>()

        val dataArray = JSON.parseObject(jsonText).getJSONArray("data")
        for (i in 0 until dataArray.size) {
            val data = dataArray.getJSONObject(i)
            // 解析数据
            val id = data.getInteger("goodsId")
            val text = data.getString("text")
            val imageUrl = data.getString("imageUrl")
            val spanSize = data.getInteger("spanSize")
            val banners = data.getJSONArray("banners")

            val bannerImageUrls = ArrayList<String>()
            var type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE
            } else if (imageUrl != null) {
                type = ItemType.IMAGE_TEXT
            } else if (banners != null) {
                type = ItemType.BANNER
                // 继续解析
                for (j in 0 until banners.size) {
                    val imageUrl = banners.getString(j)
                    bannerImageUrls.add(imageUrl)
                }
            }

            // 解析完毕,构造实体,有些字段可能为空,但无妨紧要
            val entity = ItemEntity.Builder()
                .setField(ItemFields.ITEM_TYPE, type)
                .setField(ItemFields.ID, id)
                .setField(ItemFields.TEXT, text)
                .setField(ItemFields.IMAGE_URL, imageUrl)
                .setField(ItemFields.SPAN_SIZE, spanSize)
                .setField(ItemFields.BANNERS, bannerImageUrls)
                .build()

            entities.add(entity)
        }

        return entities
    }
}