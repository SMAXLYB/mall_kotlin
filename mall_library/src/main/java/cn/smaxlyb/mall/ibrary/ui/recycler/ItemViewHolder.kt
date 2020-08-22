package cn.smaxlyb.mall.ibrary.ui.recycler

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
// 负责承载View
class ItemViewHolder constructor(view: View) : BaseViewHolder(view) {
    companion object {
        fun create(view: View): ItemViewHolder {
            return ItemViewHolder(view)
        }
    }
}