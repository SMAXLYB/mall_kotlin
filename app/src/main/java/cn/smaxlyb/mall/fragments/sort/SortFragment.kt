package cn.smaxlyb.mall.fragments.sort

import android.os.Bundle
import android.view.View
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.fragments.sort.content.ContentFragment
import cn.smaxlyb.mall.fragments.sort.list.ListFragment
import cn.smaxlyb.mall.ibrary.fragments.bottom.ContentItemFragment

class SortFragment : ContentItemFragment() {
    override fun setLayout(): Any {
        return R.layout.fragment_sort
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)

        val listFragment = ListFragment()
        supportDelegate.loadRootFragment(R.id.sort_list_container, listFragment)
        val contentFragment = ContentFragment.newInstance(1)
        supportDelegate.loadRootFragment(R.id.sort_content_container, contentFragment)
    }
}