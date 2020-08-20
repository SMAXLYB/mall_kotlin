package cn.smaxlyb.mall.fragments.my

import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.ibrary.fragments.bottom.ContentItemFragment

class MyFragment : ContentItemFragment() {
    override fun setLayout(): Any {
        return R.layout.fragment_my
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        Toast.makeText(context, "我的初始化完成", Toast.LENGTH_SHORT).show()
    }
}