package cn.smaxlyb.mall.fragments.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import cn.smaxlyb.mall.R
import cn.smaxlyb.mall.ibrary.global.GlobalKeys
import cn.smaxlyb.mall.ibrary.global.Mall

class TranslucentBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<Toolbar>(context, attrs) {

    companion object {
        private const val MORE = 100
    }

    private var mOffSet = 0

    // 确定动作依赖的载体,即recyclerView
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: Toolbar,
        dependency: View
    ): Boolean {
        return dependency.id == R.id.rv_home
    }

    // 是否消费滑动事件
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Toolbar,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return true
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Toolbar,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        val context = Mall.getConfiguration<Context>(GlobalKeys.APPLICATION_CONTEXT)

        // 滑动起始点
        val startOffSet = 0
        // 滑动结束点
        val endOffset = context.resources.getDimensionPixelOffset(R.dimen.header_height) + MORE

        // 水平滚动距离
        mOffSet += dyConsumed
        //改变透明度
        when {
            mOffSet <= startOffSet -> child.background.alpha = 0
            mOffSet in (startOffSet + 1) until endOffset -> {
                var percent = (mOffSet - startOffSet).toFloat() / endOffset
                val alpha = Math.round(percent * 255)
                child.background.alpha = alpha
            }
            mOffSet >= endOffset -> child.background.alpha = 255
        }

    }
}