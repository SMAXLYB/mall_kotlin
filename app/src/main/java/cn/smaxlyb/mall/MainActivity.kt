package cn.smaxlyb.mall

import cn.smaxlyb.mall.fragments.MallMainFragment
import cn.smaxlyb.mall.ibrary.activities.ProxyActivity
import cn.smaxlyb.mall.ibrary.fragments.BusinessFragment

class MainActivity : ProxyActivity() {
    override fun setRootFragment(): BusinessFragment {
        return MallMainFragment()
    }
}