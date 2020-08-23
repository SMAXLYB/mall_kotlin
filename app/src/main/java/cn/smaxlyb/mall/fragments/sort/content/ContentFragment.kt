package cn.smaxlyb.mall.fragments.sort.content

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.ibrary.fragments.BusinessFragment
import cn.smaxlyb.mall.ibrary.net.RestClient
import cn.smaxlyb.mall.ibrary.net.callback.ISuccess

class ContentFragment private constructor() : BusinessFragment() {

    private lateinit var mRecyclerView: RecyclerView
    private var mContentId = -1

    // 静态方法创建Content,方便传参
    companion object {
        private const val CONTENT_ID = "contentId"
        fun newInstance(contentId: Int): ContentFragment {
            val bundle = Bundle()
            bundle.putInt(CONTENT_ID, contentId)
            val contentFragment = ContentFragment()
            contentFragment.arguments = bundle
            return contentFragment
        }
    }

    override fun setLayout(): Any {
        return R.layout.fragment_sort_content
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            mContentId = args.getInt(CONTENT_ID)
        }
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mRecyclerView = rootView.findViewById(R.id.rv_sort_content)
        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView.layoutManager = manager
        initData()
    }

    private fun initData() {
        RestClient.builder()
            .setUrl("sort_content_list.php?contentId=$mContentId")
            .onSuccess(object : ISuccess {
                override fun onSuccess(response: String) {
                    val data = ContentDataConverter().convert(response)
                    mRecyclerView.adapter = SectionAdapter(
                        R.layout.sort_section_content,
                        R.layout.sort_section_header,
                        data
                    )
                }
            })
            .build()
            .get()
    }
}