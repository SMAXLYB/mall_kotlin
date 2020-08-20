package cn.smaxlyb.mall.ibrary.activities

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import cn.smaxlyb.mall.ibrary.R
import cn.smaxlyb.mall.ibrary.fragments.BusinessFragment
import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportActivity
import me.yokeyword.fragmentation.SupportActivityDelegate
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

// 容器activity,用来承载fragment
abstract class ProxyActivity : AppCompatActivity(), ISupportActivity {
    private var mDelegate: SupportActivityDelegate = SupportActivityDelegate(this)

    // 根fragment,即第一个显示的fragment
    abstract fun setRootFragment(): BusinessFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化代理
        // mDelegate = SupportActivityDelegate(this)
        mDelegate.onCreate(savedInstanceState)

        initContainer(savedInstanceState)
    }

    //初始化activity布局,使用frameLayout来承载
    private fun initContainer(savedInstanceState: Bundle?) {
        // 实例化一个view,设置id
        val container = FrameLayout(this)
        container.id = R.id.fragment_container

        setContentView(container)

        // 如果没有要恢复的,就加载frameLayout中的第一个fragment
        if (savedInstanceState == null) {
            mDelegate.loadRootFragment(R.id.fragment_container, setRootFragment())
        }
    }

    override fun onDestroy(){
        mDelegate.onDestroy()
        super.onDestroy()
        System.gc()
        System.runFinalization()
    }

    override fun post(runnable: Runnable?) {
        mDelegate.post(runnable)
    }

    override fun getSupportDelegate(): SupportActivityDelegate {
        return mDelegate
    }

    override fun extraTransaction(): ExtraTransaction {
        return mDelegate.extraTransaction()
    }

    override fun getFragmentAnimator(): FragmentAnimator {
        return mDelegate.fragmentAnimator
    }

    // 设置动画
    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator?) {
        mDelegate.fragmentAnimator = DefaultHorizontalAnimator()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mDelegate.onCreateFragmentAnimator()
    }

    override fun onBackPressedSupport() {
        mDelegate.onBackPressedSupport()
    }

    override fun onBackPressed() {
        mDelegate.onBackPressed()
    }
}
