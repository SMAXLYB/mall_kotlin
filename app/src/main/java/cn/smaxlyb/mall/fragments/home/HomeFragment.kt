package cn.smaxlyb.mall.fragments.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.ibrary.fragments.bottom.ContentItemFragment
import cn.smaxlyb.mall.ibrary.net.RestClient
import cn.smaxlyb.mall.ibrary.net.callback.IError
import cn.smaxlyb.mall.ibrary.net.callback.ISuccess
import cn.smaxlyb.mall.ibrary.ui.loader.LoaderStyle
import cn.smaxlyb.mall.ibrary.ui.recycler.ItemAdapter
import cn.smaxlyb.mall.ibrary.ui.recycler.MyDecoration


class HomeFragment : ContentItemFragment() {
    private lateinit var mRecyclerView: RecyclerView

    override fun setLayout(): Any {
        return R.layout.fragment_home
    }

    // onCreateView()方法执行后回调
    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        Toast.makeText(context, "主页初始化完成", Toast.LENGTH_SHORT).show()
        mRecyclerView = rootView.findViewById(R.id.rv_home)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.tb_home)
        toolbar.background.alpha = 0
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initRecyclerView()
        initData()
    }

    private fun initRecyclerView() {
        val manager = GridLayoutManager(context, 4)
        mRecyclerView.layoutManager = manager
        mRecyclerView.addItemDecoration(MyDecoration.create(Color.LTGRAY, 5))
    }

    private fun initData() {
        RestClient.builder()
            .setUrl("index.php")
            .setLoader(context!!, LoaderStyle.BallGridBeatIndicator)
            .onSuccess(object : ISuccess {
                override fun onSuccess(response: String) {
                    Toast.makeText(context, "加载成功", Toast.LENGTH_SHORT).show()
                    val adapter = ItemAdapter.create(HomeDataConverter().convert(response))
                    mRecyclerView.adapter = adapter
                }
            })
            .onError(object : IError {
                override fun onError(code: Int, msg: String) {
                    Toast.makeText(context, "Error code: $code", Toast.LENGTH_SHORT).show()
                }
            })
            .build()
            .get()
    }
}