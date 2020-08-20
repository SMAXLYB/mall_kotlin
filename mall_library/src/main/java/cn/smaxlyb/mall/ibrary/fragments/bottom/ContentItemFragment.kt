package cn.smaxlyb.mall.ibrary.fragments.bottom

import cn.smaxlyb.mall.ibrary.fragments.BusinessFragment

// Tab栏中每个tab对应页面的fragment
abstract class ContentItemFragment : BusinessFragment(){

    override fun onBackPressedSupport(): Boolean {
        return exitWithDoubleClick()
    }
}