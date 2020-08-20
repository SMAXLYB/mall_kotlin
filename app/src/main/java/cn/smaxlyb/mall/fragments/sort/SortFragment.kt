package cn.smaxlyb.mall.fragments.sort

import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.ibrary.fragments.bottom.ContentItemFragment

class SortFragment : ContentItemFragment() {
    override fun setLayout(): Any {
        return R.layout.fragment_sort
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        Toast.makeText(context, "分类初始化完成", Toast.LENGTH_SHORT).show()
    }
}