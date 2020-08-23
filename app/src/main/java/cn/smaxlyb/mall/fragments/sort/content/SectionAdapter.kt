package cn.smaxlyb.mall.fragments.sort.content

import androidx.appcompat.widget.AppCompatImageView
import cn.smaxlyb.mall.R
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class SectionAdapter(contentResId: Int, headerResId: Int, data: List<SectionBean>) :
    BaseSectionQuickAdapter<SectionBean, BaseViewHolder>(contentResId, headerResId, data) {

    override fun convert(holder: BaseViewHolder, item: SectionBean) {
        val thumb = item.t.goodsThumb
        val name = item.t.goodsName
        holder.setText(R.id.content_name, name)
        val contentImageView = holder.getView<AppCompatImageView>(R.id.content_thumb)
        Glide.with(mContext)
            .load(thumb)
            .into(contentImageView)
    }

    override fun convertHead(holder: BaseViewHolder, item: SectionBean) {
        // 设置分组标题
        holder.setText(R.id.header_title, item.header)
        // 设置分组更多标签,文字已经写死
        holder.setVisible(R.id.header_more, item.isMore)
        // holder.addOnClickListener(R.id.header_more)
    }
}