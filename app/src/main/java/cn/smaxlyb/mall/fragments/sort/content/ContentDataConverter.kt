package cn.smaxlyb.mall.fragments.sort.content

import com.alibaba.fastjson.JSON

class ContentDataConverter {

    internal fun convert(jsonText: String): List<SectionBean> {
        val beanList = ArrayList<SectionBean>()
        val dataArray = JSON.parseObject(jsonText)
            .getJSONArray("data")
        val size = dataArray.size
        for (i in 0 until size) {
            val data = dataArray.getJSONObject(i)
            val id = data.getInteger("id")
            val sectionTitle = data.getString("section")

            // 分组头部
            val sectionTitleBean = SectionBean(true, sectionTitle)
            sectionTitleBean.id = id
            // 是否有 更多标签
            sectionTitleBean.isMore = true
            beanList.add(sectionTitleBean)

            // 分组主体
            val goods = data.getJSONArray("goods")
            val goodSize = goods.size
            for (j in 0 until goodSize) {
                val good = goods.getJSONObject(j)
                val goodsId = good.getInteger("goods_id")
                val goodsName = good.getString("goods_name")
                val goodsThumb = good.getString("goods_thumb")
                val sectionContentEntity = SectionContentEntity()
                sectionContentEntity.goodsId = goodsId
                sectionContentEntity.goodsName = goodsName
                sectionContentEntity.goodsThumb = goodsThumb
                val sectionContentBean = SectionBean(sectionContentEntity)
                beanList.add(sectionContentBean)
            }
        }
        return beanList
    }
}