package cn.smaxlyb.mall.fragments.sort.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.fragments.sort.SortFragment
import cn.smaxlyb.mall.ibrary.fragments.BusinessFragment
import cn.smaxlyb.mall.ibrary.net.RestClient
import cn.smaxlyb.mall.ibrary.net.callback.ISuccess

class ListFragment : BusinessFragment() {
    private lateinit var mRecyclerView: RecyclerView

    override fun setLayout(): Any {
        return R.layout.fragment_sort_list
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mRecyclerView = rootView.findViewById(R.id.rv_sort_list)
        val manager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = manager
        mRecyclerView.itemAnimator = null
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        RestClient.builder()
            .setUrl("sort_list.php")
            .setLoader(context!!)
            .onSuccess(object : ISuccess {
                override fun onSuccess(response: String) {
                    val data = ListDataConverter().convert(response)
                    val adapter = ListRecyclerAdapter(data, parentFragment as SortFragment)
                    mRecyclerView.adapter = adapter
                }
            })
            .build()
            .get()
    }
}