package cn.smaxlyb.mall.ibrary.ui.recycler

import androidx.annotation.ColorInt
import com.choices.divider.DividerItemDecoration

// recyclerView的分割线
class MyDecoration private constructor(@ColorInt color: Int, size: Int) :
    DividerItemDecoration() {

    init {
        setDividerLookup(DividerLookupImpl(color, size))
    }

    companion object {
        fun create(@ColorInt color: Int, size: Int): MyDecoration {
            return MyDecoration(color, size)
        }
    }

}