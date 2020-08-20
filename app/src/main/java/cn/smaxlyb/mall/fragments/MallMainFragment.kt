package cn.smaxlyb.mall.fragments

import android.graphics.Color
import cn.smaxlyb.mall.fragments.home.HomeFragment
import cn.smaxlyb.mall.fragments.my.MyFragment
import cn.smaxlyb.mall.fragments.shoppingcart.ShoppingCartFragment
import cn.smaxlyb.mall.fragments.sort.SortFragment
import cn.smaxlyb.mall.ibrary.fragments.bottom.BaseBottomFragment
import cn.smaxlyb.mall.ibrary.fragments.bottom.BottomTabBean
import cn.smaxlyb.mall.ibrary.fragments.bottom.ContentItemFragment
import cn.smaxlyb.mall.ibrary.fragments.bottom.ItemBuilder

class MallMainFragment : BaseBottomFragment() {
    override fun setItems(builder: ItemBuilder): LinkedHashMap<BottomTabBean, ContentItemFragment> {
        val items = LinkedHashMap<BottomTabBean, ContentItemFragment>()
        items[BottomTabBean("{fa-home}", "主页")] = HomeFragment()
        items[BottomTabBean("{fa-sort}", "分类")] = SortFragment()
        items[BottomTabBean("{fa-shopping-cart}", "购物车")] = ShoppingCartFragment()
        items[BottomTabBean("{fa-user}", "我的")] = MyFragment()
        return builder.addItems(items).build()
    }

    override fun setIndexFragment(): Int {
        return 1
    }

    override fun setClickColor(): Int {
        return Color.parseColor("#ff00aeff")
    }

}