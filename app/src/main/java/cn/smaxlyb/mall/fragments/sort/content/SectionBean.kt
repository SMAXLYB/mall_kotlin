package cn.smaxlyb.mall.fragments.sort.content

import com.chad.library.adapter.base.entity.SectionEntity

// 每个分组的内容,这里内部存储了一个SectionContentEntity,当这个分组为标题时为空,当为主体时有entity
// 既可以当分组标题,也可以当分组内容
class SectionBean : SectionEntity<SectionContentEntity> {

    // 是否显示更多标签
    var isMore = false

    // 默认id
    var id = -1

    constructor(isHeader: Boolean, header: String) : super(isHeader, header)

    constructor(sectionContentEntity: SectionContentEntity) : super(sectionContentEntity)

}