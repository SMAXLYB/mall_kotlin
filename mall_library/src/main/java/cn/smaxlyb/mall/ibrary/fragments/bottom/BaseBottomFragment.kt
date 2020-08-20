package cn.smaxlyb.mall.ibrary.fragments.bottom

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import cn.smaxlyb.mall.ibrary.R
import cn.smaxlyb.mall.ibrary.fragments.BusinessFragment
import com.joanzapata.iconify.widget.IconTextView

// 拥有多个fragment的fragment的基类
abstract class BaseBottomFragment : BusinessFragment() {

    private val mTabBeans = ArrayList<BottomTabBean>()
    private val mItemFragments = ArrayList<ContentItemFragment>()
    private val mItems = LinkedHashMap<BottomTabBean, ContentItemFragment>()

    private lateinit var mBottomTab: LinearLayoutCompat

    // 当前页面
    private var mCurrentFragment = 0

    // 起始页面
    private var mIndexFragment = 0

    //点击颜色
    private var mClickedColor = Color.RED

    // 设置布局
    override fun setLayout(): Any {
        return R.layout.fragment_bottom
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIndexFragment = setIndexFragment()
        if (setClickColor() != 0) {
            mClickedColor = setClickColor()
        }
        val builder = ItemBuilder.builder()
        val items = setItems(builder)
        // 取出已经存入的bean和fragment
        mItems.putAll(items)
        for ((key, value) in mItems) {
            mTabBeans.add(key)
            mItemFragments.add(value)
        }
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mBottomTab = rootView.findViewById(R.id.bottom_tab)
        val size = mItems.size
        for (i in 0 until size) {
            // 将每个tab加载到tab栏中
            LayoutInflater.from(context).inflate(R.layout.bottom_item, mBottomTab)
            val item = mBottomTab.getChildAt(i) as RelativeLayout

            // 设置标签,方便设置监听
            item.tag = i
            // 设置监听
            item.setOnClickListener { view ->
                val tabIndex = view.tag as Int
                changeColor(tabIndex)
                // 展示所选fragment
                supportDelegate.showHideFragment(
                    mItemFragments[tabIndex],
                    mItemFragments[mCurrentFragment]
                )
                // 切换当前下标
                mCurrentFragment = tabIndex
            }

            val icon = item.getChildAt(0) as IconTextView
            val title = item.getChildAt(1) as AppCompatTextView
            // 初始化数据
            icon.text = mTabBeans[i].icon
            title.text = mTabBeans[i].title
            if (i == mIndexFragment) {
                icon.setTextColor(mClickedColor)
                title.setTextColor(mClickedColor)
            }
        }

        // 展示当前fragment
        val fragments = mItemFragments.toTypedArray<ContentItemFragment>()
        // 加载一系列fragment到tab内容上方
        supportDelegate.loadMultipleRootFragment(
            R.id.bottom_fragment_container,
            mIndexFragment,
            *fragments
        )
    }

    private fun resetColor() {
        val count = mBottomTab.childCount
        for (i in 0 until count) {
            val item = mBottomTab.getChildAt(i) as RelativeLayout
            val icon = item.getChildAt(0) as IconTextView
            val title = item.getChildAt(1) as AppCompatTextView
            icon.setTextColor(Color.GRAY)
            title.setTextColor(Color.GRAY)
        }
    }

    private fun changeColor(index: Int) {
        resetColor()
        val item = mBottomTab.getChildAt(index) as RelativeLayout
        val icon = item.getChildAt(0) as IconTextView
        val title = item.getChildAt(1) as AppCompatTextView
        icon.setTextColor(mClickedColor)
        title.setTextColor(mClickedColor)
    }

    // 构建Builder自然返回linkedHashMap
    abstract fun setItems(builder: ItemBuilder): LinkedHashMap<BottomTabBean, ContentItemFragment>
    abstract fun setIndexFragment(): Int

    @ColorInt
    abstract fun setClickColor(): Int
}