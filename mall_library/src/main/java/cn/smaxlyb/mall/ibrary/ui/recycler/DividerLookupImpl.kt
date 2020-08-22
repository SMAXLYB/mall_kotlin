package cn.smaxlyb.mall.ibrary.ui.recycler

import androidx.annotation.ColorInt
import com.choices.divider.Divider
import com.choices.divider.DividerItemDecoration

class DividerLookupImpl(
    @ColorInt private val color: Int, private val size: Int
) : DividerItemDecoration.DividerLookup {

    override fun getVerticalDivider(position: Int): Divider {
        return Divider.Builder()
            .size(size)
            .color(color)
            .build()
    }

    override fun getHorizontalDivider(position: Int): Divider {
        return Divider.Builder()
            .size(size)
            .color(color)
            .build()
    }
}