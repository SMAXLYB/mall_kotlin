package cn.smaxlyb.mall.fragments.shoppingcart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.ibrary.fragments.bottom.ContentItemFragment

class ShoppingCartFragment : ContentItemFragment() {
    override fun setLayout(): Any {
        return R.layout.fragment_shopping_cart
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        Toast.makeText(context, "购物车初始化完成", Toast.LENGTH_SHORT).show()
    }
}