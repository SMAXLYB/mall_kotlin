package cn.smaxlyb.mall.fragments.sort.list

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.fragments.sort.SortFragment
import cn.smaxlyb.mall.fragments.sort.content.ContentFragment
import cn.smaxlyb.mall.ibrary.ui.recycler.*
import me.yokeyword.fragmentation.SupportHelper

class ListRecyclerAdapter(data: List<ItemEntity>, private val fragment: SortFragment) :
    ItemAdapter(data) {

    private var mCurrentPosition = 0

    init {
        initView()
    }

    private fun initView() {
        addItemType(ItemType.SORT_LIST, R.layout.sort_list)
    }

    override fun convert(holder: ItemViewHolder, entity: ItemEntity) {
        super.convert(holder, entity)
        when (holder.itemViewType) {
            ItemType.SORT_LIST -> {
                val text = entity.getField<String>(ItemFields.NAME)
                val isSelect = entity.getField<Boolean>(ItemFields.IS_SELECT)
                val id = entity.getField<Int>(ItemFields.ID)

                val name = holder.getView<AppCompatTextView>(R.id.name)
                val line = holder.getView<View>(R.id.line)
                val itemView = holder.itemView

                itemView.setOnClickListener {
                    val position = holder.adapterPosition
                    if (mCurrentPosition != position) {
                        // 上一个被选中的取消选中
                        data[mCurrentPosition].setFiled(ItemFields.IS_SELECT, false)
                        notifyItemChanged(mCurrentPosition)
                        // 当前选中
                        entity.setFiled(ItemFields.IS_SELECT, true)
                        notifyItemChanged(position)
                        // 更新位置
                        mCurrentPosition = position
                        // 切换content的fragment
                        switchContent(id)
                    }
                }
                //未选中
                if (!isSelect) {
                    line.visibility = View.INVISIBLE
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_blank))
                    itemView.setBackgroundColor(
                        ContextCompat.getColor(
                            mContext,
                            R.color.item_background
                        )
                    )
                } else {
                    line.visibility = View.VISIBLE
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                    itemView.setBackgroundColor(Color.WHITE)
                }
                name.text = text
            }
            else -> {

            }
        }
    }

    // 切换content
    private fun switchContent(contentId: Int) {
        val toFragment = ContentFragment.newInstance(contentId)
        val fromFragment =
            SupportHelper.findFragment(fragment.childFragmentManager, ContentFragment::class.java)
        fromFragment.supportDelegate.replaceFragment(toFragment, false)
    }
}