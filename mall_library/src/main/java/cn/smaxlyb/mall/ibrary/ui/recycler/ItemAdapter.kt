package cn.smaxlyb.mall.ibrary.ui.recycler

import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import cn.smaxlyb.mall.ibrary.R
import cn.smaxlyb.mall.ibrary.ui.banner.BannerCreator
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.listener.OnItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter

//recyclerView的adapter,负责创建viewHolder和关联数据
class ItemAdapter private constructor(data: MutableList<ItemEntity>) :
    BaseMultiItemQuickAdapter<ItemEntity, ItemViewHolder>(data), BaseQuickAdapter.SpanSizeLookup,
    OnItemClickListener {

    private var isBannerInitialized = false

    companion object {
        private val BANNER_OPTIONS = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop()

        // 静态工厂
        fun create(data: MutableList<ItemEntity>): ItemAdapter {
            return ItemAdapter(data)
        }
    }

    init {
        initView()
    }

    // 配置不同的类型使用不同的布局
    private fun initView() {
        addItemType(ItemType.TEXT, R.layout.item_text)
        addItemType(ItemType.IMAGE, R.layout.item_image)
        addItemType(ItemType.IMAGE_TEXT, R.layout.item_image_text)
        addItemType(ItemType.BANNER, R.layout.item_banner)

        // 设置大小变化监听
        setSpanSizeLookup(this)
        openLoadAnimation()
        isFirstOnly(false)
    }

    // // 创建viewHolder
    // override fun createBaseViewHolder(view: View): ItemViewHolder {
    //     return ItemViewHolder.create(view)
    // }

    // 关联数据和viewHolder
    override fun convert(holder: ItemViewHolder, entity: ItemEntity) {
        val text: String
        val imageUrl: String
        val bannerImageUrls: ArrayList<String>
        // 根据viewHolder返回的类型就知道当前的holder承载哪个布局
        when (holder.itemViewType) {
            ItemType.TEXT -> {
                text = entity.getField(ItemFields.TEXT)
                holder.setText(R.id.text_single, text)
            }
            ItemType.IMAGE -> {
                imageUrl = entity.getField(ItemFields.IMAGE_URL)
                Glide.with(mContext)
                    .load(imageUrl)
                    .apply(BANNER_OPTIONS)
                    .into(holder.getView(R.id.image_single))
            }
            ItemType.IMAGE_TEXT -> {
                text = entity.getField(ItemFields.TEXT)
                holder.setText(R.id.text_multiple, text)
                imageUrl = entity.getField(ItemFields.IMAGE_URL)
                Glide.with(mContext)
                    .load(imageUrl)
                    .apply(BANNER_OPTIONS)
                    .into(holder.getView<ImageView>(R.id.image_multiple))
            }
            ItemType.BANNER -> if (!isBannerInitialized) {
                bannerImageUrls = entity.getField(ItemFields.BANNERS)
                val banner = holder.getView<ConvenientBanner<String>>(R.id.banner)
                BannerCreator.setDefault(banner, bannerImageUrls, this)
                isBannerInitialized = true
            }
            else -> {
            }
        }
    }


    // 轮播图监听
    override fun onItemClick(position: Int) {
    }

    //　动态设置大小
    override fun getSpanSize(p0: GridLayoutManager?, position: Int): Int {
        return data[position].getField<Int>(ItemFields.SPAN_SIZE)
    }
}