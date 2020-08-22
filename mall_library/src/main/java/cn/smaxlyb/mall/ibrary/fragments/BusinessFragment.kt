package cn.smaxlyb.mall.ibrary.fragments

import android.widget.Toast

// 业务fragment的基类
abstract class BusinessFragment : BaseFragment() {
    // 点击时间
    private var mTouchTime: Long = 0

    // 双击有效时间
    companion object {
        private const val WAIT_TIME = 2000L
    }

    // 检测双击退出fragment所在的Activity
    protected fun exitWithDoubleClick(): Boolean {
        if (System.currentTimeMillis() - mTouchTime < 2000L) {
            _mActivity.finish()
        } else {
            mTouchTime = System.currentTimeMillis()
            Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT)
        }

        return true
    }

    override fun onBackPressedSupport(): Boolean {
        return exitWithDoubleClick()
    }
}