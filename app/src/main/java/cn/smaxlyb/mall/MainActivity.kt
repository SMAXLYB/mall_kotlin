package cn.smaxlyb.mall

import android.os.Bundle
import cn.smaxlyb.mall.fragments.MallMainFragment
import cn.smaxlyb.mall.ibrary.activities.ProxyActivity
import cn.smaxlyb.mall.ibrary.fragments.BusinessFragment

class MainActivity : ProxyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    override fun setRootFragment(): BusinessFragment {
        return MallMainFragment()
    }
}